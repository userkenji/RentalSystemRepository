package com.ikeda.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private DvdItemRepository itemRepository;

    // カートに追加
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int id, HttpSession session) {

        List<DvdItem> cart = (List<DvdItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        DvdItem item = itemRepository.findById(id).orElse(null);
        if (item != null) {
            cart.add(item);
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    // カート画面表示
    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<DvdItem> cart = (List<DvdItem>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart"; // cart.html
    }

}
