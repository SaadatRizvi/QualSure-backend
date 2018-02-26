package com.qualsure.dataapi.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.UniversityDAO;
import com.qualsure.dataapi.model.University;

@Service
public class UniversitiesService {

	@Autowired	
	private UniversityDAO universityDAO;
	
	
	public List<University> getAllUniversities() {
		return universityDAO.findAll();
	}

	public University getUniversities(String universitiesId) {
		return universityDAO.findOne(universitiesId);
	}

	public University addUniversities(University university) {
		universityDAO.insert(university);
		University newUniversities = university;
		return newUniversities;
	}

	public void updateUniversities(University university) {
		universityDAO.save(university);
		
	}

	public void deleteUniversities(String universitiesId) {
		universityDAO.delete(universitiesId);
	}

	public List<University> getAllUniversitiesNames() {

		return universityDAO.findByNameRegex("*");
	}
	
	public University findFormFieldsByUniId(String universityId) {
		return universityDAO.findById(universityId);
	}

}
