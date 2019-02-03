package fr.epsi.mspr.restapi.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {

	ResponseEntity<?> authentificateImage(String result);
	
	boolean isValid(String token);

	boolean isValidAndAdmin(String authorization);

}
