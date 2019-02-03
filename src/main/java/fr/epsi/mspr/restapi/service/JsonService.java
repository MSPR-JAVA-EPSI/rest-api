package fr.epsi.mspr.restapi.service;

import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;
import fr.epsi.mspr.restapi.service.metier.dto.DtoGuardian;
import fr.epsi.mspr.restapi.service.metier.dto.DtoItem;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

public interface JsonService {

	DtoToken getDtoToken(String result);

	DtoInIdentification getDtoInIdentification(String data);

	DtoInBorrowItems getDtoInBorrowItems(String data);

	DtoItem getDtoItem(String result);

	DtoEquipment getDtoEquipment(String result);

	DtoGuardian getDtoGuardian(String result);

}
