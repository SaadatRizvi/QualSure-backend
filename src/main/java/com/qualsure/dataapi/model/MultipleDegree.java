package com.qualsure.dataapi.model;

import java.util.List;
import java.util.Map;

public class MultipleDegree {

	private String password;
	private List<Map<String,String>> degreeDetails;
	
	
	public MultipleDegree() {
	}
	public MultipleDegree(String password, List<Map<String, String>> degreeDetails) {
		this.password = password;
		this.degreeDetails = degreeDetails;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Map<String, String>> getDegreeDetails() {
		return degreeDetails;
	}
	public void setDegreeDetails(List<Map<String, String>> degreeDetails) {
		this.degreeDetails = degreeDetails;
	}

}
