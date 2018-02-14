package com.qualsure.dataapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.service.DegreeService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RestController
public class DegreeController {
	
	@Autowired
	private DegreeService degreeService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hoila";
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/degrees")
	public List<Degree> getAllDegrees() {
		return degreeService.getAllDegrees();
	}
	@GetMapping("/degrees/{degreeId}")
	public Degree getDegree(@PathVariable String degreeId) {
		return degreeService.getDegree(degreeId);
	}
	//A POST Service should return a status of created (201)
	//when the resource creation is successful.
	@PostMapping("/degrees")
	public ResponseEntity<?> addDegree(@RequestBody Degree degree) {
		 Degree newDegree= degreeService.addDegree(degree);
		 if (degree == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newDegree.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
	
	@PutMapping("/degrees")
	public void updateDegree(@RequestBody Degree degree) {
		
		 degreeService.updateDegree(degree);
	}
	
	@DeleteMapping("/degrees/{degreeId}")
	public void deleteDegree(@PathVariable String degreeId) {
		
		 degreeService.deleteDegree(degreeId);
	}
	
	
}
