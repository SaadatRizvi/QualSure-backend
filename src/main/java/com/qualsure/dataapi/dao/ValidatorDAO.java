package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.Users;
import com.qualsure.dataapi.model.Validator;
@Repository
public interface ValidatorDAO extends MongoRepository<Validator, String>{

	

}