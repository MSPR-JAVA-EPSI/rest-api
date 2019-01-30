package fr.epsi.mspr.restapi.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.service.ImageConverterService;

@Service
public class ImageConverterServiceImpl implements ImageConverterService {

	@Override
	public byte[] getBytesByBase64(String base64) {
		return Base64.getDecoder().decode(base64);
	}
}
