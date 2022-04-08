package com.revature.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repositories.UserRepository;

import com.revature.dtos.UserDTO;
import com.revature.models.User;


@Service
public class AuthService {
	
	private UserRepository ur;
	
	private static Logger log = LoggerFactory.getLogger(AuthService.class);
	
	
@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
public UserDTO login(String username, String password) {

	User principal = ur.findUserByUsername(username);
	
	
	if(principal == null || !password.equals(principal.getPassword())) {
	
		return null;
	}
	
    log.info("User succesfully logged in: id" + principal.getId()+ " name: "+ principal.getUsername());
	return new UserDTO(principal);
}



public String generateToken(UserDTO principal) {
	
	return principal.getId()+":"+principal.getUsername();
}



}

