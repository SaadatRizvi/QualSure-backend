package com.qualsure.dataapi.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qualsure.dataapi.DbSeeder;
import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.MultipleDegree;
import com.qualsure.dataapi.model.NetworkConfig;
import com.qualsure.dataapi.model.ResponseStatus;
import com.qualsure.dataapi.model.Users;

@Service
public class DegreeService {
	
	@Autowired
	private static Logger logger = Logger.getLogger(DbSeeder.class);

	@Autowired	
	private DegreeDAO degreeDAO;

	@Autowired
    private UsersService userService;
	
	
	@Autowired
	private HashService hashService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public List<Degree> getAllDegrees(){
	
		
		return degreeDAO.findAll();
	}
	
	public Degree getDegree(String universityId, String degreeId) {
	//	return degrees.stream().filter(t -> t.getId() == degreeId).findFirst().get();
		return degreeDAO.findByUniversityIdAndId(universityId, degreeId);
	}

	public Map<String,String> addDegree(String universityId, String password, Map<String, String>  degreeDetails)   {

		Map<String,String> endResponse = new HashMap<String,String>();
		Users user= userService.findById(universityId);
		if(user==null){
			endResponse.put("status", "false");
			endResponse.put("errorMessage", "university doesnt exist");
			return endResponse;
		}
		if(!verifyLogin(user,password))
		{
			endResponse.put("status", "false");
			endResponse.put("errorMessage", "Password is incorrect");
			return endResponse;
		}
		
		degreeDetails.put("universityId", universityId);
		String hash = hashService.getHash(degreeDetails);
		List<Degree> degree = degreeDAO.findByHash(hash);
		logger.info("Hash addDegree="+hash);
		
		if(degree.isEmpty()){
			Degree newDegree = new Degree(universityId,degreeDetails, hash,"Pending");
			degreeDAO.insert(newDegree);
			if(!addDataCryptDegree(user,password,hash)){
				newDegree.setStatus("Failed");
				logger.info("Failed1");
				this.updateDegree(newDegree);
				endResponse.put("status", "false");
				endResponse.put("errorMessage", "Degree cannot be inserted in DataCrypt");
				return endResponse;
			}
			
			newDegree.setStatus("Success");
			logger.info("Success");
			this.updateDegree(newDegree);
			endResponse.put("status", "true");
			endResponse.put("degreeId", newDegree.getId());
			return endResponse;
		}
		
		endResponse.put("status", "false");
		endResponse.put("errorMessage", "Degree already in QualSure");
		return endResponse;

	}
	
	public boolean verifyLogin (Users user, String password) {
		return encoder.matches(password, user.getPassword());
	}
	
	public List<List<String>> addMultipleDegree(String universityId, MultipleDegree responseObj) {
		Users user= userService.findById(universityId);
		if(!verifyLogin(user,responseObj.getPassword()))
			return null;
		
		List<String> hashList = new ArrayList<String>();
		List<String> hashListHashExistFailed = new ArrayList<String>();
		List<String> hashListDataCryptFailed = new ArrayList<String>();
		List<String> hashListSuccess = new ArrayList<String>();


		for(Map<String,String> degreeDetails: responseObj.getDegreeDetails()){
			degreeDetails.put("universityId", universityId);
			String hash = hashService.getHash(degreeDetails);
			List<Degree> degree = degreeDAO.findByHash(hash);
			if(degree.isEmpty()){
				Degree newDegree = new Degree(universityId,degreeDetails, hash,"Pending");
				this.updateDegree(newDegree);
				hashList.add(hash);
			}
			else{
				hashListHashExistFailed.add(hash);
				Degree newDegree = new Degree(universityId,degreeDetails, hash,"Failed");
				this.updateDegree(newDegree);
			}	
		}
		
		Map<String,String> response = addDataCryptMultipleDegree(user, responseObj.getPassword(), hashList);
		
		for (Map.Entry<String, String> entry : response.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    if(entry.getValue().equals("false")){
		    	hashListDataCryptFailed.add(entry.getKey());
				List<Degree> degree = degreeDAO.findByHash(entry.getKey());
				degree.get(0).setStatus("Failed");
				this.updateDegree(degree.get(0));
		    }
		    else{
		    	hashListSuccess.add(entry.getKey());
				List<Degree> degree = degreeDAO.findByHash(entry.getKey());
				degree.get(0).setStatus("Success");
				this.updateDegree(degree.get(0));
		    }
		}
		
		List<List<String>> listOLists = new ArrayList<List<String>>();
		
		listOLists.add(hashListHashExistFailed);
		listOLists.add(hashListDataCryptFailed);
		listOLists.add(hashListSuccess);

				
		return listOLists; 
	}
	
