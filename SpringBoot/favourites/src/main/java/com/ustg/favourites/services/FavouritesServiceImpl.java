package com.ustg.favourites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustg.favourites.exceptions.FavouriteAlreadyExistException;
import com.ustg.favourites.exceptions.FavouriteNotFoundException;
import com.ustg.favourites.exceptions.UserNotFoundException;
import com.ustg.favourites.model.Favourites;
import com.ustg.favourites.model.User;
import com.ustg.favourites.repository.FavouritesRepository;

@Service
public class FavouritesServiceImpl implements FavouritesService {

	@Autowired
	FavouritesRepository favouritesRepo;

	public FavouritesServiceImpl(FavouritesRepository favouritesRepo) {
		this.favouritesRepo = favouritesRepo;

	}

	@Override
	public boolean addFavourite(Favourites favourite) throws FavouriteAlreadyExistException {

		/* checking and getting user object */
		Optional<User> userExist = favouritesRepo.findById(favourite.getUserName());
		if (userExist.isPresent()) {

			/* getting count on favourite exists or not */
			long favCount = userExist.get().getFavourites().stream()
					.filter(fav -> fav.getTeamName().equalsIgnoreCase(favourite.getTeamName())).count();

			/* add favourite to existing list if count is 0 */
			if (favCount == 0) {
				userExist.get().getFavourites().add(favourite);
				favouritesRepo.save(userExist.get());
				return true;
			} else {
				throw new FavouriteAlreadyExistException("favourite already exists");
			}
		}
		List<Favourites> favList= new ArrayList<>();
		favList.add(favourite);
		User user= new User();
		user.setUserName(favourite.getUserName());
		user.setFavourites(favList);
		if(favouritesRepo.insert(user)!=null) {
			return true;
		}
		return false;
//		throw new UserNotFoundException("User" + favourite.getUserName() + "doesn't exists");
	}

	@Override
	public List<Favourites> getFavourites(String userName) throws UserNotFoundException {
		Optional<User> user = favouritesRepo.findById(userName);
		if (user.isPresent()) {
			List<Favourites> favouritesList = user.get().getFavourites();
			return favouritesList;
		}
		throw new UserNotFoundException("User" + userName + "doesn't exists");
	}

	@Override
	public boolean deleteFavourite(String userName, String favouriteName) throws UserNotFoundException, FavouriteNotFoundException {
		Optional<User> userExist = favouritesRepo.findById(userName);
		if (userExist.isPresent()) {
			boolean favStatus = userExist.get().getFavourites().stream().anyMatch(favz->favz.getTeamName().equalsIgnoreCase(favouriteName));
			
			if(favStatus) {
				User user=userExist.get();
				List<Favourites> newsList=user.getFavourites().stream().filter(favz->!favz.getTeamName().equalsIgnoreCase(favouriteName)).collect(Collectors.toList());
				user.setFavourites(newsList);
				favouritesRepo.save(user);
				return true;
			}
			throw new FavouriteNotFoundException("favourite name" +favouriteName+"doesn't exist");
		}
		throw new UserNotFoundException("User" + userName + "doesn't exists");
	}
}
