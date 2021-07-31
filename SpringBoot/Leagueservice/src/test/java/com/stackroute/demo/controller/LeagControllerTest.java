package com.stackroute.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;
import com.stackroute.demo.service.LeagueService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LeagControllerTest {

	private MockMvc mockMvc;
	@MockBean
	private LeagueInfo leagueInfoobj;
	@MockBean
	private User userInfoobj;
	@MockBean
	private LeagueService leagueService;
	
	@InjectMocks
	private LeagController leagueController;
	
	private List<LeagueInfo> leaguesList;
	
	
	@BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(leagueController).build();
        
        
        leagueInfoobj = new LeagueInfo();
        
        leagueInfoobj.setLid("2");
        leagueInfoobj.setCategory("cricket");
        leagueInfoobj.setLeagueName("The-county-championship");
        leagueInfoobj.setPoints(32);
        leagueInfoobj.setRank(3);
        leagueInfoobj.setTeamName("Countyshire");
        leagueInfoobj.setUserName("Raju");
        leagueInfoobj.setCountry("england");
     

        leaguesList = new ArrayList<>();
        leaguesList.add(leagueInfoobj);

      

    }


    @Test
    public void addLeagueSuccess() throws Exception {
        when(leagueService.addLeagueDetails(any(),any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/League/addLeagueinfo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void addLeagueFailure() throws Exception {
        when(leagueService.addLeagueDetails(any(),any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/League/addLeagueinfo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	
}
