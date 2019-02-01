package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Borrow implements Serializable {

	private static final long serialVersionUID = 6261766896435526999L;
	
	@Id
	@Column(name="borrow_id")
	private long id;
	@Column(name="borrow_date")
	private Date date;
	@Column(name="borrow_guardian")
	private Guardian guardian;
	@Column(name="borrow_return")
	private boolean isReturn = false;
	@OneToMany(mappedBy="borrow", cascade=CascadeType.ALL)
	private Set<BorrowItem> borrowItems;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Guardian getGuardian() {
		return guardian;
	}
	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}
	public boolean isReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public Set<BorrowItem> getBorrowItems() {
		return borrowItems;
	}
	public void setBorrowItems(Set<BorrowItem> borrowItems) {
		this.borrowItems = borrowItems;
	}
	public void addBorrowItem(BorrowItem borrowItem) {
		borrowItem.setBorrow(this);
	}
}
