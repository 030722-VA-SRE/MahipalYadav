package com.revature.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Storemenu;

@Repository
public interface StoremenuRepository extends JpaRepository<Storemenu, Integer> {

	public Storemenu findStoremenuByItemname( String itemname);
	
	

}
