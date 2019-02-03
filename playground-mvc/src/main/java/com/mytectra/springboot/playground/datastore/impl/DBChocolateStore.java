package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component("dbstore")
@Primary
public class DBChocolateStore implements ItemStore<Chocolate> {
	
	@Autowired
	private ChocolateDao dao;

	@Override
	public void loadItems(List<Chocolate> items) {
		items.stream().forEach(chocolate -> dao.save(chocolate));
		
	}

	@Override
	public void loadItem(Chocolate items) {
		dao.save(items);
		
	}

	@Override
	public Optional<List<Chocolate>> getItems(int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chocolate> listItems() {
		return dao.findAll();
	}

}
