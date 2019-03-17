package com.mytectra.springboot.playground.datastore.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component("dbstore")
@Primary
public class DBChocolateStore implements ItemStore<Chocolate> {
	
	
	@Autowired
	private IChocolateDao dao;

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void loadItems(List<Chocolate> items) {
		items.stream().forEach(chocolate -> dao.save(chocolate));		
	}

	@Override
	public void loadItem(Chocolate items) {
		dao.save(items);
		
	}

	@Override
	public Optional<List<Chocolate>> getItems(int quantity) {
		//TODO
		
		return Optional.of(dao.findAll());
		
		/*if(quantity <= chocolatesFromDb.size()) {
			return fulfillItems(quantity);
		} else {
			return Optional.empty();
		}*/
	}

	@Override
	public List<Chocolate> listItems() {
		List<Chocolate> list = dao.findAll();
		/*List<Chocolate> list = dao.findAll("Cadebury");
		Chocolate ch = list.get(0);
		ch.setPrice(7);
		dao.update(ch);
		return list;*/
		return list;
	}

	private Optional<List<Chocolate>> fulfillItems(int quantity) {
		/*List<Chocolate> itemsToBeReturned = new ArrayList<>();
		int count = 0;
		Iterator<Chocolate> iterate = chocolatesFromDb.iterator();
		while(iterate.hasNext() && count < quantity) {
			itemsToBeReturned.add(iterate.next());
			iterate.remove();
			count++;
		}
		return Optional.ofNullable(itemsToBeReturned);*/
		return Optional.empty();
		
	}
	
	public Chocolate getItemByName(String chocolateName) {
		return dao.findByChocolate(chocolateName);
	}

	@Override
	public void updateChocolate(Chocolate chocolate) {
		dao.updateChocolate(chocolate);
		
	}
	
}
