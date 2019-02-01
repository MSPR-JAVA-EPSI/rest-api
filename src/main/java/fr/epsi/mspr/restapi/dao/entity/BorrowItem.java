package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class BorrowItem implements Serializable {

	private static final long serialVersionUID = -3860847751219232507L;
	
	@EmbeddedId
	private BorrowItemId id;
	@ManyToOne()
	@MapsId("itemId")
	private Item item;
	@ManyToOne()
	@MapsId("borrowId")
	private Borrow borrow;
	@Column(name="borrowitem_quantity")
	private int quantity;
	
	public BorrowItemId getId() {
		return id;
	}
	public void setId(BorrowItemId id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		if(this.item != null) {
			this.item.getBorrowItems().remove(this);
		}
		this.item = item;
		if(this.item != null) {
			this.item.getBorrowItems().add(this);
		}
	}
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		if(this.borrow != null) {
			this.borrow.getBorrowItems().remove(this);
		}
		this.borrow = borrow;
		if(this.borrow != null) {
			this.borrow.getBorrowItems().add(this);
		}
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
