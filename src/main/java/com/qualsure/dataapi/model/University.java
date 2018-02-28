package com.qualsure.dataapi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "University")
public class University {

	@Id
	private String id;
	private String accountId;
	private String name;
	private List<FormField> formFields;
	private String firstTime;



	University() {
		
	}

	public University(String accountId, String name, String firstTime, List<FormField> formFields) {
		
		this.accountId=accountId;
		this.name = name;
		this.firstTime = firstTime;
		this.formFields=new ArrayList<FormField>();
		for (int i=0; i<formFields.size(); i++) {
			this.formFields.add(formFields.get(i));
		}
	
	}
	

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

