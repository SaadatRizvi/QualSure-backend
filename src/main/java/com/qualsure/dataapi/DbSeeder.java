package com.qualsure.dataapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
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
			new Universities("10", "GIKI", Arrays.asList(new FormAttributes("Stme",  "[A-Zas-z ]+", "rect", "String"))),
			new Universities("20", "LUMS", Arrays.asList()),
			new Universities("30", "NUST", Arrays.asList())
			));
	
	
	private static List<Degree> degrees = new ArrayList<>(Arrays.asList(
			new Degree("1","Saadat","Rizvi","BS"),
			new Degree("2","Rahjabeen","Abbasi","MS"),
			new Degree("3","Urwah","Khan","BS")	
					
			));
	
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.universitiesDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.universitiesDAO.save(universities);
	}

}
