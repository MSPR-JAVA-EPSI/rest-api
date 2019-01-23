package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;

public interface AuthService {

	String authentificateImage(String result);

	boolean isValid(DtoToken token);
	
	boolean isValid(String token);

}
