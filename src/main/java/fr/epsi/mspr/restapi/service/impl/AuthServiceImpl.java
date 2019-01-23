package fr.epsi.mspr.restapi.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class AuthServiceImpl implements AuthService {

	public String authentificateImage(DtoInIdentification identifier) {
		return UUID.randomUUID().toString();
	}

	public boolean isValid(DtoToken token) {
		return true;
	}
}