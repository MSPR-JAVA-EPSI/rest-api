package fr.epsi.mspr.restapi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.BorrowItem;
import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.BorrowRepository;
import fr.epsi.mspr.restapi.service.BorrowService;
import fr.epsi.mspr.restapi.service.GuardianService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;

public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private GuardianService guardianService;
	
	@Override
	public ResponseEntity<?> borrow(String data, Guardian guardian) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		Borrow borrow = new Borrow();
		borrow.setDate(new Date());
		borrow.setGuardian(guardian);
		for(Item item : dtoInBorrowItems.getEquipments()) {
			BorrowItem borrowItem = new BorrowItem();
			borrowItem.setItem(item);
			borrow.addBorrowItem(borrowItem);
		}
		borrowRepository.save(borrow);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> returnBorrow(String authorization, String body) {
		Guardian guardian = guardianService.getByToken(authorization);
		if(guardian == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
