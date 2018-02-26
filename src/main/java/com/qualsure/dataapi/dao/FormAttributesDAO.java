package com.qualsure.dataapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qualsure.dataapi.model.FormAttributes;

@Repository
public interface FormAttributesDAO extends MongoRepository<FormAttributes, String>{

	FormAttributes findByName(String attributeName);

}
