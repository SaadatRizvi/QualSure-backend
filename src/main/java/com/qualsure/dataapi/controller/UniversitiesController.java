package com.qualsure.dataapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.model.Universities;
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
public class UniversitiesController {
	
	@Autowired
	private UniversitiesService universitiesService;

	@GetMapping("/universities")
	public List<Universities> getAllUniversities() {
		return universitiesService.getAllUniversities();
	}
	@GetMapping("/universities/{universitiesId}")
	public Universities getUniversities(@PathVariable String universitiesId) {
		return universitiesService.getUniversities(universitiesId);
	}
	//A POST Service should return a status of created (201)
	//when the resource creation is successful.
	@PostMapping("/universities")
	public ResponseEntity<?> addUniversities(@RequestBody Universities universities) {
		 Universities newUniversities= universitiesService.addUniversities(universities);
		 if (universities == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newUniversities.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
	
	@PutMapping("/universities")
	public void updateUniversities(@RequestBody Universities universities) {
		
		 universitiesService.updateUniversities(universities);
	}
	
	@DeleteMapping("/universities/{universitiesId}")
	public void deleteUniversities(@PathVariable String universitiesId) {
		
		 universitiesService.deleteUniversities(universitiesId);
	}
	
	
}
