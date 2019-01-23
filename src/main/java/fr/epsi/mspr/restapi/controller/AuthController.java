package fr.epsi.mspr.restapi.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;

@RestController
public class AuthController {

	@Autowired
    private AuthService authService;

	@RequestMapping(value = "/auth", produces = { MediaType.APPLICATION_JSON })
    public @ResponseBody ResponseEntity<?> auth(@RequestBody String result) {
		String token = authService.authentificateImage(result);
		return ResponseEntity.ok(new DtoToken(token));
    }
}