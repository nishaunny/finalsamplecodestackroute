package com.example.userauthenticationapp.exception;

public class UsernameAndPasswordMismatchException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsernameAndPasswordMismatchException(String message) {
        super(message);
    }
}
