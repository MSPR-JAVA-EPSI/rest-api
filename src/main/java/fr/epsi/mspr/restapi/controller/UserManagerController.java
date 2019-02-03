package fr.epsi.mspr.restapi.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagerController {

	@RequestMapping(value = "/admin/user/new")
    public @ResponseBody ResponseEntity<?> add(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
	
	@RequestMapping(value = "/admin/user/edit")
    public @ResponseBody ResponseEntity<?> edit(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
	
	@RequestMapping(value = "/admin/user/remove")
    public @ResponseBody ResponseEntity<?> remove(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
	
	@RequestMapping(value = "/admin/user/getAll", produces = { MediaType.APPLICATION_JSON })
    public @ResponseBody ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String authorization) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}