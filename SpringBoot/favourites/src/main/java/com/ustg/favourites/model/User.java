package com.ustg.favourites.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	@Id
	String userName;
	List<Favourites> favourites;
	
	public User(String userId, String userName, List<Favourites> favourites) {
		this.userName = userName;
		this.favourites = favourites;
	}
	
	public User() {
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Favourites> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<Favourites> favourites) {
		this.favourites = favourites;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", favourites=" + favourites + "]";
	}
	
}
