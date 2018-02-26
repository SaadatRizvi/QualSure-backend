package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Universities")
public class Universities {

	private String id;
	private String name;
	private List<FormAttributes> formFields;
	private String firstTime;



	Universities() {
		
	}

	public Universities(String id, String name, String firstTime, List<FormAttributes> formFields) {
		
		this.id = id;
		this.name = name;
		this.firstTime = firstTime;
/*		this.formFields = new ArrayList<FormAttributes>(Arrays.asList(
				 new FormAttributes("StudentName",  Arrays.asList(alphaReq), "Username is incorrect", "String"), 
				 new FormAttributes("GPA",  Arrays.asList(floatingReq), "GPA is incorrect", "Number"),
				 new FormAttributes("DegreeType",  Arrays.asList(alphaReq), "DegreeType is incorrect", "String"),
				 new FormAttributes("DegreeName",  Arrays.asList(alpha), "DegreeName is incorrect", "String")));
*/		
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

	public List<FormAttributes> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormAttributes> formFields) {
		this.formFields = formFields;
	}

	
	
	
}

