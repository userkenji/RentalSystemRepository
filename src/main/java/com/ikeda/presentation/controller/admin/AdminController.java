package com.ikeda.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/index";  // admin/index.html (管理者用ページ）を表示
    }
}

