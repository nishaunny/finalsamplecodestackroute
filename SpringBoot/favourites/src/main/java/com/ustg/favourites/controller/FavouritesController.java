package com.ustg.favourites.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ustg.favourites.exceptions.FavouriteAlreadyExistException;
import com.ustg.favourites.exceptions.FavouriteNotFoundException;
import com.ustg.favourites.exceptions.UserNotFoundException;
import com.ustg.favourites.model.Favourites;
import com.ustg.favourites.services.FavouritesService;

@RestController
@CrossOrigin("*")
public class FavouritesController {

	@Autowired
	public FavouritesService favouriteService;

	public FavouritesController(FavouritesService favouriteService) {
		this.favouriteService = favouriteService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/api/favourites/addFavourite")
	public ResponseEntity<String> addFavourite(@RequestBody Favourites favourite) {
		try {
			if (favouriteService.addFavourite(favourite)) {
				return new ResponseEntity<String>("favourite added", HttpStatus.CREATED);
			}
		} catch (FavouriteAlreadyExistException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/api/favourites/getFavourites/{userName}")
	public ResponseEntity<List<Favourites>> getAllFavourites(@PathVariable("userName") String userName) {
		try {
			List<Favourites> favourites = favouriteService.getFavourites(userName);
			if (favourites!= null) {
				return new ResponseEntity<List<Favourites>>(favouriteService.getFavourites(userName),
						HttpStatus.CREATED);
			}
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/api/favourites/deleteFavourite/{userName}/{favName}")
	public ResponseEntity<String> deleteFavourite(@PathVariable("userName") String userName,
			@PathVariable("favName") String favouriteName) {

		try {
			if (favouriteService.deleteFavourite(userName, favouriteName))
				return new ResponseEntity<String>("favourite deleted", HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (FavouriteNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
