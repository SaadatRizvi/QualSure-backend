package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.Users;


@Repository
public interface UsersDAO extends MongoRepository<Users, String>{

	Users findByUsername(String username);

	
}
