package fr.epsi.mspr.restapi.dao.repository;

import java.util.Set;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface BorrowRepository extends CustomRepository<Borrow, Long> {

	Set<Borrow> findByGuardian(Guardian guardian);

}