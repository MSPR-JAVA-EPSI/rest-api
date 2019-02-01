package fr.epsi.mspr.restapi.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import fr.epsi.mspr.restapi.dao.entity.Item;

public interface ItemService {

	List<Item> getAll();

	HttpStatus borrow(String body);

}
