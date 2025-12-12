package com.ikeda.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ikeda.presentation.form.AdminLoginForm;

@Controller
public class AdminController {

//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin/index";  // admin/index.html (管理者用ページ）を表示
//    }
	
	
	// index.htmlから管理者用ログインページに直接遷移
	@GetMapping("/admin/login")
	public String toAdminLoginPage() {
		return "admin/login";
	}
	
	// admin/loginからのPost通信
	@PostMapping("/admin/login")
	public String adminLogin(Model model) {
		model.addAttribute("adminLoginForm", new AdminLoginForm());
		return "admin/login";
	}
}

