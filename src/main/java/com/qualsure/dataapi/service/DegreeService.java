package com.qualsure.dataapi.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.DbSeeder;
import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;

@Service
public class DegreeService {
	
	@Autowired
	private static Logger logger = Logger.getLogger(DbSeeder.class);

	@Autowired	
	private DegreeDAO degreeDAO;
	
	
	public List<Degree> getAllDegrees(){
	
		
		return degreeDAO.findAll();
	}
	
	public Degree getDegree(String universityId, String degreeId) {
	//	return degrees.stream().filter(t -> t.getId() == degreeId).findFirst().get();
		return degreeDAO.findByUniversityIdAndId(universityId, degreeId);
	}

	public Degree addDegree(String universityId, Map<String, String>  degreeDetails) {
		Degree newDegree = new Degree(universityId,degreeDetails,"tempHash");
		degreeDAO.insert(newDegree);
		return newDegree;
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
	

	public boolean verifyDegree(String universityId, Degree degree) {
		String studentName = degree.getStudentName();
		String gpa = degree.getGpa();
		String graduationYear = degree.getGraduationYear();
		String degreeType = degree.getDegreeType();
		String degreeName = degree.getDegreeName();
		String CNIC = degree.getCNIC();

		Degree returnedDegree= degreeDAO.findByFixedFields(universityId, studentName, gpa, graduationYear, degreeType, degreeName,CNIC);
		
		String[] keys = returnedDegree.getDegreeDetails().keySet().toArray(new String[0]);
		for(int i=0;i<keys.length;i++) {
			if(!returnedDegree.getDegreeDetails().get(keys[i]).equals(degree.getDegreeDetails().get(keys[i])) ) {
//				logger.info("Returned degree "+ returnedDegree.getDegreeDetails().get(keys[i]));
//
//				logger.info("Returned degree "+ returnedDegree.getDegreeDetails().get(keys[i]));
//				logger.info("degree "+ degree.getDegreeDetails().get(keys[i]));

				return false;
			}
		}
		return true;
	}

	
}
