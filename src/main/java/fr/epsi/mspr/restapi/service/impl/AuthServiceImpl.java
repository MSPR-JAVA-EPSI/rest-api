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
	private GuardianRepository guardianRepository;
	@Autowired
	private JsonService jsonService;
	@Autowired
	private VisageApiService visageApiService;
	
	@Override
	public boolean isValid(String token) {
		return guardianRepository.findByToken(token.split(" ")[1]) != null;
	}

	@Override
	public ResponseEntity<?> authentificateImage(String data) {
		DtoInIdentification identification = jsonService.getDtoInIdentification(data);
		if(identification == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		Guardian guardian = guardianRepository.findByName(identification.getId());
		if(guardian == null) {
			System.out.println(this.getClass().getName() + "> guardian not found");
			return new ResponseEntity<>("Ce gardien est introuvable", HttpStatus.NO_CONTENT);
		}
		if(visageApiService.isValidUser(identification, guardian)) {
			guardian.setToken(UUID.randomUUID().toString());
			guardianRepository.save(guardian);
			return ResponseEntity.ok(new DtoToken(guardian.getToken()));
		}
		return new ResponseEntity<>("Aucune correspondance trouvée pour ce gardien", HttpStatus.NO_CONTENT);
	}

	@Override
	public boolean isValidAndAdmin(String token) {
		Guardian g = guardianRepository.findByToken(token.split(" ")[1]);
		return g != null && g.isAdministrator();
	}
}