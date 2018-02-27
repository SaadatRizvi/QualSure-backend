package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.FormField;

@Repository
public interface FormFieldDAO extends MongoRepository<FormField, String>{

	FormField findByName(String attributeName);

}
