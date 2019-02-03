package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BorrowId implements Serializable {

	private static final long serialVersionUID = 4120280281908382410L;

	@Column(name="borrow_guardian")
	private long borrowGuardian;
	@Column(name="borrow_item")
	private long borrowItem;
	
	public long getBorrowGuardian() {
		return borrowGuardian;
	}
	public void setBorrowGuardian(long borrowGuardian) {
		this.borrowGuardian = borrowGuardian;
	}
	public long getBorrowItem() {
		return borrowItem;
	}
	public void setBorrowItem(long borrowItem) {
		this.borrowItem = borrowItem;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (borrowGuardian ^ (borrowGuardian >>> 32));
		result = prime * result + (int) (borrowItem ^ (borrowItem >>> 32));
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
		BorrowId other = (BorrowId) obj;
		if (borrowGuardian != other.borrowGuardian)
			return false;
		if (borrowItem != other.borrowItem)
			return false;
		return true;
	}	
}
