package com.palmen.foodtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/indexWeb")
	public String indexWeb() {
		return "indexWeb";
	}
}
