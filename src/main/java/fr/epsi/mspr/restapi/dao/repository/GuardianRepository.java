package fr.epsi.mspr.restapi.dao.repository;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface GuardianRepository extends CustomRepository<Guardian, Long> {

	Guardian findByToken(String guaToken);
	
	Guardian findByName(String guaName);
}