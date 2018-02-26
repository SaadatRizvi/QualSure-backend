package com.qualsure.dataapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.qualsure.dataapi.model.FixedFormAttributes;
import com.qualsure.dataapi.model.FormField;
import org.apache.log4j.Logger;

@RestController
public class FixedFormAttributesController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(FixedFormAttributesController.class);
	
	@GetMapping("/fixedFormAttributes")
	public List<FormField> getAllFixedFormAttributes() {
		return FixedFormAttributes.getFormFields();
	}
	@GetMapping("/fixedFormAttributes/{attributeName}")
	public FormField getFixedFormAttribute(@PathVariable String attributeName) {
//		logger.info("attributeName: "+attributeName+" val: "+
//				FixedFormAttributes.getFormFieldByName(attributeName));
		
		return FixedFormAttributes.getFormFieldByName(attributeName);
	}
}
