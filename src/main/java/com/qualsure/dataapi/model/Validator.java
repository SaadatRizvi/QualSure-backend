package com.qualsure.dataapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Validators")
public class Validator {
	@Id
	private String id;
	private String name;
	private String regex;
	private String type;

	
	public Validator() {
		
	}
	
	public Validator(String id, String name, String regex, String type) {
		this.id = id;
		this.name = name;
		this.regex = regex;
		this.type = type;
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
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
