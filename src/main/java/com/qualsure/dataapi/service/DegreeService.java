package com.qualsure.dataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;

@Service
public class DegreeService {

	@Autowired	
	private DegreeDAO degreeDAO;
	
	
	public List<Degree> getAllDegrees(){
	
		
		return degreeDAO.findAll();
	}
	
	public Degree getDegree(String universityId, String degreeId) {
	//	return degrees.stream().filter(t -> t.getId() == degreeId).findFirst().get();
		return degreeDAO.findByUniversityIdAndId(universityId, degreeId);
	}

	public Degree addDegree(String universityId, Degree degree) {
		degree.setUniversityId(universityId);
		degreeDAO.insert(degree);
		return degree;
	}

	public void updateDegree( Degree degree) {
		degreeDAO.save(degree);
	}

	public void deleteDegree(String degreeId) {
		//	degrees.removeIf(t-> t.getId() == degreeId);
		degreeDAO.delete(degreeId);
	}
	
	public List<Degree> findByUniId(String universityId) {
		return degreeDAO.findByUniversityId(universityId);
	}
	

	public Degree findDegreeInDb(String universityId, Degree degree) {
		String studentName = degree.getStudentName();
		String gpa = degree.getGpa();
		String graduationYear = degree.getGraduationYear();
		String degreeType = degree.getDegreeType();
		String degreeName = degree.getDegreeName();
		return degreeDAO.findByUniversityIdAndStudentNameAndGpaAndGraduationYearAndDegreeTypeAndDegreeName(universityId, studentName, gpa, graduationYear, degreeType, degreeName);
	}

	
}
