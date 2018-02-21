package com.qualsure.dataapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Degrees")
public class Degree {

	@Id 
	private String id;
	private String firstName;
	private String lastName;
	private String degreeType;
//	private Universities universities;

	
	public Degree() {
		
	}
	
	
	
	
public Degree(String id, String firstName, String lastName, String degreeType){ //, Universities universities) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.degreeType = degreeType;
//		this.universities = universities;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
/*	public Universities getUniversities() {
		return universities;
	}

	public void setUniversities(Universities universities) {
		this.universities = universities;
	}
*/
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	
}
