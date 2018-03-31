package com.qualsure.dataapi.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qualsure.dataapi.dao.FormFieldDAO;
import com.qualsure.dataapi.dao.UniversityDAO;
import com.qualsure.dataapi.dao.UsersDAO;
import com.qualsure.dataapi.model.ResponseStatus;
import com.qualsure.dataapi.model.University;
import com.qualsure.dataapi.model.Users;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService implements UserDetailsService {
	
	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private UniversityDAO universityDAO;
	
	@Autowired	
	private FormFieldDAO formFieldDAO;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	CacheManager cm = CacheManager.getInstance();
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Users user = usersDAO.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Users> findAll() {
		List<Users> list = new ArrayList<>();
		usersDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public void delete(String id) {
		usersDAO.delete(id);
	}

	public Users findOne(String username) {
		return usersDAO.findByUsername(username);
	}
	public ResponseStatus getAccountBalance(String userId){
		Users user=findById(userId);
	
		try{
      	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("username", user.getUsername());
			 
			  
			  String url = "http://192.168.100.28:8090/user/checkBalance";
			  
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
			  
			  System.out.println(response.toString());
		      if(response.getStatus().equals("true")){
		    	  System.out.println("balance" + response.getMessage());
		    	  return response;
		      }
		      else{
		    	  System.out.println("user not created");
		    	  return null;
		      }
	 } 	
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        return null;
	    }

	}
	public boolean signInDataCrypt(String username, String password, byte[] cipherText) throws Exception{
		byte[] decryptedCipherText = decryptPassword(password, cipherText);
		Cache cache = cm.getCache("cache1");
		System.out.println(new String(cipherText, StandardCharsets.UTF_8));
		System.out.println(new String(decryptedCipherText, StandardCharsets.UTF_8));
		try{
      	  RestTemplate restTemplate = new RestTemplate();
		  HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			  
		  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		  map.add("username", username);
		  map.add("password", new String(decryptedCipherText, StandardCharsets.UTF_8));
			  
		  String url = "http://192.168.0.105:8090/authenticate";
			   
		  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
			  
		  System.out.println(response.toString());
	      if(response.getStatus().equals("true")){
	    	  System.out.println("Data Cypt user logged in ");
	    	  //cache.put(new Element("token",response.getToken()));
	    	  //Element ele = cache.get("token");
	  		
	  		  //6. Print out the element
	  		  //String output = (ele == null ? null : ele.getObjectValue().toString());
	  		  //System.out.println(output);
//	    	  cach.setMyCache("token",response.getToken());
//	    	  System.out.println(cach.getMyCache("token"));
	    	  return true;
	      }
	      else{
	    	  System.out.println("user not logged in");
	    	  return false;
	      }
	 } 	
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        return false;
	    }

	}

	public byte[] decryptPassword(String password, byte[] cipherText)  {
		EncryptionService advancedEncryptionStandard = new EncryptionService();
		advancedEncryptionStandard.setKey(getRequiredLength(password).getBytes(StandardCharsets.UTF_8));
		return advancedEncryptionStandard.decrypt(cipherText);
		
	}
	public Users findById(String id) {
		return usersDAO.findOne(id);
	}

	public Users save(Users user) {
        return usersDAO.save(user);
    }
	public Users addUser(Users user) throws Exception {
		EncryptionService advancedEncryptionStandard = new EncryptionService();
		String encryptionKey = user.getPassword();
		user.setPassword(bcryptEncoder.encode(user.getPassword()));

		String randomPassword = randomPasswordGenerator();
		ResponseStatus response=signUpDataCrypt(user.getUsername(),randomPassword,user.getEmail());
		if(response != null){
			if(response.getStatus().equals("true")){
				String key=getRequiredLength(encryptionKey);
				System.out.println(key);
				String plainText = randomPassword;
				advancedEncryptionStandard.setKey(key.getBytes(StandardCharsets.UTF_8));
				byte[] cipherText = advancedEncryptionStandard.encrypt(plainText.getBytes(StandardCharsets.UTF_8));
				byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
				System.out.println(new String(plainText));
				System.out.println(new String(cipherText));
				System.out.println(new String(decryptedCipherText));
				user.setDataCryptPassword(cipherText);
				user.setPublicAddress(response.getPublicAddress());
				usersDAO.insert(user);
				Users muser = this.findOne(user.getUsername());
				University university = new University(muser.getId(), muser.getName(), "True", formFieldDAO.findAll());
				universityDAO.insert(university);
				
				return user;
			}
		}
		else {
			return null; 
		}
		return null;
	}
	
	public void addMultipleUsers(List<Users> users) throws Exception {
		
		for(Users user: users) {
			addUser(user);
			
		}
		
	}

	public boolean findIfAvailable(String username) {
		Users temp = this.findOne(username);
		if(temp != null) return false;
		return true;
		
	}
	public ResponseStatus signUpDataCrypt(String userName, String password, String email) {
		try{
        	  RestTemplate restTemplate = new RestTemplate();
			  HttpHeaders headers = new HttpHeaders();
			  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			  
			  MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
			  map.add("username", userName);
			  map.add("password", password);
			  map.add("email", email);
			  
			  String url = "http://192.168.100.28:8090/user/createUser";
			  
			  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			  ResponseStatus response =  restTemplate.postForObject( url, request , ResponseStatus.class );
			  
			  System.out.println(response.toString());
		      if(response.getStatus().equals("true")){
		    	  System.out.println("Data Cypt user created");
		    	  return response;
		      }
		      else{
		    	  System.out.println("user not created");
		    	  return null;
		      }
	 } 	
		catch (ResourceAccessException e) {
	        System.out.println("Timed out");
	        return null;
	    }

		}
	public String randomPasswordGenerator(){
		
	
		char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
		String randomStr = RandomStringUtils.random( 24, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
		System.out.println( randomStr );
		return randomStr;
	}
	
	public String getRequiredLength(String a){
		
		while(a.length() < 17){
			a+='a';
		}
		return a.substring(0, 16);
	}
}
