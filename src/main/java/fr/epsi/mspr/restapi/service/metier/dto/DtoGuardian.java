package fr.epsi.mspr.restapi.service.metier.dto;

import java.util.List;

public class DtoGuardian {

	private List<DtoGuardianEntity> guardians;

	public List<DtoGuardianEntity> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<DtoGuardianEntity> guardians) {
		this.guardians = guardians;
	}
}
