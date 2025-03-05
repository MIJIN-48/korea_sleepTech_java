package chapter10.practice02.repository;

import java.util.Set;
import chapter10.practice02.entity.Item;

public interface ItemRepository {
	void addItem(Item item);
	void removeItem(String item);
	
	Item findById(String itemId);
	Set<Item> findAll();
}
