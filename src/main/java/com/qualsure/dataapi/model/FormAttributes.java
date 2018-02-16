package com.qualsure.dataapi.model;

public class FormAttributes {

	private String name;		//name of form attributes. studentName, GPA 
	private String validator;
	private String customError;
	
	public FormAttributes() {

	}
	
	public FormAttributes(String name, String validator, String customError) {
		super();
		this.name = name;
		this.validator = validator;
		this.customError = customError;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
