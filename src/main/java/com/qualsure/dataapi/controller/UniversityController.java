package com.qualsure.dataapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.DbSeeder;
import com.qualsure.dataapi.dao.UniversityDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.MultipleDegree;
import com.qualsure.dataapi.model.NetworkConfig;
import com.qualsure.dataapi.model.ResponseStatus;
import com.qualsure.dataapi.model.University;
import com.qualsure.dataapi.model.Users;
//import com.qualsure.dataapi.service.CacheService;
import com.qualsure.dataapi.service.DegreeService;
import com.qualsure.dataapi.service.UniversitiesService;
import com.qualsure.dataapi.service.UsersService;


import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UniversityController {

	@Autowired
	private static Logger logger = Logger.getLogger(DbSeeder.class);
	
	@Autowired
	private UniversitiesService universitiesService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private UsersService userService;


	@GetMapping("/universities/{universityId}/formFields")
	public University getFormFieldsByUniId(@PathVariable String universityId) {
			return universitiesService.findFormFieldsByUniId(universityId);
	}	
	
	@PostMapping("/universities/{universityId}/verifyDegree")
	public  Map<String, String> verifyDegreeById(@PathVariable String universityId, @RequestBody Map<String, String> degreeDetails) {
		

//		Degree degree = new Degree(universityId,degreeDetails,"tempHash");
		System.out.println("in verify");
		Map<String,String> hm=new HashMap<String, String>();  

		Map<String, String> res=degreeService.verifyDegree(universityId, degreeDetails);
		if(res.get("status").equals("true")) {
			System.out.println(res.get("degreeId"));
			hm.put("status", "Success");
			hm.put("degreeId", res.get("degreeId") );
			
		}
		else{
			hm.put("status", "false");
			hm.put("errorMessage", res.get("errorMessage") );
		}
		System.out.println("2");
		return hm;
		}
		
	
	
	@GetMapping("/hello")
	public String hello() {
		return "Hoila";
	}
	
	@GetMapping("/universities/{universityId}/getPublicAddress")
	public  Map<String, String> getPublicAddress(@PathVariable String universityId) {
		Users user= userService.findById(universityId);
		if(user == null) return Collections.singletonMap("publicAddress", "UniversityIdNull" );
		if(user.getPublicAddress().isEmpty()) return Collections.singletonMap("publicAddress", "PublicAddressNotFound" );
		
		return Collections.singletonMap("publicAddress", user.getPublicAddress());
	}
	@GetMapping("/universities/{universityId}/getAccountBalance")
	public   Map<String, String> getAccountBalance(@PathVariable String universityId) {
		ResponseStatus response= userService.getAccountBalance(universityId);
		return Collections.singletonMap("accountBalance", response.getOwner());
	}
	//@PreAuthorize("hasAnyRole('ADMIN')")
	
	@GetMapping("universities/{universityId}/degrees/{degreeId}")
	public Degree getDegree(@PathVariable String universityId, @PathVariable String degreeId) {
		return degreeService.getDegree(universityId, degreeId);
	}
	
/*	@GetMapping("/universities/{universityId}/degrees/{degreeId}")
	public Degree getOneByUniIdAndDegreeId(@PathVariable("universityId") String universityId, @PathVariable("degreeId") String degreeId) {
		return degreeService.getDegree(universityId, degreeId);
	}
	*/
	
	//A POST Service should return a status of created (201)
	//when the resource creation is successful.
	@PostMapping("universities/{universityId}/degrees")
	public Map<String, String> addDegree(@PathVariable String universityId, @RequestBody Map<String, String> responseObj) {
			
		String password = responseObj.get("password");
		responseObj.remove("password");
		System.out.println(password);
		Map<String, String> response= degreeService.addDegree(universityId, password, responseObj);
		
//		 if (response.get("status").equals("false"))
//				return response;	
						
//		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
//					"/{id}").buildAndExpand(response.get("degreeId")).toUri();

			return response;
		 
	}
	
	
	
	@PostMapping("universities/{universityId}/multipleDegrees")
	public Map<String, String> multipleAddDegree(@PathVariable String universityId, @RequestBody MultipleDegree responseObj) {
					
		Map<String, String> response= degreeService.addMultipleDegree(universityId, responseObj);
			return response;
		 
	}
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/universities/{universityId}/degrees")
	public List<Degree> getDegreesByUniId(@PathVariable String universityId) {
			return degreeService.findByUniId(universityId);
	}


	@GetMapping("/universities/names")
	public List<University> getAllUniversitiesNames() {
		return universitiesService.getAllUniversitiesNames();
	}
	
	
	@GetMapping("/universities")
	public List<University> getAllUniversities() {
		return universitiesService.getAllUniversities();
	}
	@GetMapping("/universities/{universitiesId}")
	public University getUniversities(@PathVariable String universitiesId) {
		return universitiesService.getUniversities(universitiesId);
	}
	//A POST Service should return a status of created (201)
	//when the resource creation is successful.
	@PostMapping("/universities")
	public ResponseEntity<?> addUniversities(@RequestBody University university) {
		 University newUniversities= universitiesService.addUniversities(university);
		 if (university == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newUniversities.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
	
	@PutMapping("/universities")
	public void updateUniversities(@RequestBody University university) {
		
		 universitiesService.updateUniversities(university);
	}
	
	@DeleteMapping("/universities/{universitiesId}")
	public void deleteUniversities(@PathVariable String universitiesId) {
		
		 universitiesService.deleteUniversities(universitiesId);
	}
	
	
}
