package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class University {

	private String id;
	private String name;
	private List<FormField> formFields;
	private String firstTime;



	University() {
		
	}

	public University(String id, String name, String firstTime, List<FormField> formFields) {
		
		Validator alpha = new Validator("1","alpha", "[a-zA-z]*","text");
		Validator alphaReq = new Validator("2","alphaReq", "[a-zA-z]+","text");

		Validator alphaNum = new Validator("3","alphaNum", "[a-zA-z0-9]*","text");
		Validator alphaNumReq = new Validator("4","alphaNumReq", "[a-zA-z0-9]+","text");

		Validator num = new Validator("5","num", "[0-9]*","number");
		Validator numReq = new Validator("6","numReq", "[0-9]+","number");
		
		Validator floating = new Validator("7","float", "[0-9]*[.]?[0-9]*","number");
		Validator floatingReq = new Validator("8","floatReq", "[0-9]*[.]?[0-9]+","number");


		this.id = id;
		this.name = name;
		this.firstTime = firstTime;
		this.formFields = new ArrayList<FormField>(Arrays.asList(
				 new FormField("StudentName",  Arrays.asList(alphaReq), "Username is incorrect", "String"), 
				 new FormField("GPA",  Arrays.asList(floatingReq), "GPA is incorrect", "Number"),
				 new FormField("DegreeType",  Arrays.asList(alphaReq), "DegreeType is incorrect", "String"),
				 new FormField("DegreeName",  Arrays.asList(alpha), "DegreeName is incorrect", "String")));
		
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

