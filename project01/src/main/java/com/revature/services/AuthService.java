package com.revature.services;


import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {
	
	private UserRepository ur;
	
	private static Logger log = LoggerFactory.getLogger(AuthService.class);

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


