package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.qualsure.dataapi.controller.FixedFormAttributesController;


public class FixedFormAttributes {
	private static Logger logger = Logger.getLogger(FixedFormAttributesController.class);

	
	private static List<FormField> formFields = new ArrayList<FormField>(Arrays.asList(
			 new FormField("StudentName",  "[A-Za-z ]+", "Username is incorrect", "String"), 
			 new FormField("GPA",  "[0-4].?[0-9][0-9]", "GPA is incorrect", "Number"),
			 new FormField("DegreeType",  "[A-Z.]+", "DegreeType is incorrect", "String"),
			 new FormField("DegreeName",  "[A-Z]", "DegreeName is incorrect", "String")));

	public static List<FormField> getFormFields() {
		return formFields;
	}

	public static FormField getFormFieldByName(String fieldName) {
//		logger.info("getFormFieldByName: "+fieldName);
		for(FormField attr: FixedFormAttributes.formFields){
//			logger.info(attr.getName());

			if(attr.getName().equals(fieldName)){
				return attr;
			}
		}
		return null;
	}

}
