package fr.epsi.mspr.restapi.service;

import org.springframework.http.ResponseEntity;

import fr.epsi.mspr.restapi.dao.entity.Item;

public interface ItemService {

	ResponseEntity<?> getAll();
	
	ResponseEntity<?> getAllAvailable();

	ResponseEntity<?> addNew(String result);

	ResponseEntity<?> remove(String result);

	ResponseEntity<?> edit(String result);
	
	boolean checkItemIsValid(Item item);

}
