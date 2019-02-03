package fr.epsi.mspr.restapi.service.metier.dto;

public class DtoGuardianEntity {

	private long id;
	private String name;
	private String image;
	private boolean administrator;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}
