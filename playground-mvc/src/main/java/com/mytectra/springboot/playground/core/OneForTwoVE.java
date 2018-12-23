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
	public List<Chocolate> getItems(int money) throws ChocolatesNotFoundException {
		
	    Optional<List<Chocolate>> chocolates = itemStore.getItems(money * 2);
		if(chocolates.isPresent()) {
			return chocolates.get();
		} else {
			
			String errMsg = "Sorry No Chocolates , we cannot fulfill chocolates for money = "+ money;
			CNFError error = new CNFError();
			error.setStatus("FULFILL_DENY_!F2");
			error.setReason(errMsg);
			throw new ChocolatesNotFoundException(error);
		}
		
	}


  
}
