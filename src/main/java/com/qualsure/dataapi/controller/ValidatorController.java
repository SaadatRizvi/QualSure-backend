package com.qualsure.dataapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.model.Validator;
import com.qualsure.dataapi.service.ValidatorService;

@RestController
public class ValidatorController {

	@Autowired	
	private ValidatorService validatorService;
	
	@GetMapping("/validators")
	public List<Validator> getAllValidators() {
		return validatorService.getAllValidators();
	}
	@GetMapping("/validators/{validatorId}")
	public Validator getValidator(@PathVariable String validatorId) {
		return validatorService.getValidator(validatorId);
	}
	//A POST Service should return a status of created (201)
	//when the resource creation is successful.
	@PostMapping("/validators")
	public ResponseEntity<?> addValidator(@RequestBody Validator validator) {
		 Validator newValidator= validatorService.addValidator(validator);
		 if (validator == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newValidator.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
	
	@PutMapping("/validators")
	public void updateValidator(@RequestBody Validator validator) {
		
		 validatorService.updateValidator(validator);
	}
	
	@DeleteMapping("/validators/{validatorId}")
	public void deleteValidator(@PathVariable String validatorId) {
		
		 validatorService.deleteValidator(validatorId);
	}
	
	
}
