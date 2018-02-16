package com.qualsure.dataapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DynamicFormData")
public class DynamicForm {
	
	@Id
	private String id;
	private String name;
	private String degreeType;
	private String degreeName;
	private String graduationDate;
	
	
	public DynamicForm() {
		
	}
	
	public DynamicForm(String id, String name, String degreeType, String degreeName, String graduationDate) {
		this.id = id;	
		this.name = name;	
		this.degreeName = degreeName;
		this.degreeType = degreeType;
		this.graduationDate = graduationDate;
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


	public String getDegreeName() {
		return degreeName;
	}


	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}


	public String getDegreeType() {
		return degreeType;
	}


	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}


	public String getGraduationDate() {
		return graduationDate;
	}


	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	
	

}
