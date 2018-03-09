package com.qualsure.dataapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.DegreeStatus;
import com.qualsure.dataapi.model.University;
import com.qualsure.dataapi.service.DegreeService;
import com.qualsure.dataapi.service.UniversitiesService;

import java.net.URI;
import java.util.List;

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
	private UniversitiesService universitiesService;
	
	@Autowired
	private DegreeService degreeService;

	@GetMapping("/universities/{universityId}/formFields")
	public University getFormFieldsByUniId(@PathVariable String universityId) {
			return universitiesService.findFormFieldsByUniId(universityId);
	}	

	
	@PostMapping("/universities/{universityId}/verifyDegree")
	public DegreeStatus verifyDegreeById(@PathVariable String universityId, @RequestBody Degree degree) {
		
		if(degreeService.findDegreeInDb(universityId, degree) != null)
			return new DegreeStatus("Success");
		else
			return new DegreeStatus("Failed");
		
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hoila";
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
	public ResponseEntity<?> addDegree(@PathVariable String universityId, @RequestBody Degree degree) {
		 Degree newDegree= degreeService.addDegree(universityId, degree);
		 if (degree == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newDegree.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
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
