package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "guardian")
public class Guardian implements Serializable {

	private static final long serialVersionUID = 4584176077274887305L;
	
	@Id
	@Column(name="guardian_id")
	private long id;
	@Column(name="guardian_name")
	private String name;
	@Lob
	@Column(name="guardian_image")
	private byte[] image;
	@Column(name="guardian_token")
	private String token;
	@Column(name="guardian_admin")
	private boolean administrator;
	@OneToMany( mappedBy="guardian", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Borrow> borrow;
	
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
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
	public Set<Borrow> getBorrow() {
		return borrow;
	}
	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}
	public void addBorrow(Borrow b) {
		borrow.add(b);
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrator ? 1231 : 1237);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guardian other = (Guardian) obj;
		if (administrator != other.administrator)
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}