package com.ustg.favourites.repository;

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

import com.ustg.favourites.model.Favourites;
import com.ustg.favourites.model.User;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class FavouritesRepositoryTests {

	@Autowired
	private FavouritesRepository favouritesRepo;

	private User user = new User();
	private Favourites favourites;

	private List<Favourites> favouriteList = null;

	@BeforeEach
	public void setUp() throws Exception {

		favourites = new Favourites();
		favourites.setFavouriteId(0);
		favourites.setTeamName("India");
		favourites.setUserName("Manmadha");

		favouriteList = new ArrayList<>();
		favouriteList.add(favourites);

		user.setUserName("Manmadha");
		user.setFavourites(favouriteList);
	}

	@AfterEach
	public void tearDown() throws Exception {
		favouritesRepo.deleteAll();
	}

	@Test
	public void AddFavouriteTest() {
		favouritesRepo.insert(user);
		List<Favourites> allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();
		assertThat(favouriteList.get(0).getFavouriteId(), is(allFavourites.get(0).getFavouriteId()));
	}

	@Test
	public void deleteFavouriteTest() {
		favouritesRepo.insert(user);
		List<Favourites> allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();
		assertThat(favouriteList.get(0).getFavouriteId(), is(allFavourites.get(0).getFavouriteId()));
		Iterator<Favourites> iterator = allFavourites.listIterator();
		while (iterator.hasNext()) {
			favourites = iterator.next();
			if (favourites.getFavouriteId() == 0)
				iterator.remove();
		}

		user.setFavourites(allFavourites);
		favouritesRepo.save(user);

		allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();

		assertThat(true, is(allFavourites.isEmpty()));
	}

	@Test
	public void updateFavouriteTest() {
		favouritesRepo.insert(user);
		List<Favourites> allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();
		assertThat(favouriteList.get(0).getFavouriteId(), is(allFavourites.get(0).getFavouriteId()));
		Iterator<Favourites> iterator = allFavourites.listIterator();
		while (iterator.hasNext()) {
			favourites = iterator.next();
			if (favourites.getFavouriteId() == 0)
				favourites.setTeamName("Australia");
		}
		user.setFavourites(allFavourites);
		favouritesRepo.save(user);
		allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();
		assertThat("Australia", is(allFavourites.get(0).getTeamName()));
	}

	@Test
	public void getAllFavouritesByUserId() {

		favouritesRepo.insert(user);
		List<Favourites> allFavourites = favouritesRepo.findById("Manmadha").get().getFavourites();
		assertThat(allFavourites.size(), is(1));
	}

}
