package fr.epsi.mspr.restapi.service.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class JsonServiceImpl implements JsonService {

	private Gson gson;
	
	public JsonServiceImpl() {
		gson = new Gson();
	}
	
	@Override
	public DtoToken getDtoToken(String data) {
		return gson.fromJson(data, DtoToken.class);
	}

	@Override
	public DtoInIdentification getDtoInIdentification(String data) {
		return gson.fromJson(data, DtoInIdentification.class);
	}
}