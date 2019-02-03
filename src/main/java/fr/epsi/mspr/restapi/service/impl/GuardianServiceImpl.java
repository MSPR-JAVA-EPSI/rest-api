package fr.epsi.mspr.restapi.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.GuardianService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoGuardian;

@Service
public class GuardianServiceImpl implements GuardianService {

	@Autowired
	private GuardianRepository guardianRepository;
	@Autowired
	private JsonService jsonService;
	
	@Override
	public Guardian findById(long id) {
		return guardianRepository.findById(id).get();
	}

	@Override
	public void save(Guardian g) {
		guardianRepository.save(g);
	}
	
	@Override
	public Guardian getByToken(String token) {
		return guardianRepository.findByToken(token.split(" ")[1]);
	}

	@Override
	public ResponseEntity<?> getAll() {
		DtoGuardian dto = new DtoGuardian();
		dto.setGuardians(guardianRepository.findAll());
		return ResponseEntity.ok(dto);
	}

	@Override
	public ResponseEntity<?> add(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if(dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toSave = new ArrayList<>();
		for(Guardian guardian : dtoGuardian.getGuardians()) {
			if(guardian.getImageBase64()!=null && guardian.getName()!=null) {
				byte[] bytes = Base64.getDecoder().decode(guardian.getImageBase64().getBytes());
				guardian.setImage(bytes);
				guardian.setId(0);
				toSave.add(guardian);
			}
		}
		guardianRepository.saveAll(toSave);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> edit(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if(dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toEdit = new ArrayList<>();
		for(Guardian receivedGuardian : dtoGuardian.getGuardians()) {
			if(receivedGuardian.getImageBase64()!=null && receivedGuardian.getName()!=null) {
				Optional<Guardian> option = guardianRepository.findById(receivedGuardian.getId());
				if(option.isPresent()) {
					Guardian guardian = option.get();
					guardian.setAdministrator(receivedGuardian.isAdministrator());
					guardian.setName(receivedGuardian.getName());
					guardian.setImage(Base64.getDecoder().decode(receivedGuardian.getImageBase64().getBytes()));
					toEdit.add(guardian);
				}
			}
		}
		guardianRepository.saveAll(toEdit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> remove(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if(dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toDelete = new ArrayList<>();
		for(Guardian receivedGuardian : dtoGuardian.getGuardians()) {
			Optional<Guardian> option = guardianRepository.findById(receivedGuardian.getId());
			if(option.isPresent()) {
				toDelete.add(option.get());
			}
		}
		guardianRepository.deleteAll(toDelete);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
