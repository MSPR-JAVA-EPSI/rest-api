package fr.epsi.mspr.restapi.service.metier.dto;

import java.util.List;

import fr.epsi.mspr.restapi.dao.entity.Guardian;

public class DtoGuardian {

	private List<Guardian> guardians;

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}
}
