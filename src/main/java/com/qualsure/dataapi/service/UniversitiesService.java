package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.UniversitiesDAO;
import com.qualsure.dataapi.model.Universities;

@Service
public class UniversitiesService {

	@Autowired	
	private UniversitiesDAO universitiesDAO;
	
	
	public List<Universities> getAllUniversities() {
		return universitiesDAO.findAll();
	}

	public Universities getUniversities(String universitiesId) {
		return universitiesDAO.findOne(universitiesId);
	}

	public Universities addUniversities(Universities universities) {
		universitiesDAO.insert(universities);
		Universities newUniversities = universities;
		return newUniversities;
	}

	public void updateUniversities(Universities universities) {
		universitiesDAO.save(universities);
		
	}

	public void deleteUniversities(String universitiesId) {
		universitiesDAO.delete(universitiesId);
	}

	

}
