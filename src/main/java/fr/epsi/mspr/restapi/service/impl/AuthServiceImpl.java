package fr.epsi.mspr.restapi.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private GuardianRepository<?> guardianRepository;
	
	@Override
	public boolean isValid(String token) {
		return guardianRepository.findByGuaToken(token.split(" ")[1]) != null;
	}

	@Override
	public String authentificateImage(String result) {
		return UUID.randomUUID().toString();
	}
}