package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.dtos.StoremenuDTO;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.StoremenuAlreadyExistException;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Storemenu;
import com.revature.models.User;
import com.revature.repositories.StoremenuRepository;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class TestStoremenuService {
	
	static StoremenuRepository sRep;
	static StoremenuService sSer;
	static List<Storemenu> menus = new ArrayList<>();
	static Storemenu m1;

	@BeforeAll
	public static void set() {
		sRep = mock(StoremenuRepository.class); 
		sSer = new StoremenuService(sRep); 
		m1 = new Storemenu();
		menus.add(m1);
	}
	
	@Test 
	public void getAllUserTest() {
		when(sRep.findAll()).thenReturn(menus);
		Assertions.assertEquals(menus, sSer.getAllStoremenu());
	}
	
	@Test 
	public void getUserByIdTest(){
		when(sRep.findById(1)).thenReturn(Optional.of(m1)); 
		Assertions.assertEquals(m1, sSer.getStoremenuById(1)); 
	}
	
	@Test
	public void createUserTest() throws StoremenuAlreadyExistException {
		when(sRep.findById(m1.getId())).thenReturn(Optional.empty());
		when(sRep.save(m1)).thenReturn(m1);
		assertEquals(m1, sSer.createStoremenu(m1));
	}
	
	
	@Test
	public void updateUserTest() throws UserNotFoundException{
		when(sRep.findById(1)).thenReturn(Optional.of(m1));
		when(sRep.save(m1)).thenReturn(m1);
		assertEquals(m1, sSer.updateStoremenu(1, m1));
	}
	
	@Test 
	public void deleteUserTest() {
		when(sRep.findById(1)).thenReturn(Optional.of(m1));
		doNothing().when(sRep).deleteById(1);
		sSer.deleteStoremenuById(1);
		Mockito.verify(sRep).deleteById(1);
	}
}
