//package com.qualsure.dataapi.service;
//
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.qualsure.dataapi.dao.UsersDAO;
//import com.qualsure.dataapi.model.UniversityLoginDetails;
//import com.qualsure.dataapi.model.Users;
//
//@Service
//public class UniversityLoginDetailsService  implements UserDetailsService{
//
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private UsersDAO userDAO;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		log.info("im here"+username);
//		Optional<Users> optionalUsers = userDAO.findByUsername(username);
//		
//		optionalUsers
//		.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//		
//		log.info(optionalUsers.map(UniversityLoginDetails::new).get().getName());
//		
//		
//		return optionalUsers
//		.map(UniversityLoginDetails::new).get();
//	}
//
//}
