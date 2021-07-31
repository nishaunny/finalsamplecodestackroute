package com.example.userauthenticationapp.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userauthenticationapp.exception.UserAlreadyExistsException;
import com.example.userauthenticationapp.exception.UserNotFoundException;
import com.example.userauthenticationapp.model.UserProfile;
import com.example.userauthenticationapp.service.UserprofileService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("auth/user")
@CrossOrigin("*")
public class UserController {

	public UserprofileService userprofileservice;

	@Autowired
	public UserController(UserprofileService userservice) {
		this.userprofileservice = userservice;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody UserProfile userObj) {
		try {
			this.userprofileservice.addUser(userObj);
			return new ResponseEntity<String>("user added", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

//	public ResponseEntity<?> adduser(@RequestBody UserProfile userobj)
//	{
//	UserProfile resultobj=	userservice.addUser(userobj);
//	
//	return new ResponseEntity<UserProfile>(resultobj,HttpStatus.CREATED);
//	}
//	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	public ResponseEntity<?> validatelogin(@RequestBody UserProfile userobj) {
		try {
			UserProfile userObj = userprofileservice.findByUsernameAndPassword(userobj.getUsername(),
					userobj.getPassword());

			String mytoken = generateToken(userobj);
			HashMap<String, String> mymap = new HashMap<String, String>();
			mymap.put("token", mytoken);
			return new ResponseEntity<>(mymap, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Invalid credentials", HttpStatus.UNAUTHORIZED);

//	public ResponseEntity<?> validatelogin(@RequestBody UserProfile userobj)
//	{
//		
//		boolean res=userservice.validateUser(userobj.getUsername(), userobj.getPassword() );
//		
//		if (res)
//		{
//			
//			String mytoken=generateToken(userobj);
//			HashMap mymap=new HashMap();
//			mymap.put("token",mytoken);
//			return new ResponseEntity<HashMap>(mymap , HttpStatus.OK);
//		}
//		else
//			
//			return new ResponseEntity<String>("Invalid credentials",HttpStatus.UNAUTHORIZED);
//		
		}
	}

	public String generateToken(UserProfile uprofile) {

		long expirytime = 10_000_000;

		return Jwts.builder().setSubject(uprofile.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirytime))
				.signWith(SignatureAlgorithm.HS256, "ustboot3").compact();

	}

}
