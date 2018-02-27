package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.FormFieldDAO;
import com.qualsure.dataapi.model.FormField;

@Service
public class FormFieldService {

	@Autowired	
	private FormFieldDAO formFieldDAO;
	
	public List<FormField> getAllFormFields() {
		return formFieldDAO.findAll();
	}

	public FormField getFormFieldByName(String fieldName) {
		return formFieldDAO.findByName(fieldName);
	}

}
