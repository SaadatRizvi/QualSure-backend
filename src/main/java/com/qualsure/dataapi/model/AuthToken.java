package com.qualsure.dataapi.model;

public class AuthToken {

    private String token;
    private String location;

    public AuthToken(){

    }

    public AuthToken(String token,String location){
        this.location= location;
    	this.token = token;
    }

    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}