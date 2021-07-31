package com.example.userauthenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userauthenticationapp.model.UserProfile;



@Repository
public interface UserRepo extends JpaRepository<UserProfile,String> {
	
	UserProfile findByUsernameAndPassword(String username,String password);

}