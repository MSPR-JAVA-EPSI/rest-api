package fr.epsi.mspr.restapi.service;

public interface AuthService {

	String authentificateImage(String result);
	
	boolean isValid(String token);

}
