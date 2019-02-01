package fr.epsi.mspr.restapi.service.metier.dto.in;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoInBorrowItems {

	private int id;
	private List<Item> equipments = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Item> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<Item> equipments) {
		this.equipments = equipments;
	}	
}