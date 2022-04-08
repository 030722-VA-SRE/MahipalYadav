package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	

	private UserRepository ur;
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public List<UserDTO> getAllUsers(){
		List<User> users = ur.findAll();

		List<UserDTO> uDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());

		return uDTO;
		
	}//end getAll
	
	
	public UserDTO getUserById(int id) throws UserNotFoundException{
		
		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
		log.info(MDC.get("userToken"));
		return new UserDTO(user);
	}//end
	
	@Transactional
	public boolean deleteUser(int id){
		
		ur.findById(id).orElseThrow(UserNotFoundException:: new);
		ur.deleteById(id);
		return true;
		
	}
	
	@Transactional
	public User createUser(User newUser) throws UserAlreadyExistException{
		
		//check if user exits
		User u = ur.findUserByUsername(newUser.getUsername());
		if(u != null) {
			throw new UserAlreadyExistException();
		}//end

		return ur.save(newUser);
	}//end createUser
	
	@Transactional
	public User updateUser(int id, User user){

		User u = ur.findById(id).orElseThrow(UserNotFoundException:: new);
		
		user.setId(u.getId());
		
		return ur.save(user);
	}
	
	public List<User> getUsersByRole(Role role){
		return ur.findUsersByRole(role);
	}

	
	
}