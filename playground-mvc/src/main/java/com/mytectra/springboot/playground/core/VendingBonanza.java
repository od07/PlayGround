package com.mytectra.springboot.playground.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component
@ConditionalOnProperty(name = { "bonus" }, havingValue = "true")
public class VendingBonanza implements VendingEngine<Chocolate> {

	private ItemStore<Chocolate> itemStore;

	@PostConstruct
	public void init() {
		System.out.println("creating bean of Vending Bonanza egerly");
	}

	@Autowired
	public VendingBonanza(ItemStore<Chocolate> itemStore) {
		this.itemStore = itemStore;
	}

	@Override
	public List<Chocolate> getItems(int money) throws ChocolatesNotFoundException {
		List<Chocolate> listOfChocolates = new ArrayList<>();
		if (money == 5 || money == 10) {
			Optional<List<Chocolate>> chocolates = itemStore.getItems(2);
			if (chocolates.isPresent()) {
				return chocolates.get();
			} else {
				String errMsg = "Sorry No Chocolates , we cannot fulfill chocolates for money = " + money;
				CNFError error = new CNFError();
				error.setStatus("FULFILL_DENY_VB");
				error.setReason(errMsg);
				throw new ChocolatesNotFoundException(error);
			}
		}
		return listOfChocolates;

	}
}
