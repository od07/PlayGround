package com.mytectra.springboot.playground.datastore.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

//We have externalize the configuration

public class ChocolateStore implements ItemStore<Chocolate> {

	private List<Chocolate> items = new ArrayList<>();

	
	@Override
	public void loadItem(List<Chocolate> items) {
		this.items.addAll(items);
	}

	@Override
	public Optional<List<Chocolate>> getItems(int quantity) {
		if(quantity <= items.size()) {
			return fulfillItems(quantity);
		} else {
			return Optional.empty();
		}
	}

	private Optional<List<Chocolate>> fulfillItems(int quantity) {
		List<Chocolate> itemsToBeReturned = new ArrayList<>();
		int count = 0;
		Iterator<Chocolate> iterate = items.iterator();
		while(iterate.hasNext() && count < quantity) {
			itemsToBeReturned.add(iterate.next());
			iterate.remove();
			count++;
		}
		return Optional.ofNullable(itemsToBeReturned);
	}

	@Override
	public List<Chocolate> listItems() {
		return items;
	}

}
