package com.qualsure.dataapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.DegreeDAO;
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
	
	
	private static List<Degree> degrees = new ArrayList<>(Arrays.asList(
			new Degree("1","Saadat","Rizvi","BS"),
			new Degree("2","Rahjabeen","Abbasi","MS"),
			new Degree("3","Urwah","Khan","BS")	
					
			));
	
	private static List<DynamicForm> dynamicForm = new ArrayList<>(Arrays.asList(
			new DynamicForm("1","Saadat","BS","CS", "1-1-18"),
			new DynamicForm("2","Abbasi","MS","FS", "21-13-18"),
			new DynamicForm("3","Raheel","PhD","EE", "13-12-12")	
					
			));
	@Override
	public void run(String... arg0) throws Exception {
	
		//drop all degrees
		this.degreeDAO.deleteAll();
		this.dynamicFormDAO.deleteAll();
		this.universitiesDAO.deleteAll();
		
		
		// add degress to db
		this.degreeDAO.save(degrees);
		this.dynamicFormDAO.save(dynamicForm);
		this.universitiesDAO.save(universities);
	}

}
