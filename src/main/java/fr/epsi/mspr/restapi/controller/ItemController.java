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

import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.metier.dto.out.DtoOutEquipment;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/item/getAll", produces = { MediaType.APPLICATION_JSON })
	public @ResponseBody ResponseEntity<?> getAll(@RequestHeader(value="Authorization") String authorization, @RequestBody String body) {
		if (authService.isValid(authorization)) {
			return ResponseEntity.ok(new DtoOutEquipment().setEquipements(itemService.getAll()));
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/item/borrow")
	public @ResponseBody ResponseEntity<?> borrowItem(@RequestHeader(value="Authorization") String authorization, @RequestBody String body) {
		if (authService.isValid(authorization)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}