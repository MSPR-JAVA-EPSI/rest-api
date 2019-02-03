package fr.epsi.mspr.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.BorrowService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;

@Service
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private JsonService jsonService;
	
	@Override
	public ResponseEntity<?> borrow(String data, Guardian guardian) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBorrows(Guardian guardian) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<?> returnBorrows(String body, Guardian guardian) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}
