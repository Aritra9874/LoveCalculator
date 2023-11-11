package com.home.lc.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.home.lc.api.User;
import com.home.lc.rowmapper.UserRowMapper;

@Repository
public class LCAppUserDAOImpl implements LCAppUserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> loadUsers() {
		
		String sql = "SELECT * FROM users";
		List<User> theListOfUser = jdbcTemplate.query(sql, new UserRowMapper());
		
		return theListOfUser;
	}

}
