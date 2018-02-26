package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import com.qualsure.dataapi.DbSeeder;

@Document(collection = "FormFields")
public class FormField {

	private String name;		//name of form attributes. studentName, GPA 
	private List<Validator> validators;
	private String customError;
	private String attributeType;
	
	@SuppressWarnings("unused")
	@Autowired
	private static Logger logger = Logger.getLogger(DbSeeder.class);
	
	public FormField() {

	}
	
	public FormField(String name, List<Validator> validators, String customError, String attributeType) {
		this.name = name;
		this.validators=new ArrayList<Validator>();
		if(!validators.isEmpty()) {
			for (int i=0; i<validators.size(); i++) {
				this.validators.add(validators.get(i));
			}
		}
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
	

	
	public List<Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	public String getCustomError() {
		return customError;
	}
	
	public void setCustomError(String customError) {
		this.customError = customError;
	}

}
