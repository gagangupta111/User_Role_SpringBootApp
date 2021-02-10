package com.omnirio.controller;

import com.omnirio.service.MainService;
import com.omnirio.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("/omnirio")
public class MainController {

	private static Logger logger = LogUtil.getInstance();

	@Autowired
	private MainService mainService;

	@RequestMapping("/user")
	public String getAllUsers() {

		return mainService.getAllUsers();
	}

}
