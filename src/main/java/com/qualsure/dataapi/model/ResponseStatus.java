package com.qualsure.dataapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {

	private String status;

	
	public ResponseStatus() {
	}

	
	public ResponseStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ResponseStatus [status=" + status + "]";
	}
	
}
