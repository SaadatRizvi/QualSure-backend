package com.qualsure.dataapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {

	private String status;
	private String token;
	
	
	public ResponseStatus() {
	}

	
	public ResponseStatus(String status) {
		this.status = status;
	}
	public ResponseStatus(String status,String token) {
		this.status = status;
		this.token = token;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ResponseStatus [status=" + status + ", token=" + token + "]";
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
}
