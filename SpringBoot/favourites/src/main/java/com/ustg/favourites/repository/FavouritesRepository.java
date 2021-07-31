package com.ustg.favourites.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ustg.favourites.model.User;

@Repository
public interface FavouritesRepository extends MongoRepository<User, String>{

}
