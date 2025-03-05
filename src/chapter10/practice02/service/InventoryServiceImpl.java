package chapter10.practice02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import chapter10.practice02.entity.Item;
import chapter10.practice02.repository.ItemRepository;

// Impl: Implements
public class InventoryServiceImpl implements InventoryService {
	private ItemRepository repository;

	public InventoryServiceImpl(ItemRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void addItme(Item item) {
		repository.addItem(item);
	}

	@Override
	public void updateItemPrice(String itemId, int newPrice) {
		// 수정할 Item이 저장소에 존재하는지 확인
		Item item = repository.findById(itemId);
		
		if (item != null) {
			item.setPrice(newPrice);
		}
	}

	@Override
	public void deleteItem(String itemId) {
		Item item = repository.findById(itemId);
		
		if (item != null) {
			repository.removeItem(itemId);
		}
	}

	@Override
	public List<Item> getItemsByCategory(String category) {
		Set<Item> allItems = repository.findAll();
		List<Item> categoryItems = new ArrayList<Item>();
		
		for (Item item : allItems) {
			if (item.getCategory().equalsIgnoreCase(category)) {
				categoryItems.add(item);
			}
		}
		
		return categoryItems;
	}
}
