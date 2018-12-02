package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class ChoclolateVendingMachine {

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	public ChoclolateVendingMachine(List<VendingEngine<Chocolate>> vendingEngines) {
		this.vendingEngines = vendingEngines;
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

}
