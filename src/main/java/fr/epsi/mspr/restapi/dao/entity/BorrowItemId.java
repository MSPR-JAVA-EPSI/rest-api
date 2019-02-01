package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BorrowItemId implements Serializable {

	private static final long serialVersionUID = -1631369975580793936L;
	
	@Column(name ="borrowitem_borrow")
	private long borrowId;
	@Column(name ="borrowitem_item")
	private long itemId;
	
	public long getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(long borrowId) {
		this.borrowId = borrowId;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (borrowId ^ (borrowId >>> 32));
		result = prime * result + (int) (itemId ^ (itemId >>> 32));
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
		BorrowItemId other = (BorrowItemId) obj;
		if (borrowId != other.borrowId)
			return false;
		if (itemId != other.itemId)
			return false;
		return true;
	}
}