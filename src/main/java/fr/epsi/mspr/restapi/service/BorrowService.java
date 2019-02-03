package fr.epsi.mspr.restapi.service;

import org.springframework.http.ResponseEntity;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public interface BorrowService {

	ResponseEntity<?> borrow(String body, Guardian guardian);

	ResponseEntity<?> getBorrows(Guardian guardian);

	ResponseEntity<?> returnBorrows(String body, Guardian guardian);

}
