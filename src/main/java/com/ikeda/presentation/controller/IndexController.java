package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.service.ItemService;

@Controller

public class IndexController {
	@Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "index";


}
}