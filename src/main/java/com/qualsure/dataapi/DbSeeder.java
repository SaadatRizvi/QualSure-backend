package com.qualsure.dataapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
import com.qualsure.dataapi.model.Degree;

@Service
public class DbSeeder implements CommandLineRunner {

	@Autowired	
	private DegreeDAO degreeDAO;
	
	private static List<Degree> degrees = new ArrayList<>(Arrays.asList(
			new Degree("1","Saadat","Rizvi","BS"),
			new Degree("2","Rahjabeen","Abbasi","MS"),
			new Degree("3","Urwah","Khan","BS")	
					
			));
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		
		// add degress to db
		this.degreeDAO.save(degrees);
		
	}

}
