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

@Service
public class DbSeeder implements CommandLineRunner {

	@Autowired	
	private DegreeDAO degreeDAO;
	
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
			new Users("1","giki","123",new ArrayList<String>(Arrays.asList("USER")),
					"admin@giki.edu.pk","true","Ghulam Ishaq Khan Institute"),
					
			new Users("2","NUST","456",new ArrayList<String>(Arrays.asList("USER")),
					"admin@nust.edu.pk","true","National University Of S&T"),
					
			new Users("3","admin","admin",new ArrayList<String>(Arrays.asList("USER","ADMIN")),
					"admin@qualsure.com","true","QualSure Inc.")		
			
			));
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.usersDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.usersDAO.save(users);
	}

}
