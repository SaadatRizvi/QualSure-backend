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

import com.qualsure.dataapi.model.DynamicForm;
import com.qualsure.dataapi.service.DynamicFormService;

@RestController
public class DynamicFormController {

	
	@Autowired
	private DynamicFormService dynamicFormService;

	@GetMapping("/formFields")
	public List<DynamicForm> getAllFormFields() {
		return dynamicFormService.getAllFormFields();
	}
	@GetMapping("/formFields/{fieldId}")
	public DynamicForm getFormField(@PathVariable String fieldId) {
		return dynamicFormService.getFormField(fieldId);
	}

	@PostMapping("/formFields")
	public ResponseEntity<?> addFormField(@RequestBody DynamicForm dynamicForm) {
		 DynamicForm newDynamicForm = dynamicFormService.addFormField(dynamicForm);
		 if (dynamicForm == null)
				return ResponseEntity.noContent().build();
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
					"/{id}").buildAndExpand(newDynamicForm.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
	
	@PutMapping("/formFields")
	public void updateDynamicForm(@RequestBody DynamicForm dynamicForm) {
		
		 dynamicFormService.updateDynamicForm(dynamicForm);
	}
	
	@DeleteMapping("/formFields/{fieldId}")
	public void deleteFormField(@PathVariable String fieldId) {
		
		 dynamicFormService.deleteFormField(fieldId);
	}
	
	
}
