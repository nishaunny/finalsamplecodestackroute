package com.stackroute.demo.service;

import com.stackroute.demo.exception.LeagueAlreadyExistsException;
import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;

public interface LeagueService {
	
	public boolean addLeagueDetails(String userName,LeagueInfo league[]) throws LeagueAlreadyExistsException;

}
