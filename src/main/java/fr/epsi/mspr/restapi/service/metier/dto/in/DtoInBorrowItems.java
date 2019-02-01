package fr.epsi.mspr.restapi.service.metier.dto.in;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoInBorrowItems {

	private List<Item> items = new ArrayList<>();
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
}