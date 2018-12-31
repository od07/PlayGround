package com.mytectra.springboot.playground.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope( value ="request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {
	
	private List<Chocolate> chocolates = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		System.out.println("Created a new request bean ...");
	}

	
	@PreDestroy
	public void des() {
		System.out.println("Destroyed a this request bean ...");
	}

	
	public void add(Chocolate chocolate) {
		chocolates.add(chocolate);
	}
	
	public List<Chocolate> getChocolates() {
		return chocolates;
	}
}
