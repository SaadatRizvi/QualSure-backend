package com.qualsure.dataapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.qualsure.dataapi.model.FormField;
import com.qualsure.dataapi.service.FormFieldService;

//import org.apache.log4j.Logger;

@RestController
public class FormFieldController {

//	private static Logger logger = Logger.getLogger(FormFieldController.class);
	
	@Autowired
	private FormFieldService formFieldService;
	
	@GetMapping("/fixedFormFields")
	public List<FormField> getAllFixedFormFields() {
		return formFieldService.getAllFormFields();
	}
	@GetMapping("/fixedFormFields/{fieldName}")
	public FormField getFixedFormField(@PathVariable String fieldName) {
//		logger.info("attributeName: "+attributeName+" val: "+
//				FixedFormAttributes.getFormFieldByName(attributeName));
		
		return formFieldService.getFormFieldByName(fieldName);
	}
}
