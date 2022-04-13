package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class TestUserService {
	static UserRepository uRep; 
	static UserService uSer; 
	static Role role; 
	static List<User> users = new ArrayList<>(); 	
	static User u1; 
	
	
	@BeforeAll
	public static void set() {
		uRep = mock(UserRepository.class); 
		uSer = new UserService(uRep); 
		u1 = new User();
	}
	
	@Test 
	public void getAllUserTest() {
		when(uRep.findAll()).thenReturn(users);
		Assertions.assertEquals(users, uSer.getUsers());
	}
	
	@Test 
	public void getUserByIdTest() throws UserNotFoundException {
		when(uRep.findById(1)).thenReturn(Optional.of(u1)); 
		Assertions.assertEquals(new UserDTO(u1), uSer.getUserById(1)); 
	}
	
	@Test
	public void createUserTest() throws UserAlreadyExistException {
		when(uRep.save(u1)).thenReturn(u1);
		assertEquals(u1, uSer.createUser(u1));
	}
	
	@Test
	public void updateUserTest() throws UserNotFoundException{
		when(uRep.findById(1)).thenReturn(Optional.of(u1));
		when(uRep.save(u1)).thenReturn(u1);
		assertEquals(u1, uSer.updateUser(1, u1));
	}
	
	@Test 
	public void deleteUserTest() throws UserNotFoundException {
		when(uRep.findById(1)).thenReturn(Optional.of(u1));
		assertEquals(true, uSer.deleteUser(1));
	}

	
}