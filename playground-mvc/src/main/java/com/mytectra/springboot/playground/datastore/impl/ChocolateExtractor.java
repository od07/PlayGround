package com.mytectra.springboot.playground.datastore.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.model.Chocolate;

@Component
public class ChocolateExtractor  implements RowMapper<Chocolate>{

	public Chocolate extractData(ResultSet rs) throws SQLException, DataAccessException {
		return null;

	}

	@Override
	public Chocolate mapRow(ResultSet rs, int rowNum) throws SQLException {
		Chocolate chocolate = new Chocolate();
		chocolate.setName(rs.getString("cname"));
		chocolate.setBrand(rs.getString("brand"));
		chocolate.setPrice(new Double(rs.getDouble("price")).intValue());
		chocolate.setExpiryDate(rs.getDate("expriry"));
		return chocolate;

	}

}
