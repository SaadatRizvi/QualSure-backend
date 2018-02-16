package com.qualsure.dataapi.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.Universities;

@Repository
public interface UniversitiesDAO extends MongoRepository<Universities, String>{

}
