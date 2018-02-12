package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.Degree;


@Repository
public interface DegreeDAO extends MongoRepository<Degree, String>{

	//Degree findById(String id);
}
