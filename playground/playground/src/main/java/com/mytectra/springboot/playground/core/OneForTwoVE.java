package com.mytectra.springboot.playground.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class OneForTwoVE implements VendingEngine<Chocolate> {

	@Override
	public List<Chocolate> getItems(int money) throws Exception {
		
		List<Chocolate> chocolates = new ArrayList<>(money * 2);
		for(int i = 0 ; i < money ; i++) {
			chocolates.add(new Chocolate("Toffy-"+i, "parle", 1));
			chocolates.add(new Chocolate("Mango bite-"+i, "parle", 1));

		}
		return chocolates;
		
	}



}
