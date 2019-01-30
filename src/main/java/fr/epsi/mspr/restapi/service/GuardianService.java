package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface GuardianService {

	Guardian findById(long id);

	void save(Guardian g);
}
