package com.qualsure.dataapi.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.ResponseStatus;
import com.qualsure.dataapi.model.Users;

@Service
public class DegreeService {

	@Autowired	
	private DegreeDAO degreeDAO;

	@Autowired
    private UsersService userService;
	
	public List<Degree> getAllDegrees(){
	
		
		return degreeDAO.findAll();
	}
	
	public Degree getDegree(String universityId, String degreeId) {
	//	return degrees.stream().filter(t -> t.getId() == degreeId).findFirst().get();
		return degreeDAO.findByUniversityIdAndId(universityId, degreeId);
	}

	public Degree addDegree(String universityId, Degree degree) {
		degree.setUniversityId(universityId);
		degreeDAO.insert(degree);
		return degree;
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
	

	public Degree findDegreeInDb(String universityId, Degree degree) {
		String studentName = degree.getStudentName();
		String gpa = degree.getGpa();
		String graduationYear = degree.getGraduationYear();
		String degreeType = degree.getDegreeType();
		String degreeName = degree.getDegreeName();
		return degreeDAO.findByUniversityIdAndStudentNameAndGpaAndGraduationYearAndDegreeTypeAndDegreeName(universityId, studentName, gpa, graduationYear, degreeType, degreeName);
	}
	
	public ResponseStatus verifyDegree(String degreeHash,String universityId){
		try{
	      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("hash", degreeHash);
				  
			  String url = "http://192.168.0.105:8090/verifyFile";
				   
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
