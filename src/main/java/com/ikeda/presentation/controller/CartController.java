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
    
    // カート内のアイテム削除機能
    @GetMapping("/delete/{id}")
    public String itemDeletedCart(@PathVariable("id") int id, HttpSession session, Model model) {
    	List<DvdItem> cart = (List<DvdItem>) session.getAttribute("cart");
    	cart.removeIf(DvdItem -> DvdItem.getId() == (id));
    	session.setAttribute("cart", cart);
    	model.addAttribute("cart", cart);
    	return "cart";
    }
    
    // 注文を確定時の処理
//    @GetMapping("/cart/confirm")
//    public String confirm() {
    
    	// ログイン中のcustomerのstatusにてレンタル状況を更新
    	// ordersにて新規注文情報を作成
    	// productsのrented_stock、not_rented_stockにて在庫状況を更新
    
//    	return  注文情報表示ページ ;
//    }
    
    

}
