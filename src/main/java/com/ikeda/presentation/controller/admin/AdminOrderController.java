package com.ikeda.presentation.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikeda.presentation.form.OrderListForm; // 【重要】OrderListFormをインポートimport com.ikeda.service.OrderItemService;
import com.ikeda.service.OrderItemService;

@Controller
public class AdminOrderController {

    private final OrderItemService orderItemService;

    public AdminOrderController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // 注文一覧表示
    @GetMapping("/admin/orders")
    public String list(Model model) {
    	List<OrderListForm> orders = orderItemService.findAll();        model.addAttribute("orders", orders);
        return "admin/order-items";
    }
 // --- 【追加箇所 1: 編集画面表示】 ---
    @GetMapping("/admin/order/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // TODO: 編集対象の注文データ（IDに基づき）を取得する処理を記述
        
        // 取得したデータをModelに格納し、編集画面へ遷移
        return "admin/order-edit"; // 仮の編集画面名
    }
    
    // --- 【追加箇所 2: 削除実行】 ---
    @PostMapping("/admin/order/delete/{id}")
    public String deleteOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        
        // 1. Service へ削除処理を委譲
        orderItemService.deleteById(id); // ★ このメソッドが Service に必要
        
        // 2. 成功メッセージをセットし、一覧へリダイレクト
        redirectAttributes.addFlashAttribute("success", "注文 ID: " + id + " を削除しました。");
        return "redirect:/admin/orders";
    }
}
