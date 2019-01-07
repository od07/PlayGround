package com.mytectra.springboot.playground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mytectra.springboot.playground.datastore.impl.ChocolateStore;


/**
 * 
 * @author admin
 *
 */
@Configuration
public class AppConfiguration {
	
	
	@Bean({"defaultCS"})
	@Primary
	public ChocolateStore chocolateStore2() {
		return new ChocolateStore();
	}
}
