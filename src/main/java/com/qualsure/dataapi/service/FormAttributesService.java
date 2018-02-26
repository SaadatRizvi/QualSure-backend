package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.FormAttributesDAO;
import com.qualsure.dataapi.model.FormField;

@Service
public class FormAttributesService {

	@Autowired	
	private FormAttributesDAO formAttributesDAO;
	
	public List<FormField> getAllFormAttributes() {
		return formAttributesDAO.findAll();
	}

	public FormField getFormAttributesByName(String attributeName) {
		return formAttributesDAO.findByName(attributeName);
	}

}
