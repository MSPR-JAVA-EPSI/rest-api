package fr.epsi.mspr.restapi.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public boolean isValid(DtoToken token) {
		return isValid(token.getToken());
	}
	
	@Override
	public boolean isValid(String token) {
		System.out.println(token);
		return true;
	}

	@Override
	public String authentificateImage(String result) {
		return UUID.randomUUID().toString();
	}
}