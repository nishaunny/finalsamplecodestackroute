package com.ustg.favourites.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favourites {

	@Id
	int favouriteId;
	String teamName;
	String userName;
	
	public Favourites(int favouriteId, String teamName, String userName) {
		this.favouriteId = favouriteId;
		this.teamName = teamName;
		this.userName = userName;
	}
	public Favourites() {}
	
	public int getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(int favouriteId) {
		this.favouriteId = favouriteId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Favourites [favouriteId=" + favouriteId + ", teamName=" + teamName + ", userName=" + userName + "]";
	}
	
}
