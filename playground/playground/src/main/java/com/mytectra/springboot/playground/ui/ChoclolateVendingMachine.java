package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

public class ChoclolateVendingMachine {

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	@Autowired
	//These are the bean id, which has to be searched for Bean
	@Qualifier("defaultCS")
	private ItemStore<Chocolate> itemStore;
	

	private int numberOfChocloatesToLoad;
	
	public ChoclolateVendingMachine(List<VendingEngine<Chocolate>> vendingEngines) {
		this.vendingEngines = vendingEngines;
	}
	
	
	/**
	 * @param numberOfChocloatesToLoad the numberOfChocloatesToLoad to set
	 */
	public void setNumberOfChocloatesToLoad(int numberOfChocloatesToLoad) {
		this.numberOfChocloatesToLoad = numberOfChocloatesToLoad;
	}


	//this will be executed after bean is created
	@PostConstruct
	public void init() {
		System.out.println(String.format("CVM init started , loading %d chocolates ", numberOfChocloatesToLoad));
		for(int i = 0 ; i< numberOfChocloatesToLoad ; i++) {
			this.addChocolate(new Chocolate("KitKat" + i, "Cadebury", 10));
		}
	}

	//this will be executed before bean is destroyed
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
