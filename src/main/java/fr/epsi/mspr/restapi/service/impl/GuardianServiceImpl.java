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
import fr.epsi.mspr.restapi.service.metier.dto.DtoGuardianEntity;

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
		List<Guardian> guardians = guardianRepository.findAll();
		List<DtoGuardianEntity> dtoGuardians = new ArrayList<>();
		for (Guardian g : guardians) {
			DtoGuardianEntity dtoGuardian = getDtoGuardianEntityFromGuardian(g);
			dtoGuardians.add(dtoGuardian);
		}
		dto.setGuardians(dtoGuardians);
		return ResponseEntity.ok(dto);
	}

	private DtoGuardianEntity getDtoGuardianEntityFromGuardian(Guardian g) {
		DtoGuardianEntity dtoGuardian = new DtoGuardianEntity();
		dtoGuardian.setAdministrator(g.isAdministrator());
		dtoGuardian.setName(g.getName());
		dtoGuardian.setId(g.getId());
		dtoGuardian.setFullname(g.getFullname());
		return dtoGuardian;
	}

	@Override
	public ResponseEntity<?> add(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if (dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toSave = new ArrayList<>();
		for (DtoGuardianEntity receivedGuardian : dtoGuardian.getGuardians()) {
			if (receivedGuardian.getName() == null || receivedGuardian.getFullname() == null
					|| receivedGuardian.getImage() == null || receivedGuardian.getName().length() < 2
					|| receivedGuardian.getFullname().length() < 2 || receivedGuardian.getImage().length() < 2) {
				return new ResponseEntity<>("Une des données est vide", HttpStatus.NOT_ACCEPTABLE);
			} else {
				Guardian guardian = new Guardian();
				guardian.setName(receivedGuardian.getName());
				guardian.setFullname(receivedGuardian.getFullname());
				guardian.setImage(Base64.getDecoder().decode(receivedGuardian.getImage().getBytes()));
				toSave.add(guardian);
			}
		}
		guardianRepository.saveAll(toSave);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> edit(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if (dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toEdit = new ArrayList<>();
		for (DtoGuardianEntity receivedGuardian : dtoGuardian.getGuardians()) {
			Optional<Guardian> option = guardianRepository.findById(receivedGuardian.getId());
			if (option.isPresent()) {
				Guardian guardian = option.get();
				guardian.setAdministrator(receivedGuardian.isAdministrator());
				String name = receivedGuardian.getName();
				String fullname = receivedGuardian.getFullname();
				String image = receivedGuardian.getImage();
				if (name != null && name.length() > 1) {
					guardian.setName(name);
				}
				if (fullname != null && fullname.length() > 1) {
					guardian.setFullname(fullname);
				}
				if (image != null && image.length() > 1) {
					guardian.setImage(Base64.getDecoder().decode(image.getBytes()));
				}
				toEdit.add(guardian);
			} else {
				return new ResponseEntity<>("Gardian avec l'id " + receivedGuardian.getId() + " introuvable", HttpStatus.NOT_FOUND);
			}
		}
		guardianRepository.saveAll(toEdit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> remove(String result) {
		DtoGuardian dtoGuardian = jsonService.getDtoGuardian(result);
		if (dtoGuardian == null || dtoGuardian.getGuardians() == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return new ResponseEntity<>("Mauvais format JSON", HttpStatus.BAD_REQUEST);
		}
		List<Guardian> toDelete = new ArrayList<>();
		for (DtoGuardianEntity receivedGuardian : dtoGuardian.getGuardians()) {
			Optional<Guardian> option = guardianRepository.findById(receivedGuardian.getId());
			if (option.isPresent()) {
				toDelete.add(option.get());
			}
		}
		guardianRepository.deleteAll(toDelete);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
