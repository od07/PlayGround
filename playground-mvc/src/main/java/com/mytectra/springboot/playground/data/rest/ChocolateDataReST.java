package com.mytectra.springboot.playground.data.rest;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mytectra.springboot.playground.model.Chocolate;

@RepositoryRestResource( collectionResourceRel="chocolates" , path="/datarest")
public interface ChocolateDataReST extends PagingAndSortingRepository<Chocolate, Integer> {	
	
	//Custom Search /search/findByName?name=''
	List<Chocolate> findByName(@Param("name") String name);

}


