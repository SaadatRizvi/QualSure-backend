package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.ValidatorDAO;
import com.qualsure.dataapi.model.Validator;

@Service
public class ValidatorService {

	@Autowired	
	private ValidatorDAO validatorDAO;
	
	
	public List<Validator> getAllValidators() {
		return validatorDAO.findAll();
	}

	public Validator getValidator(String validatorId) {
		return validatorDAO.findOne(validatorId);
	}

	public Validator addValidator(Validator validator) {
		validatorDAO.insert(validator);
		Validator newValidators = validator;
		return newValidators;
	}

	public void updateValidator(Validator validator) {
		validatorDAO.save(validator);
		
	}

	public void deleteValidator(String validatorId) {
		validatorDAO.delete(validatorId);
	}

	

}

