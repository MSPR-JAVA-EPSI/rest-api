package fr.epsi.mspr.restapi.dao.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item  implements Serializable {

	private static final long serialVersionUID = 838937981809482869L;
	
	@Id
	@Column(name="item_id")
	private long id;
	@Column(name="item_name")
	private String name;
	@Column(name="item_quantity")
	private int quantity;
	@OneToMany(mappedBy="item")
	private Set<BorrowItem> borrowItems;
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Set<BorrowItem> getBorrowItems() {
		return borrowItems;
	}
	public void setBorrowItems(Set<BorrowItem> borrowItems) {
		this.borrowItems = borrowItems;
	}
}