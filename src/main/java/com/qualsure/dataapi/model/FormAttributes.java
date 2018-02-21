package com.qualsure.dataapi.model;

public class FormAttributes {

	private String name;		//name of form attributes. studentName, GPA 
	private String validator;
	private String customError;
	private String attributeType;
	
	public FormAttributes() {

	}
	
	public FormAttributes(String name, String validator, String customError, String attributeType) {
		this.name = name;
		this.validator = validator;
		this.customError = customError;
		this.attributeType = attributeType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttributeType() {
		return attributeType;
	}
	
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	
	public String getValidator() {
		return validator;
	}
	
	public void setValidator(String validator) {
		this.validator = validator;
	}
	
	public String getCustomError() {
		return customError;
	}
	
	public void setCustomError(String customError) {
		this.customError = customError;
	}

}
