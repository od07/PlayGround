package com.mytectra.springboot.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.mytectra.springboot.playground.ui.ChoclolateVendingMachine;

//to start the spring boot application
@SpringBootApplication

//To Load the beans from XML File
@ImportResource(locations= {"classpath:beans.xml"})

//To load the customized property files
@PropertySource("classpath:application-beans.properties")

public class PlaygroundApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PlaygroundApplication.class, args);
		ChoclolateVendingMachine vendingMachine = ctx.getBean(ChoclolateVendingMachine.class);	
		
		
		vendingMachine.listChocolates();
		
		
		System.out.println("Print--------"+vendingMachine.getChocolates(2).size());
	}
}
