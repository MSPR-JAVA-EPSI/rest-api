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

@RestController
public class ItemManagerController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/admin/item/new")
    public @ResponseBody ResponseEntity<?> add(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return itemService.addNew(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/item/edit")
    public @ResponseBody ResponseEntity<?> edit(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return itemService.edit(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/item/remove")
    public @ResponseBody ResponseEntity<?> remove(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return itemService.remove(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/item/getAll", produces = { MediaType.APPLICATION_JSON })
    public @ResponseBody ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String authorization) {
		if (authService.isValidAndAdmin(authorization)) {
			return itemService.getAll();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
