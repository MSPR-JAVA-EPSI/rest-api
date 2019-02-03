package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {

	private static final long serialVersionUID = 6261766896435526999L;

	@EmbeddedId
	private BorrowId id;
	@ManyToOne()
	@MapsId("guardianId")
	@JoinColumn(name = "borrow_guardian", nullable = false, updatable = false, insertable = false)
	private Guardian guardian;
	@ManyToOne()
	@MapsId("itemId")
	@JoinColumn(name = "borrow_item", nullable = false, updatable = false, insertable = false)
	private Item item;
	@Column(name = "borrow_quantity")
	private int quantity = 0;

	public BorrowId getId() {
		return id;
	}

	public void setId(BorrowId id) {
		this.id = id;
	}

	public Guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(int i) {
		quantity += i;
	}
	
	public void removeQuantity(int i) {
		quantity -= i;
		if(quantity < 0) quantity = 0;
	}

	@PrePersist
	private void prePersiste() {
		if (getId() == null) {
			BorrowId pk = new BorrowId();
			pk.setBorrowGuardian(getGuardian().getId());
			pk.setBorrowItem(getItem().getId());
			setId(pk);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guardian == null) ? 0 : guardian.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + quantity;
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
		Borrow other = (Borrow) obj;
		if (guardian == null) {
			if (other.guardian != null)
				return false;
		} else if (!guardian.equals(other.guardian))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
