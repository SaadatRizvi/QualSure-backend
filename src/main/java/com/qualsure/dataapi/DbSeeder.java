package com.qualsure.dataapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.dao.UsersDAO;
import com.qualsure.dataapi.dao.ValidatorDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.Users;
import com.qualsure.dataapi.model.Validator;
import com.qualsure.dataapi.dao.UniversityDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.FormField;
import com.qualsure.dataapi.model.University;

@Service
public class DbSeeder implements CommandLineRunner {

	@Autowired	
	private DegreeDAO degreeDAO;
	@Autowired	
	private UniversityDAO universityDAO;
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private ValidatorDAO validatorDAO;
	
	private static List<University> university = new ArrayList<>(Arrays.asList(
			new University("10", "GIKI", "True", Arrays.asList(new FormField("Stme",  "[A-Zas-z ]+", "rect", "String"))),
			new University("20", "LUMS", "True", Arrays.asList()),
			new University("30", "NUST", "False", Arrays.asList())
			));
	
	
	


//	private String id;
//	private String username;
//	private String password;
//	private List<String> roles;
//	private String email;
//	private String active;
//	private String name;
//	
	private static List<Degree> degrees = new ArrayList<>(Arrays.asList(
			new Degree("1","Saadat","Rizvi","BS", "654654"),
			new Degree("2","Rahjabeen","UmerSheikh","MS", "10"),
			new Degree("4","Musab","Hameed","MBBS", "10"),
			new Degree("3","Urwah","QA-Engineer","BS", "654645")	
					
			));
	private static List<Users> users = new ArrayList<>(Arrays.asList(
			//password : "123"
			new Users("1","giki","$2a$04$JPQDDreU05bkZmZNGKUp8u5xYese3mQDf7po.6sLoV.QuMv4F2H8C",new ArrayList<String>(Arrays.asList("USER")),
					"admin@giki.edu.pk","true","Ghulam Ishaq Khan Institute"),
			//password : "456"
			new Users("2","nust","$2a$04$49F/aKWaDUKOJsDX8OXGoewgEzut64WutNDbjleLp1sYkpuryHQHa",new ArrayList<String>(Arrays.asList("USER")),
					"admin@nust.edu.pk","true","National University Of S&T"),
			//password : "admin"					
			new Users("3","admin","$2a$04$aOSd.znG7tUQSFTkHc07ZeN/mUI4GAXu6yxxeJ0qfxkmtQ0UbBvuy",new ArrayList<String>(Arrays.asList("USER","ADMIN")),
					"admin@qualsure.com","true","QualSure Inc.")
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
	
	private static List<Validator> validators = new ArrayList<>(
			Arrays.asList(
			new Validator("1","alpha", "[a-zA-z]*","text"),
			new Validator("2","alphaReq", "[a-zA-z]+","text"),
			new Validator("3","alphaNum", "[a-zA-z0-9]*","text"),
			new Validator("4","alphaNumReq", "[a-zA-z0-9]+","text"),
			new Validator("5","num", "[0-9]*","number"),
			new Validator("6","numReq", "[0-9]+","number"),
			new Validator("7","float", "[0-9]*[.]?[0-9]*","number"),
			new Validator("8","floatReq", "[0-9]*[.]?[0-9]+","number")
			));
	
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.usersDAO.deleteAll();
		this.universityDAO.deleteAll();
		this.validatorDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.usersDAO.save(users);
		this.universityDAO.save(university);
		this.validatorDAO.save(validators);
	}

}
