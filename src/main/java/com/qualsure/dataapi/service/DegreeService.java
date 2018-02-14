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
	
	public Degree getDegree(String degreeId) {
	//	return degrees.stream().filter(t -> t.getId() == degreeId).findFirst().get();
		return degreeDAO.findOne(degreeId);
	}

	public Degree addDegree(Degree degree) {
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
}
