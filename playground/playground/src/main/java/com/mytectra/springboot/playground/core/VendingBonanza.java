package com.mytectra.springboot.playground.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component
@ConditionalOnProperty(name = {"bonus"} ,  havingValue = "true" )
public class VendingBonanza implements VendingEngine<Chocolate>{
	
	
	
	private ItemStore<Chocolate> itemStore;
	
	@Autowired
	public VendingBonanza(@Qualifier("defaultCS") ItemStore<Chocolate> itemStore) {
		this.itemStore = itemStore;
	}
	
	
	@Override
	public List<Chocolate> getItems(int money) throws Exception {
		List<Chocolate> listOfChocolates = new ArrayList<>();
		if(money == 5 || money == 10) {			
			Optional<List<Chocolate>> chocolates = itemStore.getItems(2);
			if(chocolates.isPresent()) {
				return chocolates.get();
			} else {
				throw new Exception("Sorry No Chocolates");
			}			
		}
		return listOfChocolates;
		
	}
}
