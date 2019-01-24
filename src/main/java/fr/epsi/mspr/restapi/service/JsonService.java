package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

public interface JsonService {

	DtoToken getDtoToken(String result);

	DtoInIdentification getDtoInIdentification(String data);

}
