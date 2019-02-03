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
import fr.epsi.mspr.restapi.service.impl.BorrowServiceImpl;
import fr.epsi.mspr.restapi.service.metier.dto.DtoEquipment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceTest {

	@Autowired
	private BorrowServiceImpl borrowServiceImpl;
	@Autowired
	private GuardianRepository guardianRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void returnBorrowTest() {
		DtoEquipment dto = new DtoEquipment();
		Item item = itemRepository.findAll().get(0);
		Gson gson = new Gson();
		dto.addEquipment(item);
		Guardian g = guardianRepository.findById(2l).get();
		borrowServiceImpl.borrow(gson.toJson(dto), g).getStatusCodeValue();
		assertEquals(200, borrowServiceImpl.returnBorrows(gson.toJson(dto), g).getStatusCodeValue());
	}
	
	@Test
	public void borrowListTest() {
		Guardian g = guardianRepository.findById(2l).get();
		ResponseEntity<?> result = borrowServiceImpl.getBorrows(g);
		assertEquals(200, result.getStatusCodeValue());
	}
}