	public ObjectNode makeJson(Users user, String password, List<String> hashList){
		byte[] decryptedCipherText = userService.decryptPassword(password, user.getDataCryptPassword());

	    ObjectNode jsonBody = JsonNodeFactory.instance.objectNode();
	    jsonBody.put("username", user.getUsername());
	    jsonBody.put("password",  new String(decryptedCipherText, StandardCharsets.UTF_8));  
	    ArrayNode arrayNode = jsonBody.putArray("hash");
	    for (String item : hashList) {
	        arrayNode.add(item);
	    }
	    

	    return jsonBody;
	}
	
	public Map<String,String> addDataCryptMultipleDegree(Users user, String password, List<String> hashList){
		
		try{
	      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			  
			  ObjectNode jsonBody = makeJson(user,password, hashList);
			  String url = NetworkConfig.getDatacryptIP()+"/addMultipleFiles";
				   
			  System.out.println(jsonBody.toString());
			  HttpEntity<String>request = new HttpEntity<>(jsonBody.toString(), headers);
			  
			  @SuppressWarnings("unchecked")    // Possible error here
			  Map<String,String> response =  restTemplate.postForObject( url, request , HashMap.class );
			  
			  System.out.println(response.toString());
			  return response;
		}
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        e.printStackTrace();
	        return null;
	    }	
		
	}
	
	
	public boolean addDataCryptDegree(Users user, String password, String hash){
		
		
		byte[] decryptedCipherText = userService.decryptPassword(password, user.getDataCryptPassword());
		
		try{
	      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("username", user.getUsername());
			  map.add("password",  new String(decryptedCipherText, StandardCharsets.UTF_8));  
			  map.add("hash", hash);
			  String url = NetworkConfig.getDatacryptIP()+"/addFile";
				   
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
				  
			  System.out.println(response.toString());
		      if(response.getStatus().equals("true")){
		    	 return true;
		      }
		      else {
		    	  return false;
		      }
		}
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        return false;
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public void updateDegree( Degree degree) {
		degreeDAO.save(degree);
	}

	public void deleteDegree(String degreeId) {
		//	degrees.removeIf(t-> t.getId() == degreeId);
		degreeDAO.delete(degreeId);
	}
	
	public List<Degree> findByUniId(String universityId) {
		return degreeDAO.findByUniversityId(universityId);
	}
	

	public  Map<String, String> verifyDegree(String universityId, Map<String, String> degreeDetails) {
		
		Map<String, String> endResponse = new HashMap<String,String>();

		Degree degree = new Degree(universityId,degreeDetails,"tempHash");

		String studentName = degree.getStudentName();
		String gpa = degree.getGpa();
		String graduationYear = degree.getGraduationYear();
		String degreeType = degree.getDegreeType();
		String degreeName = degree.getDegreeName();
		String CNIC = degree.getCNIC();
		degreeDetails.put("universityId", universityId);
		degree.setHash(hashService.getHash(degreeDetails));
		String degreeId;
		try{
			Degree returnedDegree= degreeDAO.findByFixedFields(universityId, studentName, gpa, graduationYear, degreeType, degreeName,CNIC,"Success");

			System.out.println("step1");
			degreeId = returnedDegree.getId();

			String[] keys = returnedDegree.getDegreeDetails().keySet().toArray(new String[0]);
			System.out.println("step2");
	
				for(int i=0;i<keys.length;i++) {
					if(!returnedDegree.getDegreeDetails().get(keys[i]).equals(degree.getDegreeDetails().get(keys[i])) ) {
						endResponse.put("status", "false");
						endResponse.put("errorMessage", "Degree not verified in QualSure Database");
						return endResponse;
					}
				}
			
		}
		catch(NullPointerException e){
			endResponse.put("status", "false");
			endResponse.put("errorMessage", "Degree not found in QualSure Database");
			return endResponse;
		}
		String rs=verifyDataCryptDegree(degree.getHash(),degree.getUniversityId());
		System.out.println(rs);
		
		if(rs == "true"){
			endResponse.put("status", "true");
			endResponse.put("degreeId", degreeId);
		}
		else{
			endResponse.put("status", "false");
			endResponse.put("errorMessage", rs);
		}
		
		return endResponse;
	}
	
	public String verifyDataCryptDegree(String degreeHash,String universityId){
		try{
	      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("hash", degreeHash);
				  
			  String url = NetworkConfig.getDatacryptIP()+"/verifyFile";
				   
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
				  
			  System.out.println(response.toString());
		      if(response.getStatus().equals("true")){
		    	  Users user = userService.findById(universityId);
		    	  if(user.getPublicAddress().equals(response.getOwner())){
		    		  System.out.println("true------------------->>>> degree verified");
		    		  return "true";
		    	  }
		    	  else 
		    		  return "Degree not Anchored by this University";
		      }
		      else {
		    	  return "Degree not found in DataCrypt";
		      }
		}
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        
	        return "DataCrypt not Responding";	    }
	}


	
}
