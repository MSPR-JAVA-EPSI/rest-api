package fr.epsi.mspr.restapi.service.metier.dto;

public class DtoToken {

	private String token;

	public DtoToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
