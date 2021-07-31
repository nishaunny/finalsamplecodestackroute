package com.example.userauthenticationapp.test.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.userauthenticationapp.model.UserProfile;
import com.example.userauthenticationapp.repository.UserRepo;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {

	 @Autowired
	    private UserRepo userrepo;
	    
	    private UserProfile usernew;
	    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        usernew = new UserProfile();
	        usernew.setUsername("ajay2");
	        usernew.setPassword("ajaypass");
	     
	    }

	    @AfterEach
	    public void tearDown() throws Exception {
	    	userrepo.deleteAll();
	    }


	    @Test
	    public void testRegisterUserSuccess() {
	    	userrepo.save(usernew);
	        UserProfile fetchUser = userrepo.findById(usernew.getUsername()).get();
	        assertThat(usernew.getUsername(), is(fetchUser.getUsername()));
	    }

	    @Test
	    public void testLoginUserSuccess() {
	    	userrepo.save(usernew);
	        UserProfile fetchUser = userrepo.findById(usernew.getUsername()).get();
	        assertThat(usernew.getUsername(), is(fetchUser.getUsername()));
	    }

	}
