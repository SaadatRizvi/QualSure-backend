package com.qualsure.dataapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.dao.UsersDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.Users;
import com.qualsure.dataapi.dao.UniversitiesDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.FormAttributes;
import com.qualsure.dataapi.model.Universities;

@Service
public class DbSeeder implements CommandLineRunner {

	@Autowired	
	private DegreeDAO degreeDAO;
	@Autowired	
	private UniversitiesDAO universitiesDAO;

	
	private static List<Universities> universities = new ArrayList<>(Arrays.asList(
			new Universities("10", "GIKI", "True", Arrays.asList(new FormAttributes("Stme",  "[A-Zas-z ]+", "rect", "String"))),
			new Universities("20", "LUMS", "True", Arrays.asList()),
			new Universities("30", "NUST", "False", Arrays.asList())
			));
	
	
	@Autowired
	private UsersDAO usersDAO;


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
	
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.usersDAO.deleteAll();
		this.universitiesDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.usersDAO.save(users);
		this.universitiesDAO.save(universities);
	}

}
