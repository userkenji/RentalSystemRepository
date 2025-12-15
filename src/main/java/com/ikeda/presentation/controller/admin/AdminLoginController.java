package com.ikeda.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {

    // 管理者用ログイン画面表示
    @GetMapping("/admin/login")
    public String showAdminLogin() {
        // templates/admin/login.html を表示する
        return "admin/login";
    }
    
    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }
}

