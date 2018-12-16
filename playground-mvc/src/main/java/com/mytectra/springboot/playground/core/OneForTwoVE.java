package com.mytectra.springboot.playground.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class OneForTwoVE implements VendingEngine<Chocolate> {

	@Autowired
	private ItemStore<Chocolate> itemStore;
	
	@Override
	public List<Chocolate> getItems(int money) throws Exception {
		
	    Optional<List<Chocolate>> chocolates = itemStore.getItems(money * 2);
		if(chocolates.isPresent()) {
			return chocolates.get();
		} else {
			throw new Exception("Sorry No Chocolates");
		}
		
	}


  
}
