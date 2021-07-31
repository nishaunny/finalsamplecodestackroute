package com.ustg.favourites.exceptions;

public class FavouriteAlreadyExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public FavouriteAlreadyExistException(String message) {
		super(message);
	}
}
