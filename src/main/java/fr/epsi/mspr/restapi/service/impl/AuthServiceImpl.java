package fr.epsi.mspr.restapi.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private GuardianRepository<Guardian> guardianRepository;
	@Autowired
	private JsonService jsonService;
	
	@Override
	public boolean isValid(String token) {
		return guardianRepository.findByGuaToken(token.split(" ")[1]) != null;
	}

	@Override
	public String authentificateImage(String data) {
		DtoInIdentification identification = jsonService.getDtoInIdentification(data);
		Guardian guardian = guardianRepository.findByGuaName(identification.getIdentifier());
		if(guardian == null) return null;
		boolean callApiResult = true;
		if(!callApiResult) return null;
		guardian.setGuaToken(UUID.randomUUID().toString());
		guardianRepository.save(guardian);
		return guardian.getGuaToken();
	}
}