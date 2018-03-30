package com.qualsure.dataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.qualsure.dataapi.service.CacheService;


@SpringBootApplication
//@EnableMongoRepositories("com.qualsure.dataapi.dao")
public class DataApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DataApiApplication.class, args);
	}
	
}

