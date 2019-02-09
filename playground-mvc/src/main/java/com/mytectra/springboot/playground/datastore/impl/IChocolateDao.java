package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;

import com.mytectra.springboot.playground.model.Chocolate;

public interface IChocolateDao {

	void save(Chocolate chocolate);

	List<Chocolate> findAll();

}