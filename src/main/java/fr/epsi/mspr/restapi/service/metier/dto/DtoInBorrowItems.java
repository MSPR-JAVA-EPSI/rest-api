package fr.epsi.mspr.restapi.service.metier.dto;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoInBorrowItems {

	private List<Item> equipments = new ArrayList<>();
	
	public List<Item> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<Item> equipments) {
		this.equipments = equipments;
	}
	public void addEquipment(Item item) {
		equipments.add(item);
	}
}