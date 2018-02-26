package com.qualsure.dataapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.qualsure.dataapi.model.FormField;
import com.qualsure.dataapi.service.FormAttributesService;

//import org.apache.log4j.Logger;

@RestController
public class FormAttributesController {

//	private static Logger logger = Logger.getLogger(FormAttributesController.class);
	
	@Autowired
	private FormAttributesService formAttributesService;
	
	@GetMapping("/fixedFormAttributes")
	public List<FormField> getAllFixedFormAttributes() {
		return formAttributesService.getAllFormAttributes();
	}
	@GetMapping("/fixedFormAttributes/{attributeName}")
	public FormField getFixedFormAttribute(@PathVariable String attributeName) {
//		logger.info("attributeName: "+attributeName+" val: "+
//				FixedFormAttributes.getFormFieldByName(attributeName));
		
		return formAttributesService.getFormAttributesByName(attributeName);
	}
}
