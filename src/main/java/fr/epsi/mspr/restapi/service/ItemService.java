package fr.epsi.mspr.restapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fr.epsi.mspr.restapi.dao.entity.Item;

public interface ItemService {

	List<Item> getAll();

	ResponseEntity<?> borrow(String body);

}
