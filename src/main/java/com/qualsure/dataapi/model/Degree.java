package com.qualsure.dataapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Degrees")
public class Degree {

	@Id 
	private String id;
	private String universityId;
	private String studentName;
	private String gpa;
	private String graduationYear;
	private String degreeType;
	private String degreeName;


	
	public Degree() {
		
	}
	
	public Degree(String id, String universityId, String studentName, String gpa, String graduationYear, String degreeType, String degreeName) {
		super();
		this.id = id;
		this.universityId = universityId;
		this.studentName = studentName;
		this.gpa = gpa;
		this.graduationYear = graduationYear;
		this.degreeType = degreeType;
		this.degreeName = degreeName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getGpa() {
		return gpa;
	}


	public void setGpa(String gpa) {
		this.gpa = gpa;
	}


	public String getGraduationYear() {
		return graduationYear;
	}


	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
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



	
}