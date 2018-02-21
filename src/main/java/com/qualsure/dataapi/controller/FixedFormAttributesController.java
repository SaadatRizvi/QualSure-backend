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

import com.qualsure.dataapi.model.FixedFormAttributes;
import com.qualsure.dataapi.model.FormAttributes;
import com.qualsure.dataapi.service.FixedFormAttributesService;

import org.apache.log4j.Logger;

@RestController
public class FixedFormAttributesController {

	private static Logger logger = Logger.getLogger(FixedFormAttributesController.class);
	
	@GetMapping("/fixedFormAttributes")
	public List<FormAttributes> getAllFixedFormAttributes() {
		return FixedFormAttributes.getFormFields();
	}
	@GetMapping("/fixedFormAttributes/{attributeName}")
	public FormAttributes getFixedFormAttribute(@PathVariable String attributeName) {
//		logger.info("attributeName: "+attributeName+" val: "+
//				FixedFormAttributes.getFormFieldByName(attributeName));
		
		return FixedFormAttributes.getFormFieldByName(attributeName);
	}
}
