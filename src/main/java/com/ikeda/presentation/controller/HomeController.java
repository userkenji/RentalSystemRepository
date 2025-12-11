package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	

	    @GetMapping("/home")
	    public String showHomePage(Model model) {
	        // item 一覧を読み込みするならここで addAttribute
	        // model.addAttribute("items", itemService.findAll());

	        return "home";  // templates/home.html
	    }
	}


