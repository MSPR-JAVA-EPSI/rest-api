package fr.epsi.mspr.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService {

	@Autowired
	private GuardianRepository guardianRepository;
	
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
}
