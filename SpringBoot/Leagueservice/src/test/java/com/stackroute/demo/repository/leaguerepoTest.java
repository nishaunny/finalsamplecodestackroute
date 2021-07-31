package com.stackroute.demo.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;


@ExtendWith(SpringExtension.class)
@DataMongoTest
public class leaguerepoTest {
	
    @Autowired
    private leaguerepo leagueRepository;
    
    private LeagueInfo leagueinfo;
    private User userinfo = new User();
    
    private List<LeagueInfo> leagueslist = null;
    
    @BeforeEach
    public void setUp() throws Exception {

    	leagueinfo = new LeagueInfo();
    	
    	leagueinfo.setLid("1");
    	leagueinfo.setCategory("cricket");
    	leagueinfo.setCountry("england");
    	leagueinfo.setLeagueName("The-county-championship");
    	leagueinfo.setTeamName("Sussex");
    	leagueinfo.setPoints(12);
    	leagueinfo.setRank(2);
    	
    	leagueslist = new ArrayList<>();
    	leagueslist.add(leagueinfo);
    	
    	userinfo.setUserName("Raju");
    	userinfo.setLeagues(leagueslist);
    	
    }

    @AfterEach
    public void tearDown() throws Exception {
    	leagueRepository.deleteAll();
    }

    @Test
    public void AddLeagueInfoTest() {
    	leagueRepository.insert(userinfo);
        List<LeagueInfo> allLeagues = leagueRepository.findById("Raju").get().getLeagues();
        assertThat(leagueslist.get(0).getLid(), is(allLeagues.get(0).getLid()));
    }
    
    @Test
	public void deleteLeagueInfoTest() {
		leagueRepository.insert(userinfo);
		List<LeagueInfo> allLeagues = leagueRepository.findById("Raju").get().getLeagues();
		assertThat(leagueslist.get(0).getLid(), is(allLeagues.get(0).getLid()));
		Iterator<LeagueInfo> iterator = allLeagues.listIterator();
		while (iterator.hasNext()) {
			leagueinfo = iterator.next();
			if (leagueinfo.getLid().equalsIgnoreCase("1"))
				iterator.remove();
		}

		userinfo.setLeagues(allLeagues);
		leagueRepository.save(userinfo);

		allLeagues = leagueRepository.findById("Raju").get().getLeagues();

		assertThat(true, is(allLeagues.isEmpty()));
	}

	@Test
	public void updateLeagueTest() {
		leagueRepository.insert(userinfo);
		List<LeagueInfo> allLeagues = leagueRepository.findById("Raju").get().getLeagues();
		assertThat(leagueslist.get(0).getLid(), is(allLeagues.get(0).getLid()));
		Iterator<LeagueInfo> iterator = allLeagues.listIterator();
		while (iterator.hasNext()) {
			leagueinfo = iterator.next();
			if (leagueinfo.getLid().equalsIgnoreCase("1"))
				leagueinfo.setTeamName("Nottinghamshire");
			
		}
		userinfo.setLeagues(allLeagues);
		leagueRepository.save(userinfo);

		allLeagues = leagueRepository.findById("Raju").get().getLeagues();
		assertThat("Nottinghamshire", is(allLeagues.get(0).getTeamName()));
	}
    @Test
    public void getAllLeaguesByUserId() {

    	leagueRepository.insert(userinfo);
        List<LeagueInfo> allLeagues = leagueRepository.findById("Raju").get().getLeagues();
        assertThat(allLeagues.size(),is(1));
    }

}
