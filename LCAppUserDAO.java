package com.home.lc.DAO;

import java.util.List;

import com.home.lc.api.User;

public interface LCAppUserDAO {
	List<User> loadUsers();
}
