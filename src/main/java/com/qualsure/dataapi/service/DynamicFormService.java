package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.dao.DynamicFormDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.DynamicForm;


@Service
public class DynamicFormService {

	@Autowired	
	private DynamicFormDAO dynamicFormDAO;
	
	public List<DynamicForm> getAllFormFields() {

		return dynamicFormDAO.findAll();
	}

	public DynamicForm getFormField(String fieldId) {

		return dynamicFormDAO.findOne(fieldId);
	}

	public DynamicForm addFormField(DynamicForm dynamicForm) {
		dynamicFormDAO.insert(dynamicForm);
		DynamicForm newDynamicForm = dynamicForm;
		return newDynamicForm;
	}

	public void updateDynamicForm(DynamicForm dynamicForm) {
		dynamicFormDAO.save(dynamicForm);
		
	}

	public void deleteFormField(String fieldId) {
		dynamicFormDAO.delete(fieldId);
		
	}

}
