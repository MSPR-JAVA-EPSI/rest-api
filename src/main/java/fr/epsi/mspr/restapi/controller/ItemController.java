package fr.epsi.mspr.restapi.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.out.DtoOutEquipment;

@RestController
public class ItemController {

	@Autowired
    private ItemService itemService;
	
	@Autowired
    private JsonService jsonService;

	@RequestMapping(value = "/item/getAll", produces = { MediaType.APPLICATION_JSON })
    public @ResponseBody ResponseEntity<?> getAll(@RequestBody String result) {
		
		return ResponseEntity.ok(new DtoOutEquipment().setEquipements(itemService.getAll()));
    }
	
	@RequestMapping(value = "/item/borrow")
    public @ResponseBody ResponseEntity<?> borrowItem(@RequestBody String result) {
		return ResponseEntity.ok(null);
    }
}