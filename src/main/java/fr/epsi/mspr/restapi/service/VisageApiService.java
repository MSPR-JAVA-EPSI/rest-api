package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

public interface VisageApiService {

	boolean isValidUser(DtoInIdentification identification, Guardian guardian);
}