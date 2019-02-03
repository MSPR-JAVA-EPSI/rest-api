package fr.epsi.mspr.restapi;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.epsi.mspr.restapi.dao.entity.Borrow;
import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.BorrowRepository;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Test
	public void getAllItemTest() {
		List<Item> list = itemRepository.findAll();
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void getAvailableItem() {
		List<Item> items = itemRepository.findAll();
		List<Borrow> borrows = borrowRepository.findAll();
		for (Item item : items) {
			for(Borrow borrow : borrows) {
				if(borrow.getItem().getId() == item.getId()) {
					item.removeQuantity(borrow.getQuantity());
				}
			}
		}
		for (Item item : items) {
			System.out.println(item.getId() + "\t" + item.getQuantity() + "\t" + item.getName());
		}
	}
}