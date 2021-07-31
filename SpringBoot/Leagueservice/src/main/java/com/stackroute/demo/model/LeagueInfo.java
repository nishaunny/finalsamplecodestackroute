package com.stackroute.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class LeagueInfo {
	
	@Id
	String Lid;
	String userName;
	String leagueName;
	String country;
	String teamName;
	int points;
	int rank;

	String Category;
	

	public String getLid() {
		return Lid;
	}

	public void setLid(String lid) {
		Lid = lid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}




	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public LeagueInfo(String lid, String userName, String leagueName, String country, String teamName, int points,
			int rank, String year, String category) {
		
		Lid = lid;
		this.userName = userName;
		this.leagueName = leagueName;
		this.country = country;
		this.teamName = teamName;
		this.points = points;
		this.rank = rank;
	
		Category = category;
	}
	
	public LeagueInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LeagueInfo [Lid=" + Lid + ", userName=" + userName + ", leagueName=" + leagueName + ", country="
				+ country + ", teamName=" + teamName + ", points=" + points + ", rank=" + rank + ", "
				+ ", Category=" + Category + "]";
	}
	

	
}
