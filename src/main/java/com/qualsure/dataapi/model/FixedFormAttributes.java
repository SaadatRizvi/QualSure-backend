//package com.qualsure.dataapi.model;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import com.qualsure.dataapi.controller.FormAttributesController;
//
//
//public class FixedFormAttributes {
//	private static Logger logger = Logger.getLogger(FormAttributesController.class);
//
//	
//	private static List<FormAttributes> formFields = new ArrayList<FormAttributes>(Arrays.asList(
//			 new FormAttributes("StudentName",  "[A-Za-z ]+", "Username is incorrect", "String"), 
//			 new FormAttributes("GPA",  "[0-4].?[0-9][0-9]", "GPA is incorrect", "Number"),
//			 new FormAttributes("DegreeType",  "[A-Z.]+", "DegreeType is incorrect", "String"),
//			 new FormAttributes("DegreeName",  "[A-Z]", "DegreeName is incorrect", "String")));
//
//	public static List<FormAttributes> getFormFields() {
//		return formFields;
//	}
//
//	public static FormAttributes getFormFieldByName(String fieldName) {
////		logger.info("getFormFieldByName: "+fieldName);
//		for(FormAttributes attr: FixedFormAttributes.formFields){
////			logger.info(attr.getName());
//
//			if(attr.getName().equals(fieldName)){
//				return attr;
//			}
//		}
//		return null;
//	}
//
//}
