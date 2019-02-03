package fr.epsi.mspr.restapi.service;

import org.springframework.http.ResponseEntity;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface GuardianService {

	Guardian findById(long id);

	void save(Guardian g);

	Guardian getByToken(String token);

	ResponseEntity<?> getAll();

	ResponseEntity<?> add(String result);

	ResponseEntity<?> edit(String result);

	ResponseEntity<?> remove(String result);
}
