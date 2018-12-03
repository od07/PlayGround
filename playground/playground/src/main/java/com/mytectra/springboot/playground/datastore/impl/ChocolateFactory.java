package com.mytectra.springboot.playground.datastore.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

public class ChocolateFactory implements ItemStore<Chocolate> {

	private ArrayList<Chocolate> items = new ArrayList<Chocolate>();
	@Override
	public void loadItem(List<Chocolate> items) throws Exception {
		items.add(new Chocolate("Kitkat", "Nestle", 1));
		items.add(new Chocolate("Tuffy", "Parle", 1));
		items.add(new Chocolate("Choco", "Cadbury", 1));
		items.add(new Chocolate("Dark Chocolate", "Cadbury", 1));
		items.add(new Chocolate("Fruit Punch", "Nestle", 1));
		this.items = (ArrayList<Chocolate>) items;
	}

	@Override
	public Optional<List<Chocolate>> getItems(int quantity) {
		if(quantity == items.size()) {
			return Optional.ofNullable(items);
		} else {
			return Optional.empty();
		}
	}

}
