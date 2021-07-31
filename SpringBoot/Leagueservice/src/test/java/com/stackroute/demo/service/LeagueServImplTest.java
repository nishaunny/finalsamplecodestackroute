package com.stackroute.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import com.stackroute.demo.exception.LeagueAlreadyExistsException;
import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;
import com.stackroute.demo.repository.leaguerepo;
@SpringBootTest
public class LeagueServImplTest {
	
	@Mock
	private LeagueInfo leagueInfoobj;
	@Mock
	private User userInfoobj;
	@Mock
	private leaguerepo LeagueRepository;
	@InjectMocks
	private LeagueServImpl LeagueServiceImpl;
	private List<LeagueInfo> leaguesList = null;
	Optional<User> options;
	
	 LeagueInfo[] leagueObjects;
	   @BeforeEach
	    public void setUp() throws Exception {

		   leagueObjects = new LeagueInfo[2];

	        leagueInfoobj = new LeagueInfo();
	        
	        leagueInfoobj.setLid("1");
	        leagueInfoobj.setUserName("Raju");
	        leagueInfoobj.setCategory("cricket");
	        leagueInfoobj.setLeagueName("The-county-championship");
	        leagueInfoobj.setTeamName("Sussex");
	        leagueInfoobj.setPoints(32);
	        leagueInfoobj.setRank(3);
	        leagueInfoobj.setCountry("england");

	        leagueObjects[0]=leagueInfoobj;
	        leagueInfoobj.setLid("2");
	        leagueInfoobj.setUserName("Rani");
	        leagueInfoobj.setCategory("football");
	        leagueInfoobj.setLeagueName("Bundesliga");
	        leagueInfoobj.setTeamName("Big Blues");
	        leagueInfoobj.setPoints(32);
	        leagueInfoobj.setRank(3);
	        leagueInfoobj.setCountry("India");
	     
	        leagueObjects[1]=leagueInfoobj;
	        
	        leaguesList = new ArrayList<>();
	        leaguesList.add(leagueInfoobj);

	        userInfoobj = new User();

	        userInfoobj.setUserName("Raju");
	        userInfoobj.setLeagues(leaguesList);

	        options = Optional.of(userInfoobj);
	        
	    }


	    @Test
	    public void addLeagueInfoSuccess() throws LeagueAlreadyExistsException {
	        when(LeagueRepository.insert((User) any())).thenReturn(userInfoobj);
	        boolean status = LeagueServiceImpl.addLeagueDetails("Raju",leagueObjects);
	        assertEquals(true, status);
	    }

	    @Test
	    public void addLeagueInfoFailure() throws LeagueAlreadyExistsException {

	        when(LeagueRepository.insert(userInfoobj)).thenReturn(null);
	        boolean status = LeagueServiceImpl.addLeagueDetails("Raju",leagueObjects);
	        assertEquals(false, status);
	     }

}
