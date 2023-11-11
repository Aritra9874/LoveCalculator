package com.home.lc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.lc.DAO.LCAppUserDAO;
import com.home.lc.api.User;

@Controller
public class UserController {

	@Autowired
	private LCAppUserDAO lcAppUserDAO;

	@RequestMapping("/showUser")
	public String showUserList(Model model) {
		// Calling the DAO method to get the data..
		List<User> userList = lcAppUserDAO.loadUsers();

		for (User user : userList) {
			System.out.println(user);
		}

		model.addAttribute("users", userList);

		return "user-list";
	}
}
