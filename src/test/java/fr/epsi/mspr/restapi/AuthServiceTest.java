package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.service.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

	@Autowired
	private AuthService service;
	
	@Test
	public void testAuthValidation() {
		assertEquals(true, service.isValid("28412842"));
	}
	
	@Test
	public void test() {
		assertNotEquals(null, service.authentificateImage(null));
	}
}

