package com.qualsure.dataapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {
	
	@Id
	private String id;
	private String username;
	private String password;
	private List<String> roles;
	private String email;
	private String active;
	private String name;
	
	public Users() {
		
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Users(String id, String username, String password, List<String> roles, String email, String active, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
		this.active = active;
		this.name = name;
	}


	public Users(Users user) {
		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.roles = user.roles;
		this.email = user.email;
		this.active = user.active;
		this.name = user.name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
