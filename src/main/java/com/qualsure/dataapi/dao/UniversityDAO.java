package com.qualsure.dataapi.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.qualsure.dataapi.model.University;
import org.springframework.data.mongodb.repository.Query;
@Repository
public interface UniversityDAO extends MongoRepository<University, String>{
	
	@Query(value="{}", fields="{ 'name' : 1}")
	List <University> findByNameRegex(String allUniRegex);
	
	@Query(value="{ 'accountId' : ?0 }", fields="{ 'formFields' : 1, 'accountId': 1}")
	University findAllFormFields(String accountId);
	
	University findByAccountId(String accountId);
	
}
