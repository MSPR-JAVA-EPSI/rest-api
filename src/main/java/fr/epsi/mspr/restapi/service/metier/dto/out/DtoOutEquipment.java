package fr.epsi.mspr.restapi.service.metier.dto.out;

import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Item;

public class DtoOutEquipment {

	private List<Item> equipements;

	public List<Item> getEquipements() {
		return equipements;
	}

	public DtoOutEquipment setEquipements(List<Item> equipements) {
		this.equipements = equipements;
		return this;
	}
}
