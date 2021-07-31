package com.stackroute.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.demo.exception.LeagueAlreadyExistsException;
import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.service.LeagueService;


@RestController
@CrossOrigin("*")
public class LeagController {
	

	@Autowired
	LeagueService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/api/League/addLeagueinfo/{userName}")
    public ResponseEntity<String> addLeaguedata(@PathVariable("userName") String userName,@RequestBody LeagueInfo[] information){
        try {
            if( service.addLeagueDetails(userName,information)) {
            return new ResponseEntity<String>("League added", HttpStatus.CREATED);
            }
        } catch (LeagueAlreadyExistsException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


}
