package com.revature.controllers;



import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.TestUserService;

@RestController
@RequestMapping("/users")
public class UserController {
 
	private TestUserService us;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


	
	@Autowired
	public UserController(TestUserService us) {
		super();
		this.us = us;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers (@RequestParam(name = "role", required = false) String role) {
		
		if(role != null) {
			return new ResponseEntity<>(us.getUsersByRole(Role.valueOf(role)), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {

		// this just checks if the token is null, not if it has the right value
		if (token == null) {
			LOG.warn("tried to access endpoint /users/id");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		MDC.put("userToken", token);
		UserDTO u = us.getUserById(id);
		MDC.clear();
		return new ResponseEntity<>(u, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user) throws UserAlreadyExistException {
		User u = us.createUser(user);
		return new ResponseEntity<>("User " + u.getUsername() + "has been created.", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		return new ResponseEntity<>(us.updateUser(id, user), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeleteById(@PathVariable("id") int id) {
		us.deleteUser(id);
		return new ResponseEntity<>("User was deleted", HttpStatus.OK);
	}
	
}
