package com.ikeda.presentation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.presentation.form.ProductForm;
import com.ikeda.service.DvdItemService;

@Controller
public class AdminProductController {
	
	@Autowired
	private DvdItemService productService;

    // 商品一覧
    @GetMapping("/admin/products")
    public String showProductList(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "admin/product-list";//商品一覧ページへ遷移する
    }

    // 商品新規登録フォーム
    @GetMapping("/admin/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "admin/product-form";//新規商品登録ページへ遷移する
    }
}
