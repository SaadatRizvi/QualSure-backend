package com.qualsure.dataapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {

	private String status;
	private Message message;
	private String errorMessage;
	

	public Message getMessage() {
		return message;
	}
	
	public String getPublicAddress() {
		return message.getPublicAddress();
	}
	public String getOwner() {
		return message.getOwner();
	}
	public String getTimeStamp() {
		return message.getTimeStamp();
	}
	public void setMessage(Message message) {
		this.message = message;
	}


	public ResponseStatus() {
	}

	
	public ResponseStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ResponseStatus [status=" + status + ", message=" + message + "]";
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
