package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	
	@RequestMapping(value = "/")
	public String greeting() {
		return "Welcome to First Micro Service Project running on port number 9191";
	}

}
