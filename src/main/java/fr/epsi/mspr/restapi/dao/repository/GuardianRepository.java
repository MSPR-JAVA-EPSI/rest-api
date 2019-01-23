package fr.epsi.mspr.restapi.dao.repository;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface GuardianRepository<E> extends CustomRepository<Guardian, Long> {

	E findByGuaToken(String guaToken);
}