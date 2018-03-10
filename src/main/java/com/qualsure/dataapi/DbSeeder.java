package com.qualsure.dataapi;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.dao.FormFieldDAO;
import com.qualsure.dataapi.dao.UsersDAO;
import com.qualsure.dataapi.dao.ValidatorDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.Users;
import com.qualsure.dataapi.model.Validator;
import com.qualsure.dataapi.service.UniversitiesService;
import com.qualsure.dataapi.service.UsersService;
import com.qualsure.dataapi.dao.UniversityDAO;
import com.qualsure.dataapi.model.FormField;
import com.qualsure.dataapi.model.University;

import org.apache.log4j.Logger;

@Service
public class DbSeeder implements CommandLineRunner {

	@SuppressWarnings("unused")
	@Autowired
	private static Logger logger = Logger.getLogger(DbSeeder.class);


	@Autowired	
	private DegreeDAO degreeDAO;
	@Autowired	
	private UniversityDAO universityDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private ValidatorDAO validatorDAO;
	@Autowired
	private FormFieldDAO formAttributesDAO;
	@Autowired
	private UsersService usersService;

	
	private static List<Validator> validators = new ArrayList<>(
			Arrays.asList(
			new Validator("1","None", "","none"),
			new Validator("2","Alpha", "[a-zA-z]*","text"),
//			new Validator("3","Alpha Required", "[a-zA-z]+","text"),
			new Validator("3","Alphanumeric", "[a-zA-z0-9]*","text"),
//			new Validator("5","Alphanumeric Required", "[a-zA-z0-9]+","text"),
			new Validator("4","Numeric", "[0-9]*","number"),
//			new Validator("7","Numeric Required", "[0-9]+","number"),
			new Validator("5","Float", "[0-9]*[.]?[0-9]*","number"),
//			new Validator("9","Float Required", "[0-9]*[.]?[0-9]+","number")
			new Validator("6","CNIC", "[0-9]*","cnic"),
			new Validator("7","Range", "[0-9]+","number")


			));
	
	
	private static List<FormField> formFields = new ArrayList<FormField>(Arrays.asList(
			 new FormField("Student Name",  Arrays.asList(validators.get(1)), "Student name is incorrect", "text"), 
			 new FormField("GPA",  Arrays.asList(validators.get(4)), "GPA is incorrect", "number"),
			 new FormField("Graduation Year",  Arrays.asList(validators.get(4)), "Graduation Year is incorrect", "number"),
			 new FormField("Degree Type",  Arrays.asList(validators.get(1)), "Degree Type is incorrect", "text"),
			 new FormField("Degree Name",  Arrays.asList(validators.get(1)), "Degree Name is incorrect", "text"),
			 new FormField("CNIC",  Arrays.asList(validators.get(5)), "CNIC is incorrect", "cnic")

			 ));
	
	
	
	

	
//	private static List<FormField> formFields2 = new ArrayList<FormField>(Arrays.asList(
//			 new FormField("StudentName",  Arrays.asList(), "Username is incorrect", "String"), 
//			 new FormField("GPA",  Arrays.asList(), "GPA is incorrect", "Number"),
//			 new FormField("DegreeType",  Arrays.asList(), "DegreeType is incorrect", "String"),
//			 new FormField("DegreeName",  Arrays.asList(), "DegreeName is incorrect", "String")));


//	private String id;
//	private String universityId;
//	private String studentName;
//	private String gpa;
//	private String graduationYear;
//	private String degreeType;
//	private String degreeName;
//	
//	private static List<Degree> degrees = new ArrayList<>(Arrays.asList(
//			new Degree("100","2","Rizvi","2.3", "2015", "BS", "CS"),
//			new Degree("200","2","UmerSheikh","3.3", "2010", "MS", "CSSS"),
//			new Degree("400","3","Hameed","1.1", "2011", "MS", "cSS"),
//			new Degree("300","1","QA-Engineer","2.6", "2016", "PHD", "CScscs")	
//					
//			));
	
//	private String number;
//	private String address;
//	private String representativeName;
//	private String representativeNumber;
//	private String representativeCNIC;
//	
	private static List<Users> users = new ArrayList<>(Arrays.asList(
			//password : "123"
			new Users("1","giki","$2a$04$JPQDDreU05bkZmZNGKUp8u5xYese3mQDf7po.6sLoV.QuMv4F2H8C",new ArrayList<String>(Arrays.asList("USER")),
					"admin@giki.edu.pk","true","Ghulam Ishaq Khan Institute","090078601","123,GIKI,Swabi","Asad","03312345676","123456789"),
			//password : "456"
			new Users("2","nust","$2a$04$49F/aKWaDUKOJsDX8OXGoewgEzut64WutNDbjleLp1sYkpuryHQHa",new ArrayList<String>(Arrays.asList("USER")),
					"admin@nust.edu.pk","true","National University Of S&T","090078601","123,nust,Swabi","Lullu","03312345676","123456789"),
			//password : "admin"					
			new Users("3","admin","$2a$04$aOSd.znG7tUQSFTkHc07ZeN/mUI4GAXu6yxxeJ0qfxkmtQ0UbBvuy",new ArrayList<String>(Arrays.asList("USER","ADMIN")),
					"admin@qualsure.com","true","QualSure Inc.","090078601","123,admin,Swabi","Lalo","03312345676","123456789")
			));
			/*
			 "username":"lums",
			 "password":"abc",
			 "roles": [
            			"USER"
        			  ],
			 "email":"admin@lums.edu.pk",
			 "active":"true",
			 "name":"Lahore University of M&S"
			
			
			*/
	

	
	@Override
	public void run(String... arg0) throws Exception {

		
//		  List<University> universities = new ArrayList<>(Arrays.asList(
//				new University("10", "GIKI", "True", formFields),
//				new University("20", "LUMS", "True", Arrays.asList()),
//				new University("30", "NUST", "False", Arrays.asList())
//				));
		//drop all
		this.degreeDAO.deleteAll();
		this.usersDAO.deleteAll();
		this.universityDAO.deleteAll();
		this.validatorDAO.deleteAll();
		this.formAttributesDAO.deleteAll();

		
		
		// add to db
//		this.degreeDAO.save(degrees);
	//	this.usersService.(users);
	//	this.universityDAO.save(universities);
	//	this.usersService.addMultipleUsers(users);
		this.validatorDAO.save(validators);
		this.formAttributesDAO.save(formFields);
		this.usersService.addMultipleUsers(users);

	}

}
