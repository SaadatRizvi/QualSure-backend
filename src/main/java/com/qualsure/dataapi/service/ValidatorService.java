package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qualsure.dataapi.dao.ValidatorDAO;
import com.qualsure.dataapi.model.Validator;

public class ValidatorService {

	@Autowired	
	private ValidatorDAO validatorDAO;
	
	
	public List<Validator> getAllValidators() {
		return validatorDAO.findAll();
	}

	public Validator getValidators(String validatorId) {
		return validatorDAO.findOne(validatorId);
	}

	public Validator addValidators(Validator validator) {
		validatorDAO.insert(validator);
		Validator newValidators = validator;
		return newValidators;
	}

	public void updateValidators(Validator validator) {
		validatorDAO.save(validator);
		
	}

	public void deleteValidators(String validatorId) {
		validatorDAO.delete(validatorId);
	}

	

}

