package com.qualsure.dataapi.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.qualsure.dataapi.model.Degree;


@Repository
public interface DegreeDAO extends MongoRepository<Degree, String>{
	
	List<Degree> findByUniversityId(String universityId);
	Degree findByUniversityIdAndId(String universityId, String degreeId);

	  @Query("{ 'universityId' : ?0, 'degreeDetails.studentName': ?1, 'degreeDetails.gpa': ?2, 'degreeDetails.graduationYear': ?3, 'degreeDetails.degreeType': ?4, 'degreeDetails.degreeName': ?5, 'degreeDetails.CNIC': ?6 } }")
	Degree findByFixedFields(String universityId, String studentName, String gpa, String graduationYear, String degreeType, String degreeName, String CNIC);
	
}
