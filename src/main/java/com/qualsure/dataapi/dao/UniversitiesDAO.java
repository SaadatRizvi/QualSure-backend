package com.qualsure.dataapi.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.Universities;
import com.qualsure.dataapi.model.Users;

import org.springframework.data.mongodb.repository.Query;
@Repository
public interface UniversitiesDAO extends MongoRepository<Universities, String>{
	
	@Query(value="{}", fields="{ 'name' : 1}")
	List <Universities> findByNameRegex(String allUniRegex);
	
}
