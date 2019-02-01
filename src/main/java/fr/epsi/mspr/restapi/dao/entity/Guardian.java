package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Guardian implements Serializable {

	private static final long serialVersionUID = 4584176077274887305L;
	
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
	@Column(name="guardian_admin")
	private boolean administrator;
	@OneToMany(mappedBy="guardian")
	private Set<Borrow> borrow;
	
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
	public void setGuaImage(byte[] guaImage) {
		this.guaImage = guaImage;
	}
	public String getGuaToken() {
		return guaToken;
	}
	public void setGuaToken(String guaToken) {
		this.guaToken = guaToken;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public Set<Borrow> getBorrow() {
		return borrow;
	}
	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}
}