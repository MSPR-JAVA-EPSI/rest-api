package fr.epsi.mspr.restapi.dao.repository;

import java.util.List;
import java.util.Optional;

import fr.epsi.mspr.restapi.dao.entity.Item;

public interface ItemRepository extends CustomRepository<Item, Long> {

	List<Item> findAllByEnable(boolean b);

	Optional<Item> findByName(String string);

}