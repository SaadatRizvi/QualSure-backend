package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universities {

	private String id;
	private String name;
	private List<FormAttributes> formFields;
	
	
	Universities() {
		
	}

	public Universities(String id, String name, List<FormAttributes> formFields) {

		this.id = id;
		this.name = name;
		this.formFields = new ArrayList<FormAttributes>(Arrays.asList(
				 new FormAttributes("StudentName",  "[A-Za-z ]+", "Username is incorrect"), 
				 new FormAttributes("GPA",  "[0-4].?[0-9][0-9]", "GPA is incorrect"),
				 new FormAttributes("DegreeType",  "[A-Z.]+", "DegreeType is incorrect"),
				 new FormAttributes("DegreeName",  "[A-Z]", "DegreeName is incorrect")));
		
		for (int i=0; i<formFields.size(); i++) {
			this.formFields.add(formFields.get(i));
		}
	
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

	public List<FormAttributes> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormAttributes> formFields) {
		this.formFields = formFields;
	}

	
	
	
}
