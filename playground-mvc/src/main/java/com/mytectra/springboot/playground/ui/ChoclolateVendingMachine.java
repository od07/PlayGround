package com.mytectra.springboot.playground.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

@RestController
public class ChoclolateVendingMachine {
	public static final Logger logger = LoggerFactory.getLogger(ChoclolateVendingMachine.class);

	@Autowired
	private List<VendingEngine<Chocolate>> vendingEngines;

	@Autowired
	// These are the bean id, which has to be searched for Bean
	@Qualifier("defaultCS")
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
	@PostConstruct
	public void init() {
		System.out.println(String.format("CVM init started , loading %d chocolates ", numberOfChocloatesToLoad));
		for (int i = 0; i < numberOfChocloatesToLoad; i++) {
			this.addChocolate(new Chocolate("KitKat" + i, "Cadebury", 10));
		}
	}

	// this will be executed before bean is destroyed
	@PreDestroy
	public void destroy() {
		System.out.println("init Destroy started..");
	}

	@PostMapping("/chocolates/load")
	public String loadChocolates(@RequestBody @RequestPart("application/json") @Validated Chocolate chocolate) {
		logger.info("Loading Chocolates : {} ", chocolate);
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
		List<Chocolate> listOfChocolates = new ArrayList<Chocolate>();
		listOfChocolates.add(chocolate);
		itemStore.loadItem(listOfChocolates);
	}

	@GetMapping("/chocolates")
	public List<Chocolate> listChocolates() {
		return itemStore.listItems();
	}

}
