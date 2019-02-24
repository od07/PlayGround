package com.mytectra.springboot.playground.model;

import org.springframework.data.rest.core.config.Projection;

import com.mytectra.springboot.playground.model.Chocolate;

@Projection(name  = "NameAndPrice" , types = {Chocolate.class})
public interface NameAndPriceProjection {

	 int getPrice();
	 
	 String getName();

}
