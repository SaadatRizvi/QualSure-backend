package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.DynamicForm;


@Repository
public interface DynamicFormDAO extends MongoRepository<DynamicForm, String>{

	
}
