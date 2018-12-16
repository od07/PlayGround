package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;

@RestController
public class ChoclolateVendingMachine {

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	@Autowired
	//These are the bean id, which has to be searched for Bean
	@Qualifier("defaultCS")
	private ItemStore<Chocolate> itemStore;
	

	@Value("${numberOfChocloatesToLoad}")
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
	
	@PostMapping("/chocolates/buy")
	public List<Chocolate> getChocolates(@RequestParam("money") int money) throws Exception {
		try {
			List<Chocolate> chocolates = new ArrayList<>();
			for (VendingEngine<? extends Chocolate> engine : vendingEngines) {
				chocolates.addAll(engine.getItems(money));
			}
			return chocolates;
		} catch (Exception e) {
			throw e;
		}
	}

	private void addChocolate(Chocolate chocolate) {
		List<Chocolate> listOfChocolates = new ArrayList<Chocolate>();
		listOfChocolates.add(chocolate);
		itemStore.loadItem(listOfChocolates);
	}
	
	@GetMapping("/chocolates")
	public List<Chocolate> listChocolates() {
		return itemStore.listItems();
	}

}
