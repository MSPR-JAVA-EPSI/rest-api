package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.Guardian;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.BorrowRepository;
import fr.epsi.mspr.restapi.dao.repository.GuardianRepository;
import fr.epsi.mspr.restapi.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;
	@Autowired
	private BorrowRepository borrowRepository;
	@Autowired
	private GuardianRepository guardianRepository;

	@Test
	public void getAllItemTest() {
		List<Item> list = itemService.getAll();
		assertNotEquals(0, list.size());
	}

	@Test
	public void testBorrow() {
		List<Item> items = itemService.getAll();
		Guardian guardian = guardianRepository.findById(1l).get();
		for (Item item : items) {
			Borrow b = getBorrowForItem(item, guardian.getBorrow());
			b.setGuardian(guardian);
			b.setItem(item);
			b.addQuantity(5);
			guardian.addBorrow(b);
		}
		guardianRepository.save(guardian);

	}

	private Borrow getBorrowForItem(Item item, Set<Borrow> borrows) {
		Borrow b = new Borrow();
		for (Borrow borrow : borrows) {
			if(borrow.getItem().getId() == item.getId()) {
				b = borrow;
				break;
			}
		}
		return b;
	}
}