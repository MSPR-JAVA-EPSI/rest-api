package fr.epsi.mspr.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository<Item> itemRepository;
	@Autowired
	private JsonService jsonService;
	
	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	@Override
	public ResponseEntity<?> borrow(String data) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		System.out.println(this.getClass().getName() + "> " + dtoInBorrowItems.getEquipments().size());
		for (Item item : dtoInBorrowItems.getEquipments()) {
			System.out.println(this.getClass().getName() + "> " + item);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
