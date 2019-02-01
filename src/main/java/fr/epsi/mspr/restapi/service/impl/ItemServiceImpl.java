package fr.epsi.mspr.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epsi.mspr.restapi.dao.entity.Item;
import fr.epsi.mspr.restapi.dao.repository.ItemRepository;
import fr.epsi.mspr.restapi.service.ItemService;
import fr.epsi.mspr.restapi.service.JsonService;
import fr.epsi.mspr.restapi.service.metier.dto.in.DtoInBorrowItems;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository<Item> itemRepository;
	@Autowired
	JsonService jsonService;
	
	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	@Override
	public boolean borrow(String data) {
		DtoInBorrowItems dtoInBorrowItems = jsonService.getDtoInBorrowItems(data);
		if(dtoInBorrowItems == null) {
			System.out.println(this.getClass().getName() + "> bad json");
			return false;
		}
		return true;
	}

}
