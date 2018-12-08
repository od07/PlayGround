package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class ChoclolateVendingMachine {

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	@Autowired
	@Qualifier("defaultCS")
	private ItemStore<Chocolate> itemStore;

	public ChoclolateVendingMachine(List<VendingEngine<Chocolate>> vendingEngines) {
		this.vendingEngines = vendingEngines;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("init CVM started..");
		this.addChocolate(new Chocolate("KitKat", "Cadebury", 10));
		this.addChocolate(new Chocolate("Tuffy", "Cadebury", 1));
		this.addChocolate(new Chocolate("Cocochoco", "Parley", 15));
		this.addChocolate(new Chocolate("Walnut", "Cadebury", 5));
		this.addChocolate(new Chocolate("XYZ", "Cadebury", 2));
	}

	@PreDestroy
	public void destroy() {
		System.out.println("init Destroy started..");
	}
	
	public List<Chocolate> getChocolates(int money) {
		try {
			List<Chocolate> chocolates = new ArrayList<>();
			for (VendingEngine<? extends Chocolate> engine : vendingEngines) {
				chocolates.addAll(engine.getItems(money));
			}
			return chocolates;
		} catch (Exception e) {
			System.out.println("Sorry we cannot fulfill the choclotes");
		}
		return null;
	}

	public void addChocolate(Chocolate chocolate) {
		List<Chocolate> listOfChocolates = new ArrayList<Chocolate>();
		listOfChocolates.add(chocolate);
		itemStore.loadItem(listOfChocolates);
	}
	
	public void listChocolates() {
		Stream.of(itemStore.listItems()).forEach(chocolate -> System.out.println(chocolate));
	}

}
