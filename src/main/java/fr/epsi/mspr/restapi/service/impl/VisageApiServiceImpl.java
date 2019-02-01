package fr.epsi.mspr.restapi.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.VisageApiService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;
import fr.epsi.mspr.restapi.visageapi.FaceComparaison;
import fr.epsi.mspr.restapi.visageapi.FaceRecognitionStream;

@Service
public class VisageApiServiceImpl implements VisageApiService {

	@Override
	public boolean isValidUser(DtoInIdentification identification, Guardian guardian) {
		try {
			byte[] bytes = Base64.getDecoder().decode(identification.getImage().getBytes());
			String id1 = FaceRecognitionStream.getFaceId(bytes);
			String id2 = FaceRecognitionStream.getFaceId(guardian.getGuaImage());
			return FaceComparaison.compare(id1, id2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
