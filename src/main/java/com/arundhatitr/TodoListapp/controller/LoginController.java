package com.arundhatitr.TodoListapp.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arundhatitr.TodoListapp.constants.Constants;
import com.arundhatitr.TodoListapp.util.Configuration;

@RestController
public class LoginController {

	@Resource
	Configuration utility;

	@GetMapping(value = "/")
	public String showWelcomePage(ModelMap model) {
		model.put(Constants.USER_NAME, utility.getLoggedinUserName());
		return Constants.WELCOME;
	}

	@GetMapping(value = "/login")
	public String login(ModelMap model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		if (logout != null) {
			model.put("msg", "You've been logged out successfully.");
		}
		if (error != null) {
			model.put("error", "Invalid username and password!");
		}

		return Constants.LOGIN_FORM;

	}
}