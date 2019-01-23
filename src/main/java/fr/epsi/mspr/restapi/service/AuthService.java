package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

public interface AuthService {

	String authentificateImage(DtoInIdentification identifier);

	boolean isValid(DtoToken token);

}
