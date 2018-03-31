package com.qualsure.dataapi.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Degrees")
public class Degree {

	@Id 
	private String id;
	private String universityId;
	private Map<String, String> degreeDetails;
	private String hash;


	
	public Degree() {
		
	}
	public Degree( String universityId, Map<String, String> degreeDetails, String hash) {
		this.universityId = universityId;
		this.degreeDetails = degreeDetails;
		this.hash = hash;
	}

	

	public Degree(String id, String universityId, Map<String, String> degreeDetails, String hash) {
		this.id = id;
		this.universityId = universityId;
		this.degreeDetails = degreeDetails;
		this.hash = hash;
	}



	public String getUniversityId() {
		return universityId;
	}
	
	public String getStudentName() {
		return this.degreeDetails.get("studentName");
		
	}
	public String getGpa() {
		return this.degreeDetails.get("gpa");
		
	}
	public String getGraduationYear() {
		return this.degreeDetails.get("graduationYear");
		
	}
	public String getDegreeType() {
		return this.degreeDetails.get("degreeType");
		
	}
	public String getDegreeName() {
		return this.degreeDetails.get("degreeName");
		
	}
	public String getCNIC() {
		return this.degreeDetails.get("CNIC");
		
	}


	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Map<String, String> getDegreeDetails() {
		return degreeDetails;
	}



	public void setDegreeDetails(Map<String, String> degreeDetails) {
		this.degreeDetails = degreeDetails;
	}



	public String getHash() {
		return hash;
	}



	public void setHash(String hash) {
		this.hash = hash;
	}
	

}