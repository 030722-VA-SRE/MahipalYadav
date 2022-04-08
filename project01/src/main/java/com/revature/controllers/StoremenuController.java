package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.StoremenuAlreadyExistException;
import com.revature.models.Storemenu;
import com.revature.services.StoremenuService;

@RestController
@RequestMapping("/menu")
public class StoremenuController {

	private StoremenuService ss;
	
	private static final Logger LOG = LoggerFactory.getLogger(StoremenuController.class);

	@Autowired
	public StoremenuController(StoremenuService ss) {
		super();
		this.ss = ss;
	}

	@GetMapping
	public ResponseEntity<List<Storemenu>> getAllStoremenu() {
		LOG.info("Cards retrieved.");
		return new ResponseEntity<>(ss.getAllStoremenu(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Storemenu> getById(@PathVariable("id") int id) {

		return new ResponseEntity<>(ss.getStoremenuById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Storemenu> updateStoremenu(@RequestBody Storemenu menu, @PathVariable("id") int id) {
		return new ResponseEntity<>(ss.updateStoremenu(id, menu), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		ss.deleteStoremenuById(id);
		return new ResponseEntity<>("Already deleted", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createStoremenu(@RequestBody Storemenu menu) throws StoremenuAlreadyExistException {
		Storemenu m = ss.createStoremenu(menu);
		return new ResponseEntity<>("Storemenu " + m.getItemname() + " is done", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/storemenu/{id}")
	public ResponseEntity<Storemenu> getStoremenuById(@PathVariable("id") int id){
		return new ResponseEntity<>(ss.getStoremenuById(id), HttpStatus.OK);
	}//end
	
	
}

