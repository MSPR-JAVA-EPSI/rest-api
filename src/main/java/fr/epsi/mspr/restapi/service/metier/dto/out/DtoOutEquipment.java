package fr.epsi.mspr.restapi.service.metier.dto.out;

import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoOutEquipment {

	private List<Item> equipments;

	public List<Item> getEquipments() {
		return equipments;
	}

	public DtoOutEquipment setEquipments(List<Item> equipments) {
		this.equipments = equipments;
		return this;
	}
}
