package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Controller
public class MypageController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/mypage")
    public String showMypage(Model model) {

        // ★ Spring Security から認証情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 未ログイン or 匿名ユーザーならログインへ
        if (auth == null || auth instanceof AnonymousAuthenticationToken || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        // SecurityConfig で username に email を使っている前提
        String email = auth.getName();

        // email から Member を取得
        Member loginUser = memberRepository.findByEmail(email);

        if (loginUser == null) {
            // 念のため、ユーザーが見つからないときもログイン画面へ
            return "redirect:/login";
        }

        // mypage.html は ${user.xxx} で参照しているので "user" で渡す
        model.addAttribute("user", loginUser);

        return "mypage"; // templates/mypage.html
    }
}
