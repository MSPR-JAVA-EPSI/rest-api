package fr.epsi.mspr.restapi.service.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoItem;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class JsonServiceImpl implements JsonService {

	private Gson gson;
	
	public JsonServiceImpl() {
		gson = new Gson();
	}
	
	@Override
	public DtoToken getDtoToken(String data) {
		try { return gson.fromJson(data, DtoToken.class);
		} catch(Exception ex) {return null;}
	}

	@Override
	public DtoInIdentification getDtoInIdentification(String data) {
		try { return gson.fromJson(data, DtoInIdentification.class);
		} catch(Exception ex) {return null;}
	}

	@Override
	public DtoInBorrowItems getDtoInBorrowItems(String data) {
		try { return gson.fromJson(data, DtoInBorrowItems.class);
		} catch(Exception ex) {return null;}
	}

	@Override
	public DtoItem getDtoItem(String data) {
		try { return gson.fromJson(data, DtoItem.class);
		} catch(Exception ex) {return null;}
	}
}