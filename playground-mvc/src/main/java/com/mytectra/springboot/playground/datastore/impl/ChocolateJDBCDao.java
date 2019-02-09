package com.mytectra.springboot.playground.datastore.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class ChocolateJDBCDao implements IChocolateDao {
	
	@Autowired
	private ChocolateExtractor extractor;
	
	private static final String INSERT_QUERY = "insert into chocolate(cname,brand,expriry,price) values (?,?,?,?)";
	
	private static final String GET_ALL_QUERY = "select * from chocolate";

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.mytectra.springboot.playground.datastore.impl.IChocolateDao#save(com.mytectra.springboot.playground.model.Chocolate)
	 */
	@Override
	public void save(Chocolate chocolate) {
		
		jdbcTemplate.update(INSERT_QUERY, chocolate.getName() , chocolate.getBrand() , chocolate.getExpiryDate() , chocolate.getPrice());
		
	}
	
	/* (non-Javadoc)
	 * @see com.mytectra.springboot.playground.datastore.impl.IChocolateDao#findAll()
	 */
	@Override
	public List<Chocolate> findAll(){
		return (List<Chocolate>) jdbcTemplate.query(GET_ALL_QUERY,extractor);		
	}

}
