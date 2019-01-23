package fr.epsi.mspr.restapi.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Guardian {

	@Id
	@Column(name="guardian_id")
	private long guaId;
	@Column(name="guardian_name")
	private String guaName;
	@Column(name="guardian_image")
	private String guaQuantity;
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
	public String getGuaQuantity() {
		return guaQuantity;
	}
	public void setGuaQuantity(String guaQuantity) {
		this.guaQuantity = guaQuantity;
	}
	public String getGuaToken() {
		return guaToken;
	}
	public void setGuaToken(String guaToken) {
		this.guaToken = guaToken;
	}	
}