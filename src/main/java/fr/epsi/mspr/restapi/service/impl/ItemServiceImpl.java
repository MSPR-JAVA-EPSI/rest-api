package fr.epsi.mspr.restapi.service.impl;

import java.util.ArrayList;
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

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private JsonService jsonService;
	@Autowired
	private BorrowRepository borrowRepository;
	
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
		List<Item> items = itemRepository.findAllByEnable(true);
		List<Borrow> borrows = borrowRepository.findAll();
		for (Item item : items) {
			for(Borrow borrow : borrows) {
				if(borrow.getItem().getId() == item.getId()) {
					item.removeQuantity(borrow.getQuantity());
				}
			}
		}
		List<Item> availableItems = new ArrayList<>();
		return ResponseEntity.ok(new DtoEquipment().setEquipments(availableItems));
	}
	
	@Override
	public ResponseEntity<?> addNew(String result) {
		DtoEquipment dtoEquipment = jsonService.getDtoEquipment(result);
		if(dtoEquipment == null || dtoEquipment.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Item> toSave = new ArrayList<>();
		for(Item item : dtoEquipment.getEquipments()) {
			if(checkItemIsValid(item)) {
				item.setId(0);
				item.setEnable(true);
				toSave.add(item);
			}
		}
		itemRepository.saveAll(toSave);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> edit(String result) {
		DtoEquipment dtoEquipment = jsonService.getDtoEquipment(result);
		if(dtoEquipment == null || dtoEquipment.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Item> toEdit = new ArrayList<>();
		for(Item receivedItem : dtoEquipment.getEquipments()) {
			Optional<Item> optionalItem = itemRepository.findById(receivedItem.getId());
			if(optionalItem.isPresent()) {
				Item item = optionalItem.get();
				item.setName(receivedItem.getName());
				item.setQuantity(receivedItem.getQuantity());
				toEdit.add(item);
			}
		}
		itemRepository.saveAll(toEdit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> remove(String result) {
		DtoEquipment dtoEquipment = jsonService.getDtoEquipment(result);
		if(dtoEquipment == null || dtoEquipment.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Item> toEdit = new ArrayList<>();
		for(Item receivedItem : dtoEquipment.getEquipments()) {
			Optional<Item> optionalItem = itemRepository.findById(receivedItem.getId());
			if(optionalItem.isPresent()) {
				Item item = optionalItem.get();
				item.setEnable(false);
				toEdit.add(item);
			}
		}
		itemRepository.saveAll(toEdit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
