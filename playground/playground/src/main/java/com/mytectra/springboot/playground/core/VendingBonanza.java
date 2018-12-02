package com.mytectra.springboot.playground.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.model.Chocolate;

@Component
@ConditionalOnProperty(name = {"bonus"} ,  havingValue = "true" )
public class VendingBonanza implements VendingEngine<Chocolate>{
	@Override
	public List<Chocolate> getItems(int money) throws Exception {
		List<Chocolate> chocolates = new ArrayList<>(2);
		if(money == 5 || money == 10) {			
			chocolates.add(new Chocolate("Toffy-Bonus1", "parle", 1));
			chocolates.add(new Chocolate("Toffy-Bonus2", "parle", 1));
		} 
		return chocolates;
	}
}
