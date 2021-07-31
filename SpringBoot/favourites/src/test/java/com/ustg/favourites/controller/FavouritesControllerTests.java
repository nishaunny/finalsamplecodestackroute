package com.ustg.favourites.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ustg.favourites.model.Favourites;
import com.ustg.favourites.services.FavouritesService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class FavouritesControllerTests {
	private MockMvc mockMvc;
	
	private Favourites favourites;

	private List<Favourites> favouritesList;

	@Mock
	private FavouritesService favouriteService;

	@InjectMocks
	private FavouritesController favouriteController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();
		favourites = new Favourites();
		favourites.setFavouriteId(1);
		favourites.setTeamName("India");
		favourites.setUserName("Manmadha");

		favouritesList = new ArrayList<>();
		favouritesList.add(favourites);
	}

	@Test
	public void addFavouriteSuccess() throws Exception {

		when(favouriteService.addFavourite(any())).thenReturn(true);
		System.out.println("test.............");
		mockMvc.perform(post("/api/favourites/addFavourite").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(favourites))).andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void addFavouriteFailure() throws Exception {
		when(favouriteService.addFavourite(any())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/favourites/addFavourite")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourites)))
				.andExpect(MockMvcResultMatchers.status().isConflict()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteFavouriteSuccess() throws Exception {

		when(favouriteService.deleteFavourite("Manmadha", favourites.getTeamName())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/favourites/deleteFavourite/Manmadha/India")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteFavouriteFailure() throws Exception {

		when(favouriteService.deleteFavourite("Manmadha", favourites.getTeamName())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/favourites/deleteFavourite/Manmadha/India")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getAllFavouriteByUserIdSuccess() throws Exception {
		when(favouriteService.getFavourites("Manmadha")).thenReturn(favouritesList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/favourites/getFavourites/Manmadha")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getAllFavouriteByUserIdFailure() throws Exception {
		when(favouriteService.getFavourites("Manmadha")).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/favourites/getFavourites/Manmadha")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
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
