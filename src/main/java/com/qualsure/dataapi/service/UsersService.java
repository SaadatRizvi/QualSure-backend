package com.qualsure.dataapi.service;

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
import com.qualsure.dataapi.model.University;
import com.qualsure.dataapi.model.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public Users findById(String id) {
		return usersDAO.findOne(id);
	}

	public Users save(Users user) {
        return usersDAO.save(user);
    }
	public Users addUser(Users user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));

		usersDAO.insert(user);
		Users muser = this.findOne(user.getUsername());
		University university = new University(muser.getId(), muser.getName(), "True", formFieldDAO.findAll());

		universityDAO.insert(university);

		return user;
	}
	
	public void addMultipleUsers(List<Users> users) {
		
		for(Users user: users) {
			usersDAO.insert(user);
			Users muser = this.findOne(user.getUsername());
			University university = new University(muser.getId(), muser.getName(), "True", formFieldDAO.findAll());

			universityDAO.insert(university);
		}
		
	}

	public boolean findIfAvailable(String username) {
		Users temp = this.findOne(username);
		if(temp != null) return false;
		return true;
		
	}

}
