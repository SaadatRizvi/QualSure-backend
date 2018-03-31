package com.qualsure.dataapi.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
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

import com.qualsure.dataapi.DbSeeder;
import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;
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

	public Degree addDegree(String universityId, String password, Map<String, String>  degreeDetails)   {
		Users user= userService.findById(universityId);
		if(!verifyLogin(user,password))
			return null;
		
		String hash = hashService.getHash(degreeDetails);

		if(!addDataCryptDegree(user,password,hash)) return null;
		Degree newDegree = new Degree(universityId,degreeDetails, hash);
		degreeDAO.insert(newDegree);
		return newDegree;
	}
	
	public boolean verifyLogin (Users user, String password) {
		return encoder.matches(password, user.getPassword());
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
			  String url = "http://192.168.100.28:8090/addFile";
				   
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
	

	public boolean verifyDegree(String universityId, Map<String, String> degreeDetails) {
		
		Degree degree = new Degree(universityId,degreeDetails,"tempHash");

		String studentName = degree.getStudentName();
		String gpa = degree.getGpa();
		String graduationYear = degree.getGraduationYear();
		String degreeType = degree.getDegreeType();
		String degreeName = degree.getDegreeName();
		String CNIC = degree.getCNIC();
		
		degree.setHash(hashService.getHash(degreeDetails));

		Degree returnedDegree= degreeDAO.findByFixedFields(universityId, studentName, gpa, graduationYear, degreeType, degreeName,CNIC);
		
		String[] keys = returnedDegree.getDegreeDetails().keySet().toArray(new String[0]);
		for(int i=0;i<keys.length;i++) {
			if(!returnedDegree.getDegreeDetails().get(keys[i]).equals(degree.getDegreeDetails().get(keys[i])) ) {
				return false;
			}
		}
		ResponseStatus rs =verifyDataCryptDegree(degree.getHash(),degree.getUniversityId());
		if(rs == null){
			return false;
		}
		return true;
	}
	
	public ResponseStatus verifyDataCryptDegree(String degreeHash,String universityId){
		try{
	      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("hash", degreeHash);
				  
			  String url = "http://192.168.100.28:8090/verifyFile";
				   
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
				  
			  System.out.println(response.toString());
		      if(response.getStatus().equals("true")){
		    	  Users user = userService.findById(universityId);
		    	  if(user.getPublicAddress().equals(response.getOwner())){
		    		  return response;
		    	  }
		    	  else 
		    		  return null;
		      }
		      else {
		    	  return null;
		      }
		}
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        return null;
	    }
	}
	
}
