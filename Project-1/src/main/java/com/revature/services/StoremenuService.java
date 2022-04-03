package com.revature.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.StoremenuAlreadyExistException;
import com.revature.exceptions.StoremenuNotFoundException;
import com.revature.models.Storemenu;
import com.revature.repositories.StoremenuRepository;

@Service
public class StoremenuService {
	
	private StoremenuRepository sr;

	@Autowired
	public StoremenuService(StoremenuRepository sr) {
		super();
		this.sr = sr;
	}
	
	//Get all list 
	public List<Storemenu> getAllStoremenu(){
		return sr.findAll();
	}
	
	//Get value by Id
	public  Storemenu getStoremenuById(int id) {
		Storemenu storemenu = sr.findById(id).orElseThrow(StoremenuNotFoundException::new);
		return storemenu;
	}
		
	
	//update store menu by id
	@Transactional
	public Storemenu updateStoremenu(int id, Storemenu storemenu) {
		Storemenu s = sr.findById(id).orElseThrow(StoremenuNotFoundException:: new);
		storemenu.setId(s.getId());
		
		return sr.save(storemenu); 
	}//end
	
	
	@Transactional
	public void deleteStoremenuById(int id){
		
		sr.findById(id).orElseThrow(StoremenuNotFoundException:: new);
		
		sr.deleteById(id);
	}
	
	@Transactional
	public Storemenu createStoremenu(Storemenu menu) throws StoremenuAlreadyExistException{
		
		//check if storemenu exits
		Optional<Storemenu> m = sr.findById(menu.getId());
		if(m.isPresent()) {
			throw new StoremenuAlreadyExistException();
		}//end

		return sr.save(menu);
	}//end createstoremenu
	
	

}
