package com.stackroute.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	
	@Id
    String userName;
    List<LeagueInfo> leagues;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<LeagueInfo> getLeagues() {
		return leagues;
	}
	public void setLeagues(List<LeagueInfo> leagues) {
		this.leagues = leagues;
	}
	
	public User() {}
	public User(String userName, List<LeagueInfo> leagues) {

		this.userName = userName;
		this.leagues = leagues;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", leagues=" + leagues + "]";
	}
    
    

}
