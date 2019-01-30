package fr.epsi.mspr.restapi.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Guardian {

	@Id
	@Column(name="guardian_id")
	private long guaId;
	@Column(name="guardian_name")
	private String guaName;
	@Lob
	@Column(name="guardian_image")
	private byte[] guaImage;
	@Column(name="guardian_token")
	private String guaToken;
	
	public long getGuaId() {
		return guaId;
	}
	public void setGuaId(long guaId) {
		this.guaId = guaId;
	}
	public String getGuaName() {
		return guaName;
	}
	public void setGuaName(String guaName) {
		this.guaName = guaName;
	}
	public byte[] getGuaImage() {
		return guaImage;
	}
	public void setGuaImage(byte[] img) {
		this.guaImage = img;
	}
	public String getGuaToken() {
		return guaToken;
	}
	public void setGuaToken(String guaToken) {
		this.guaToken = guaToken;
	}
	
}