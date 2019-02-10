package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;

import com.mytectra.springboot.playground.model.Chocolate;

public interface IChocolateDao {

	void save(Chocolate chocolate);
	
	void update(Chocolate chocolate);

	List<Chocolate> findAll();
	
	List<Chocolate> findAll(String brand);

	Chocolate findByChocolate(String chocolateName);

	void updateChocolate(Chocolate chocolate);

}