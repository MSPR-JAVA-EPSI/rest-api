package fr.epsi.mspr.restapi.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.BorrowService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;

@Service
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private JsonService jsonService;
	@Autowired
	private GuardianRepository guardianRepository;
	
	@Override
	public ResponseEntity<?> borrow(String data, Guardian guardian) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		if(dtoInBorrowItems.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> contenu invalide");
			return new ResponseEntity<>("Liste d'équipement inexistant", HttpStatus.NO_CONTENT);
		}
		for (Item item : dtoInBorrowItems.getEquipments()) {
			Borrow b = getBorrowForItem(item, guardian.getBorrow());
			b.setGuardian(guardian);
			b.setItem(item);
			b.addQuantity(item.getQuantity());
			guardian.addBorrow(b);
		}
		guardianRepository.save(guardian);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBorrows(Guardian guardian) {
		Set<Borrow> borrows = guardian.getBorrow();
		DtoInBorrowItems dtoInBorrowItems = new DtoInBorrowItems();
		for (Borrow borrow : borrows) {
			Item item = new Item();
			item.setId(borrow.getItem().getId());
			item.setName(borrow.getItem().getName());
			item.setQuantity(borrow.getQuantity());
			dtoInBorrowItems.addEquipment(item);
		}
		return ResponseEntity.ok(dtoInBorrowItems);
	}

	@Override
	public ResponseEntity<?> returnBorrows(String data, Guardian guardian) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		if(dtoInBorrowItems.getEquipments() == null) {
			System.out.println(this.getClass().getName() + "> contenu invalide");
			return new ResponseEntity<>("Liste d'équipement inexistant", HttpStatus.NO_CONTENT);
		}
		for (Item item : dtoInBorrowItems.getEquipments()) {
			Borrow b = getBorrowForItem(item, guardian.getBorrow());
			b.setGuardian(guardian);
			b.setItem(item);
			b.removeQuantity(item.getQuantity());
			guardian.addBorrow(b);
		}
		guardianRepository.save(guardian);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private Borrow getBorrowForItem(Item item, Set<Borrow> borrows) {
		Borrow b = new Borrow();
		for (Borrow borrow : borrows) {
			if(borrow.getItem().getId() == item.getId()) {
				b = borrow;
				break;
			}
		}
		return b;
	}
}