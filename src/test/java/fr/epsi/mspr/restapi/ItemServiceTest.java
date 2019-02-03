package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemService itemService;
	private Gson json = new Gson();
	
	@Test
	public void getAllItemTest() {
		List<Item> list = itemRepository.findAll();
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void addItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		Item item = new Item();
		item.setName("Banane");
		item.setQuantity(20);
		dtoEquipment.addEquipment(item);
		assertEquals(200, itemService.addNew(json.toJson(dtoEquipment)).getStatusCodeValue());
	}
	
	@Test
	public void editItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		Item item = new Item();
		item.setId(15);
		item.setName("Banane à la feuille !");
		item.setQuantity(30);
		dtoEquipment.addEquipment(item);
		assertEquals(200, itemService.edit(json.toJson(dtoEquipment)).getStatusCodeValue());
	}
	
	@Test
	public void removeItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		Item item = new Item();
		item.setId(15);
		dtoEquipment.addEquipment(item);
		assertEquals(200, itemService.remove(json.toJson(dtoEquipment)).getStatusCodeValue());
	}
}