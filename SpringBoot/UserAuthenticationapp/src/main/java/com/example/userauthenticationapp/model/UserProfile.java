package com.example.userauthenticationapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserProfile {

	
	@Id
	String username;
	String password;
	private String firstName;
	private String lastName;
	int age;
	


	@JsonIgnore
	private String cPassword;
	
	public UserProfile() {
		super();
	}

	public UserProfile(String username, String firstName, String lastName, String password, String cPassword) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.cPassword = cPassword;
		
	}
	
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

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	   
    @Override
    public String toString() {
		return "UserProfile [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+  "]";
	}  
 

}