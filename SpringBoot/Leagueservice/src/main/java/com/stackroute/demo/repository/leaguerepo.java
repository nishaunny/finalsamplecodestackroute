package com.stackroute.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.demo.model.LeagueInfo;
import com.stackroute.demo.model.User;


@Repository
public interface leaguerepo extends MongoRepository<User,String>{

}
