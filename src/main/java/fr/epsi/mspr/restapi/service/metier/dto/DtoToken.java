package fr.epsi.mspr.restapi.service.metier.dto;

public class DtoToken {

	private String token;
	private boolean administrator;
	private String fullname;
	private String image;

	public DtoToken(String token, String fullname, String image) {
		this.token = token;
		this.fullname = fullname;
		this.image = image;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
