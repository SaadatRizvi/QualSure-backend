package com.qualsure.dataapi.model;

import java.util.List;

public class University {

	private String id;
	private String name;
	private List<FormField> formFields;
	private String firstTime;



	University() {
		
	}

	public University(String id, String name, String firstTime, List<FormField> formFields) {
		
		this.id = id;
		this.name = name;
		this.firstTime = firstTime;
		for (int i=0; i<formFields.size(); i++) {
			this.formFields.add(formFields.get(i));
		}
	
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	
	
	
}

