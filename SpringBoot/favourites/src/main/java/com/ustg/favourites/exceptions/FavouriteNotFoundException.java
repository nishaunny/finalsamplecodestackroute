package com.ustg.favourites.exceptions;

public class FavouriteNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public FavouriteNotFoundException(String message) {
		super(message);
	}
}
