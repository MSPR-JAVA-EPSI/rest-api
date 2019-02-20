package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void RunAs1_AddItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		Item item = new Item();
		item.setName("Banane");
		item.setQuantity(20);
		dtoEquipment.addEquipment(item);
		assertEquals(200, itemService.addNew(json.toJson(dtoEquipment)).getStatusCodeValue());
	}

	@Test
	public void RunAs2_EditItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		List<Item> itemList = itemRepository.findAll();
		if (itemList.size() > 0) {
			Item item = itemList.get(itemList.size() - 1);
			if ("Banane".equals(item.getName())) {
				item.setName("Banana");
				item.setQuantity(30);
				dtoEquipment.addEquipment(item);
				assertEquals(200, itemService.edit(json.toJson(dtoEquipment)).getStatusCodeValue());
			}
		}
	}

	@Test
	public void RunAs3_RemoveItems() {
		DtoEquipment dtoEquipment = new DtoEquipment();
		Optional<Item> oprionalItem = itemRepository.findByName("Banana");
		if (oprionalItem.isPresent()) {
			dtoEquipment.addEquipment(oprionalItem.get());
			assertEquals(200, itemService.remove(json.toJson(dtoEquipment)).getStatusCodeValue());
		}
	}
}