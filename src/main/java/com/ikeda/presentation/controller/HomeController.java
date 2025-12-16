package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.entity.DvdItem;
import com.ikeda.service.ItemService;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService; // ★ new しない！

    @GetMapping("/home")
    public String showHomePage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        int pageSize = 9;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        Page<DvdItem> items = itemService.findAll(pageable);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);

        return "home";
    }
}
