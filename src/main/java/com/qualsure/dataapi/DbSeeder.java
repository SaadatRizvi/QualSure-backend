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
import com.qualsure.dataapi.dao.DynamicFormDAO;
import com.qualsure.dataapi.dao.UniversitiesDAO;
import com.qualsure.dataapi.model.Degree;
import com.qualsure.dataapi.model.DynamicForm;
import com.qualsure.dataapi.model.Universities;

@Service
public class DbSeeder implements CommandLineRunner {

	@Autowired	
	private DegreeDAO degreeDAO;
	@Autowired	
	private DynamicFormDAO dynamicFormDAO;
	@Autowired	
	private UniversitiesDAO universitiesDAO;

	
	private static List<Universities> universities = new ArrayList<>(Arrays.asList(
			new Universities("10", "GIKI", Arrays.asList()),
			new Universities("20", "LUMS", Arrays.asList()),
			new Universities("30", "NUST", Arrays.asList())
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
			new Degree("1","Saadat","Rizvi","BS"),
			new Degree("2","Rahjabeen","Abbasi","MS"),
			new Degree("3","Urwah","Khan","BS")	
					
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
	
	private static List<DynamicForm> dynamicForm = new ArrayList<>(Arrays.asList(
			new DynamicForm("1","Saadat","BS","CS", "1-1-18"),
			new DynamicForm("2","Abbasi","MS","FS", "21-13-18"),
			new DynamicForm("3","Raheel","PhD","EE", "13-12-12")	
					
			));
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.usersDAO.deleteAll();
		this.dynamicFormDAO.deleteAll();
		this.universitiesDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.usersDAO.save(users);
		this.dynamicFormDAO.save(dynamicForm);
		this.universitiesDAO.save(universities);
	}

}
