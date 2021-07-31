package com.ustg.favourites.services;

import java.util.List;

import com.ustg.favourites.exceptions.FavouriteAlreadyExistException;
import com.ustg.favourites.exceptions.FavouriteNotFoundException;
import com.ustg.favourites.exceptions.UserNotFoundException;
import com.ustg.favourites.model.Favourites;

public interface FavouritesService {

	boolean addFavourite(Favourites favourite) throws FavouriteAlreadyExistException;

	List<Favourites> getFavourites(String userName) throws UserNotFoundException;
	
	boolean deleteFavourite(String userName, String favouriteName) throws UserNotFoundException, FavouriteNotFoundException;

}
