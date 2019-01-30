package fr.epsi.mspr.restapi.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.internal.util.Base64;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.amazonapi.FaceRecoginitionStream;
import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.service.VisageApiService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@Service
public class VisageApiServiceImpl implements VisageApiService {

	@Override
	public boolean isValidUser(DtoInIdentification identification, Guardian guardian) {
		try {
			InputStream stream = new ByteArrayInputStream(Base64.decode(identification.getImage().getBytes()));
			byte[] bytes = IOUtils.toByteArray(stream);
			String id = FaceRecoginitionStream.getFaceId(bytes);
			System.out.println(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
