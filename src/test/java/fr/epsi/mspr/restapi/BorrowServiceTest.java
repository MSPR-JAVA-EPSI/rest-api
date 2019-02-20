package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;
import fr.epsi.mspr.restapi.service.BorrowService;
import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceTest {

	@Autowired
	private BorrowService borrowService;
	@Autowired
	private GuardianRepository guardianRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void returnBorrowTest() {
		DtoEquipment dto = new DtoEquipment();
		Item item = itemRepository.findAll().get(0);
		Gson gson = new Gson();
		item.setQuantity(5);
		item.setName("salut");
		dto.addEquipment(item);
		Guardian g = guardianRepository.findById(3l).get();
		borrowService.borrow(gson.toJson(dto), g);
		assertEquals(200, borrowService.returnBorrows(gson.toJson(dto), g).getStatusCodeValue());
	}
	
	@Test
	public void borrowListTest() {
		Guardian g = guardianRepository.findById(3l).get();
		ResponseEntity<?> result = borrowService.getBorrows(g);
		assertEquals(200, result.getStatusCodeValue());
	}
}
