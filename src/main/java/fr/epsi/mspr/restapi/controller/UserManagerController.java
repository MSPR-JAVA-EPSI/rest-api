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
import fr.epsi.mspr.restapi.service.GuardianService;

@RestController
public class UserManagerController {

	@Autowired
	private AuthService authService;
	@Autowired
	private GuardianService guardianService;
	
	@RequestMapping(value = "/admin/user/new")
    public @ResponseBody ResponseEntity<?> add(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return guardianService.add(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/user/edit")
    public @ResponseBody ResponseEntity<?> edit(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return guardianService.edit(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/user/remove")
    public @ResponseBody ResponseEntity<?> remove(@RequestHeader(value = "Authorization") String authorization, @RequestBody String result) {
		if (authService.isValidAndAdmin(authorization)) {
			return guardianService.remove(result);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/admin/user/getAll", produces = { MediaType.APPLICATION_JSON })
    public @ResponseBody ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String authorization) {
		if (authService.isValidAndAdmin(authorization)) {
			return guardianService.getAll();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
