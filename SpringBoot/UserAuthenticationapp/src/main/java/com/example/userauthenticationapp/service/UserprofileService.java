package com.example.userauthenticationapp.service;


import com.example.userauthenticationapp.exception.UserAlreadyExistsException;
import com.example.userauthenticationapp.exception.UserNotFoundException;
import com.example.userauthenticationapp.model.UserProfile;


public interface UserprofileService {

//	UserProfile addUser(UserProfile usernew);
//	    boolean  validateUser(String username,String pass);
	
//}
	boolean addUser(UserProfile userObj)throws UserAlreadyExistsException;
	public UserProfile findByUsernameAndPassword(String username,String password) throws UserNotFoundException;

}