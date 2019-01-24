package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.service.AuthService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInIdentification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

	@Autowired
	private AuthService service;
	
	@Test
	public void testAuthValidation() {
		String key = "Bearer 117b3a03-ee64-4b25-8291-52ab6ac4dafe";
		assertEquals(false, service.isValid(key));
	}
	
	@Test
	public void testAuthWithImageWithNoData() {
		assertEquals(null, service.authentificateImage(null));
	}
	
	@Test
	public void testAuthWithImageWithBadData() {
		assertEquals(null, service.authentificateImage("JéùjejfzzcEHT%jy9h56"));
	}
	
	@Test
	public void testAuthWithImageWithCorrectUser() {
		Gson gson = new Gson();
		DtoInIdentification dtoIdentification = new DtoInIdentification();
		dtoIdentification.setIdentifier("Clément");
		assertNotEquals(null, service.authentificateImage(gson.toJson(dtoIdentification)));
	}
}

