package fr.epsi.mspr.restapi.service.impl;

import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.VisageApiService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoInIdentification;
import fr.epsi.mspr.restapi.visageapi.FaceComparaison;
import fr.epsi.mspr.restapi.visageapi.FaceRecognitionStream;

@Service
public class VisageApiServiceImpl implements VisageApiService {

	@Override
	public boolean isValidUser(DtoInIdentification identification, Guardian guardian) {
		try {
			byte[] bytes = Base64.getDecoder().decode(identification.getImage().getBytes());
			UUID uuid1 = FaceRecognitionStream.getFaceId(bytes);
			if(uuid1 == null) return false;
			UUID uuid2 = FaceRecognitionStream.getFaceId(guardian.getImage());
			if(uuid2 == null) return false;
			return FaceComparaison.compare(uuid1.toString(), uuid2.toString());
		} catch (Exception ex) {
			System.out.println(this.getClass().getName() + "> " + ex.getMessage());
		}
		return false;
	}
}
