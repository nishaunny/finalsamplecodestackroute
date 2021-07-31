package com.ustg.favourites.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ustg.favourites.exceptions.FavouriteAlreadyExistException;
import com.ustg.favourites.exceptions.FavouriteNotFoundException;
import com.ustg.favourites.exceptions.UserNotFoundException;
import com.ustg.favourites.model.Favourites;
import com.ustg.favourites.model.User;
import com.ustg.favourites.repository.FavouritesRepository;
import com.ustg.favourites.services.FavouritesServiceImpl;

@SpringBootTest
public class FavouritesServiceTest {
//	@Mock
	private Favourites favourites;
//	@Mock
	private User user;
	@Mock
	private FavouritesRepository favouritesRepo;
	@InjectMocks
	private FavouritesServiceImpl favouriteServiceImpl;
	Optional<User> options;

	private List<Favourites> favouriteList = null;

	@BeforeEach
	public void setUp() throws Exception {

		favourites = new Favourites();
		favourites.setFavouriteId(0);
		favourites.setTeamName("India");
		favourites.setUserName("Manmadha");

		favouriteList = new ArrayList<>();
		favouriteList.add(favourites);

		user = new User();
		user.setUserName("Manmadha");
		user.setFavourites(favouriteList);
		options = Optional.of(user);
	}

	@Test
	public void addFavouriteSuccess() throws FavouriteAlreadyExistException {
		when(favouritesRepo.insert((User) any())).thenReturn(user);
		boolean status = favouriteServiceImpl.addFavourite(favourites);
		assertEquals(true, status);
	}

	@Test
	public void addFavouriteFailure() throws FavouriteAlreadyExistException {

		when(favouritesRepo.insert(user)).thenReturn(null);
		boolean status = favouriteServiceImpl.addFavourite(favourites);
		assertEquals(false, status);
	}

	@Test
	public void getAllFavouritesByUserId() throws UserNotFoundException {
		when(favouritesRepo.findById("Manmadha")).thenReturn(options);
		List<Favourites> favouritelist1 = favouriteServiceImpl.getFavourites("Manmadha");
		assertEquals(favouriteList, favouritelist1);
	}

	@Test
	public void deleteFavouritesSuccess() throws UserNotFoundException, FavouriteNotFoundException {
		when(favouritesRepo.findById(user.getUserName())).thenReturn(options);
		when(favouritesRepo.save(user)).thenReturn(user);
		boolean flag = favouriteServiceImpl.deleteFavourite("Manmadha", favourites.getTeamName());
		assertEquals(true, flag);
	}

	@Test
	public void deleteNewsFailure() {
		when(favouritesRepo.findById(user.getUserName())).thenReturn(null);
		when(favouritesRepo.save(user)).thenReturn(user);

		assertThrows(NullPointerException.class, () -> {
			favouriteServiceImpl.deleteFavourite("Manmadha", favourites.getTeamName());
		});
	}
}
