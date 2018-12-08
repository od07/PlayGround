package com.mytectra.springboot.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mytectra.springboot.playground.ui.ChoclolateVendingMachine;

@SpringBootApplication
public class PlaygroundApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PlaygroundApplication.class, args);
		ChoclolateVendingMachine vendingMachine = ctx.getBean(ChoclolateVendingMachine.class);	
		
		
		vendingMachine.listChocolates();
		
		//vendingMachine.getChocolates(20);
		
		System.out.println("Print--------"+vendingMachine.getChocolates(2).size());
	}
}
