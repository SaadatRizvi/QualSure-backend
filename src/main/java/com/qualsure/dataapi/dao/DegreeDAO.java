package com.qualsure.dataapi.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.qualsure.dataapi.model.Degree;


@Repository
public interface DegreeDAO extends MongoRepository<Degree, String>{
	
	List<Degree> findByUniversityId(String universityId);
	Degree findByUniversityIdAndId(String universityId, String degreeId);

	
	Degree findByUniversityIdAndStudentNameAndGpaAndGraduationYearAndDegreeTypeAndDegreeName(String universityId, String studentName, String gpa, String graduationYear, String degreeType, String degreeName);
	
}
