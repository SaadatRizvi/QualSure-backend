package com.qualsure.dataapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users {
	
	@Id
	private String id;
	
	@Indexed(unique=true)
	private String username;
	private String password;
	private List<String> roles;
	private String email;
	private String active;
	private String name;
	private String number;
	private String address;
	private String representativeName;
	private String representativeNumber;
	private String representativeCNIC;
	private byte[] dataCryptPassword;

	
	
	public Users() {
		
	}
	
	
	public Users(String id, String username, String password, List<String> roles, String email, String active,
			String name, String number, String address, String representativeName, String representativeNumber,
			String representativeCNIC, byte[] dataCryptPassword ) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
		this.active = active;
		this.name = name;
		this.number = number;
		this.address = address;
		this.representativeName = representativeName;
		this.representativeNumber = representativeNumber;
		this.representativeCNIC = representativeCNIC;
		this.dataCryptPassword = dataCryptPassword;

	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRepresentativeName() {
		return representativeName;
	}


	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}


	public String getRepresentativeNumber() {
		return representativeNumber;
	}


	public void setRepresentativeNumber(String representativeNumber) {
		this.representativeNumber = representativeNumber;
	}


	public String getRepresentativeCNIC() {
		return representativeCNIC;
	}


	public void setRepresentativeCNIC(String representativeCNIC) {
		this.representativeCNIC = representativeCNIC;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
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
		this.number = user.number;
		this.address = user.address;
		this.representativeName = user.representativeName;
		this.representativeNumber = user.representativeNumber;
		this.representativeCNIC = user.representativeCNIC;
		this.dataCryptPassword = user.dataCryptPassword;

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
	
	public byte[] getDataCryptPassword() {
		return dataCryptPassword;
	}

	public void setDataCryptPassword(byte[] dataCryptPassword) {
		this.dataCryptPassword = dataCryptPassword;
	}
}
