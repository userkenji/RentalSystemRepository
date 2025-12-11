package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.entity.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {

    @GetMapping("/mypage")
    public String showMypage(HttpSession session, Model model) {
        // セッションから Member を取得
        Member loginUser = (Member) session.getAttribute("loginUser");
        
        if (loginUser == null) {
            // 未ログインならログインページへリダイレクト
            return "redirect:/login";
        }

        // View にユーザー情報を渡す
        model.addAttribute("loginUser", loginUser);

        // templates/mypage.html を表示
        return "mypage";
    }
}
