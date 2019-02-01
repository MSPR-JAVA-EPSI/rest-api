package fr.epsi.mspr.restapi.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.VisageApiService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private GuardianRepository<Guardian> guardianRepository;
	@Autowired
	private JsonService jsonService;
	@Autowired
	private VisageApiService visageApiService;
	
	@Override
	public boolean isValid(String token) {
		return guardianRepository.findByGuaToken(token.split(" ")[1]) != null;
	}

	@Override
	public ResponseEntity<?> authentificateImage(String data) {
		DtoInIdentification identification = jsonService.getDtoInIdentification(data);
		if(identification == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Guardian guardian = guardianRepository.findByGuaName(identification.getIdentifier());
		if(guardian == null) {
			System.out.println(this.getClass().getName() + "> guardian not found");
			new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if(visageApiService.isValidUser(identification, guardian)) {
			guardian.setGuaToken(UUID.randomUUID().toString());
			guardianRepository.save(guardian);
			return ResponseEntity.ok(new DtoToken(guardian.getGuaToken()));
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}