package com.example.userauthenticationapp.test.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.userauthenticationapp.exception.UserAlreadyExistsException;
import com.example.userauthenticationapp.exception.UserNotFoundException;
import com.example.userauthenticationapp.model.UserProfile;
import com.example.userauthenticationapp.repository.UserRepo;
import com.example.userauthenticationapp.service.UserprofileServiceImpl;



public class UserprofileServiceImplTest {

	 @Mock
	    private UserRepo userrepo;

	    private UserProfile usernew;
	    @InjectMocks
	    private UserprofileServiceImpl userprofileServiceImpl;

	    Optional<UserProfile> optional;


	    @BeforeEach
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        usernew = new UserProfile();
	        usernew.setUsername("ajay1");
	        usernew.setPassword("ajaypass");
	        
	        optional = Optional.of(usernew);
	    }

	    @Test
	    public void testSaveUserSuccess() throws UserAlreadyExistsException {

	        Mockito.when(userrepo.save(usernew)).thenReturn(usernew);
	        boolean flag = userprofileServiceImpl.addUser(usernew);
	        assertEquals(true, flag);

	    }


	    @Test
	    public void testSaveUserFailure() {

	        Mockito.when(userrepo.findById("ajay1")).thenReturn(optional);
	        Mockito.when(userrepo.save(usernew)).thenReturn(usernew);
	        assertThrows(
	        		UserAlreadyExistsException.class,
	                    () -> { userprofileServiceImpl.addUser(usernew); });

	    }

	    @Test
	    public void testFindByUsernameAndPassword() throws UserNotFoundException {
	        Mockito.when(userrepo.findByUsernameAndPassword("ajay1", "ajaypass")).thenReturn(usernew);
	        UserProfile fetchedUser = userprofileServiceImpl.findByUsernameAndPassword("ajay1", "ajaypass");
	        assertEquals("ajay1", fetchedUser.getUsername());
	    }
	}

