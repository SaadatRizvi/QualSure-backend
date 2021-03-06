package com.qualsure.dataapi.controller;


import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qualsure.dataapi.config.JwtTokenUtil;
import com.qualsure.dataapi.model.AuthToken;
import com.qualsure.dataapi.model.LoginUser;
import com.qualsure.dataapi.model.Users;
import com.qualsure.dataapi.service.UsersService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsersService userService;

    @RequestMapping(value = "/token/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Users user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/users").path(
				"/{id}").buildAndExpand(user.getId()).toUri();
        
        headers.add("location", location.toString());
        
        return  ResponseEntity.ok()
        .headers(headers)
        .body(new AuthToken(token, location.toString() ));
        }
    
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> Signup(@RequestBody Users user) {
    	Users newUser= userService.addUser(user);
		 if (user == null)
				return ResponseEntity.noContent().build();
		 
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/users").path(
					"/{id}").buildAndExpand(newUser.getId()).toUri();

			return ResponseEntity.created(location).build();
		 
	}
    
    @GetMapping("/checkUsername/{username}")
	public Map<String, Boolean> getAllUsers(@PathVariable String username) {
    	return Collections.singletonMap("success", userService.findIfAvailable(username));
    }

    
    @GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.findAll();
	}

    @GetMapping("/users/{usersId}")
	public Users getUser(@PathVariable String usersId) {
		return userService.findById(usersId);
	}
}
