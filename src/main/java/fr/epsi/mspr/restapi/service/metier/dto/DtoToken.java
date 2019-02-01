package fr.epsi.mspr.restapi.service.metier.dto;

public class DtoToken {

	private String token;
	private boolean administrator;

	public DtoToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}
