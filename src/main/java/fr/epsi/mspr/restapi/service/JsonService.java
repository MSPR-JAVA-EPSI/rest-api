package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;
import fr.epsi.mspr.restapi.service.metier.dto.DtoGuardian;
import fr.epsi.mspr.restapi.service.metier.dto.DtoInBorrowItems;
import fr.epsi.mspr.restapi.service.metier.dto.DtoInIdentification;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;

public interface JsonService {

	DtoToken getDtoToken(String result);

	DtoInIdentification getDtoInIdentification(String data);

	DtoInBorrowItems getDtoInBorrowItems(String data);

	DtoEquipment getDtoEquipment(String result);

	DtoGuardian getDtoGuardian(String result);

}
