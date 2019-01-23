package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemService service;
	
	@Test
	public void getAllItemTest() {
		List<Item> list = service.getAll();
		assertNotEquals(0, list.size());
	}
}