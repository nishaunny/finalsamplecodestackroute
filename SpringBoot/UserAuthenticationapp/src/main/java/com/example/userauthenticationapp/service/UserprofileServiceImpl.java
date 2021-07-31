package com.example.userauthenticationapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userauthenticationapp.exception.UserAlreadyExistsException;
import com.example.userauthenticationapp.exception.UserNotFoundException;
import com.example.userauthenticationapp.model.UserProfile;
import com.example.userauthenticationapp.repository.UserRepo;


@Service
public class UserprofileServiceImpl  implements UserprofileService{

	
	public UserRepo userrepo;
	@Autowired
	public UserprofileServiceImpl(UserRepo userrepo) 
	{
		this.userrepo = userrepo;
	}
	@Override
	public boolean addUser(UserProfile usernew) throws UserAlreadyExistsException {
		Optional<UserProfile> optionUser = userrepo.findById(usernew.getUsername());
		if (optionUser.isPresent()) {
			throw new UserAlreadyExistsException("user already exists");
		}

		userrepo.save(usernew);

		return true;
		
	}

	@Override
	public UserProfile findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		UserProfile uProfile = userrepo.findByUsernameAndPassword(username, password);
		if (uProfile == null) {
			throw new UserNotFoundException("user already exists");
		}
		return uProfile;
	}
	}

	

//	
//	@Override
//	public UserProfile addUser(UserProfile usernew) {
//		
//		UserProfile result= userrepo.save(usernew);
//		return result;
//	}
//
//	@Override
//	public boolean validateUser(String username, String pass) {
//		
//	
//		UserProfile uprofile=userrepo.findByUsernameAndPassword(username, pass);	
//		
//		if (uprofile==null)
//		return false;
//		else
//			return true;
//		
//	}

