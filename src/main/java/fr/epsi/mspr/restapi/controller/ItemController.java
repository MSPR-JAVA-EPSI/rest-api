package fr.epsi.mspr.restapi.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.BorrowService;
import fr.epsi.mspr.restapi.service.GuardianService;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.metier.dto.out.DtoOutEquipment;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private AuthService authService;
	@Autowired
	private GuardianService guardianService;

	@RequestMapping(value = "/item/getAll", produces = { MediaType.APPLICATION_JSON })
	public @ResponseBody ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String authorization) {
		if (authService.isValid(authorization)) {
			return ResponseEntity.ok(new DtoOutEquipment().setEquipments(itemService.getAll()));
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/item/borrow")
	public @ResponseBody ResponseEntity<?> borrowItem(@RequestHeader(value = "Authorization") String authorization, @RequestBody String body) {
		Guardian guardian = guardianService.getByToken(authorization);
		if (guardian == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return borrowService.borrow(body, guardian);
	}

	@RequestMapping(value = "/item/getBorrows")
	public @ResponseBody ResponseEntity<?> getBorrow(@RequestHeader(value = "Authorization") String authorization) {
		Guardian guardian = guardianService.getByToken(authorization);
		if (guardian == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(guardian.getBorrow());
	}
	
	@RequestMapping(value = "/item/returnBorrow")
	public @ResponseBody ResponseEntity<?> returnBorrow(@RequestHeader(value = "Authorization") String authorization, @RequestBody String body) {
		return borrowService.returnBorrow(authorization, body);
	}
}