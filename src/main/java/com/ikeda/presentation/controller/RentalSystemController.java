package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentalSystemController {
	
	@GetMapping(value = "/home")
	public String toHome() {
		return "index";
	}
	
	@GetMapping(value = "/detail")
	public String toDetail() {
		return "detail";
	}
	
	@GetMapping(value = "/login")
	public String toLogin() {
		return "login";
	}

}
