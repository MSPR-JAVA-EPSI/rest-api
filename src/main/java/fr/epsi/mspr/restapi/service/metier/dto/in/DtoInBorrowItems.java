package fr.epsi.mspr.restapi.service.metier.dto.in;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoInBorrowItems {

	private String token;
	private List<Item> items = new ArrayList<>();
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}