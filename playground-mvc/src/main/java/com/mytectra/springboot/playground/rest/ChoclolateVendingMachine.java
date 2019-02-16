package com.mytectra.springboot.playground.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.mytectra.springboot.playground.core.CNFError;
import com.mytectra.springboot.playground.core.ChocolatesNotFoundException;
import com.mytectra.springboot.playground.core.VendingEngine;
import com.mytectra.springboot.playground.datastore.ItemStore;
import com.mytectra.springboot.playground.model.Chocolate;
import com.mytectra.springboot.playground.model.RequestScopeBean;

@RestController
public class ChoclolateVendingMachine {
	public static final Logger logger = LoggerFactory.getLogger(ChoclolateVendingMachine.class);

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	
	@Autowired
	private RequestScopeBean bean;
	
	@Autowired
	// These are the bean id, which has to be searched for Bean
	private ItemStore<Chocolate> itemStore;

	@Value("${numberOfChocloatesToLoad}")
	// this is load value from prop file
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

	// this will be executed after bean is created
	/*@PostConstruct
	public void init() {
		System.out.println(String.format("CVM init started , loading %d chocolates ", numberOfChocloatesToLoad));
		for (int i = 0; i < numberOfChocloatesToLoad; i++) {
			this.addChocolate(new Chocolate("KitKat" + i, "Cadebury", 10 , new Date()));
		}
	}

	// this will be executed before bean is destroyed
	@PreDestroy
	public void destroy() {
		System.out.println("init Destroy started..");
	}*/

	@PostMapping("/chocolates/load")
	@Secured("ADMIN")
	public String loadChocolates(@RequestBody @RequestPart("application/json") @Validated Chocolate chocolate) {
		System.out.println("Loading Chocolates : "+ chocolate);
		this.addChocolate(chocolate);
		return "Chocolates added sucessfully";
	}
	/*
	 * @RequestMapping(value = "/chocolates/load/", method = RequestMethod.POST)
	 * public ResponseEntity<?> loadChocolates(@RequestParam Chocolate chocolate,
	 * UriComponentsBuilder ucBuilder) { logger.info("Loading Chocolates : {} ",
	 * chocolate);
	 * 
	 * this.addChocolate(chocolate);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/chocolates/load/{name}").buildAndExpand(
	 * chocolate.getName()).toUri()); return new ResponseEntity<String>(headers,
	 * HttpStatus.CREATED); }
	 */

	@PostMapping("/chocolates/buy")
	@RolesAllowed({"ADMIN" , "USER"})
	public List<Chocolate> getChocolates(@RequestParam("money") int money) throws Exception {
		List<Chocolate> chocolates = new ArrayList<>();
		for (VendingEngine<? extends Chocolate> engine : vendingEngines) {
			chocolates.addAll(engine.getItems(money));
		}
		return chocolates;
	}

	
	@ExceptionHandler(ChocolatesNotFoundException.class)
	public CNFError handleCNF(ChocolatesNotFoundException ex) {
		return ex.getCnfError();
	}
	
	
		// Path Variable
	@GetMapping(value = "/chocolates/{brandName}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<Chocolate> getChocolatesByBrand(@PathVariable("brandName") String brandName) throws Exception {
		List<Chocolate> chocolates;
		try {
			chocolates = itemStore.listItems();
			chocolates = chocolates.stream().filter(c -> c.getBrand().equals(brandName)).collect(Collectors.toList());
			return chocolates;
		} catch (Exception e) {
			throw e;
		}
	}

	private void addChocolate(Chocolate chocolate) {
		itemStore.loadItem(chocolate);
	}

	@GetMapping("/chocolates")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Chocolate> listChocolates() {
		return itemStore.listItems();
	}
    
	//Multi JSON Load of chocolates. Read the JSON as an Array
	@PostMapping("/chocolates/multiload")
	public String multiLoadChocolates(@RequestBody @RequestPart("application/json") @Validated List<Chocolate> chocolates) {
		if(chocolates != null) {
			for(Chocolate chocolate : chocolates) {
				this.addChocolate(chocolate);
			}
		}		
		return "Multi chocolates added sucessfully";
	}
}
