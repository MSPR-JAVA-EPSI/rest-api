package fr.epsi.mspr.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.BorrowRepository;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;
import fr.epsi.mspr.restapi.service.metier.dto.DtoItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private JsonService jsonService;
	@Autowired
	private BorrowRepository borrowRepository;

	@Override
	public ResponseEntity<?> addNew(String result) {
		DtoItem dtoItem = jsonService.getDtoItem(result);
		if(dtoItem == null || dtoItem.getItem() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		if(!checkItemIsValid(dtoItem.getItem())) {
			return new ResponseEntity<>("Données invalides", HttpStatus.PARTIAL_CONTENT);
		}
		dtoItem.getItem().setId(0);
		Item resultItem = itemRepository.save(dtoItem.getItem());
		if(resultItem == null) {
			return new ResponseEntity<>("Ajout impossible en base", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private boolean checkItemIsValid(Item item) {
		final String name = item.getName();
		if(name == null || name.length() == 0 || name.length() > 30) {
			System.out.println(this.getClass().getName() + "> bad name content");
			return false;
		}
		final int quantity = item.getQuantity();
		if(quantity <= 0 || quantity > 100000) {
			System.out.println(this.getClass().getName() + "> bad quantity content");
			return false;
		}
		return true;
	}

	@Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(new DtoEquipment().setEquipments(itemRepository.findAll()));
	}

	@Override
	public ResponseEntity<?> getAllAvailable() {
		List<Item> items = itemRepository.findAll();
		List<Borrow> borrows = borrowRepository.findAll();
		for (Item item : items) {
			for(Borrow borrow : borrows) {
				if(borrow.getItem().getId() == item.getId()) {
					item.removeQuantity(borrow.getItem().getQuantity());
				}
			}
		}
		return ResponseEntity.ok(new DtoEquipment().setEquipments(items));
	}

	@Override
	public ResponseEntity<?> remove(String result) {
		DtoEquipment dtoEquipment = jsonService.getDtoEquipment(result);
		if(dtoEquipment == null || dtoEquipment.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		for(Item receivedItem : dtoEquipment.getEquipments()) {
			Optional<Item> optionamItem = itemRepository.findById(receivedItem.getId());
			if(optionamItem.isPresent()) {
				Item item = optionamItem.get();
				itemRepository.delete(item);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> edit(String result) {
		DtoEquipment dtoEquipment = jsonService.getDtoEquipment(result);
		if(dtoEquipment == null || dtoEquipment.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		for(Item receivedItem : dtoEquipment.getEquipments()) {
			Optional<Item> optionamItem = itemRepository.findById(receivedItem.getId());
			if(optionamItem.isPresent()) {
				Item item = optionamItem.get();
				item.setName(receivedItem.getName());
				item.setQuantity(receivedItem.getQuantity());
				itemRepository.save(item);
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
