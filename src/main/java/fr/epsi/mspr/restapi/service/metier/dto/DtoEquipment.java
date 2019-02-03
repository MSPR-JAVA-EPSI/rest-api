package fr.epsi.mspr.restapi.service.metier.dto;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoEquipment {

	private List<Item> equipments = new ArrayList<>();

	public List<Item> getEquipments() {
		return equipments;
	}

	public DtoEquipment setEquipments(List<Item> equipments) {
		this.equipments = equipments;
		return this;
	}

	public void addEquipment(Item item) {
		equipments.add(item);
	}
}
