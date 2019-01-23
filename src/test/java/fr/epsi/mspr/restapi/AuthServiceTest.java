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
		String key = "Bearer 117b3a03-ee64-4b25-8291-52ab6ac4dafe";
		assertEquals(true, service.isValid(key));
	}
	
	@Test
	public void test() {
		assertNotEquals(null, service.authentificateImage(null));
	}
}

