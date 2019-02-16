package com.mytectra.springboot.playground.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mytectra.springboot.playground.security.User.Role;

@Component
public class UserDaoJDBCImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate template;

	private final String query = "SELECT * FROM USER where uname = ?";
	
	@Override
	public User getUserByName(String name) {
	List<User> users =  template.query(query, new Object[] {name} ,  new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("uname"));
				user.setPassword(rs.getString("password"));
				user.setRole(Role.valueOf(rs.getString("role")));
				return user;
			}
			
		});
	
	if(users == null || users.isEmpty()) {
		return null;
	}
	
	return users.get(0);
	
	}

}
