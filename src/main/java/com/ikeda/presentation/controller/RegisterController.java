package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")//←templates/register.htmlへ遷移する（新規登録ページ）
    public String showRegisterForm() {
        return "register"; 
    }
    @PostMapping("/register") // フォーム送信時の仮処理
    public String registerMember() {
        // 仮の処理（後で中身を書く）
        return "register";
    }

}
