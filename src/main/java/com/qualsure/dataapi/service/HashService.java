package com.qualsure.dataapi.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class HashService {


	public  String getHash(Map<String,String> hm)  {
		
		String degreeString = "";
		
//		HashMap<String,String> hm=new HashMap<String, String>();  
//		hm.put("55","Rahjabeen"); 
//		hm.put("Degree","BSCS");  
//		hm.put("GPA","3.2");

		for(Map.Entry m:hm.entrySet()){  
			degreeString += m.getKey() + ": " + m.getValue() + ", ";  
		}
		
		try{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedHash = digest.digest(
				degreeString.getBytes(StandardCharsets.UTF_8));
			
		
		byte[] hash = encodedHash;
		StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    
		    if(hex.length() == 1) hexString.append('0');
		        hexString.append(hex);
		 }
	    return (hexString.toString());
		}
		catch(NoSuchAlgorithmException ex){
			return "HashingError: NoSuchAlgorithmException";
		}
	}

}
