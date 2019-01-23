package fr.epsi.mspr.restapi.service.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoToken;

@Service
public class JsonServiceImpl implements JsonService {

	private Gson gson;
	
	public JsonServiceImpl() {
		gson = new Gson();
	}
	
	@Override
	public DtoToken getDtoToken(String result) {
		return gson.fromJson(result, DtoToken.class);
	}
}