package com.ikeda.presentation.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.entity.DvdItem;
import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;
import com.ikeda.service.ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/home")
    public String showHomePage(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            
            Principal principal,
            HttpSession session
    		) {
    	
    	 // ★ ログイン済みの場合
        if (principal != null) {
            String email = principal.getName(); // sasa@gmail.com

            // ★ email で customers テーブルを検索
            Member member = memberRepository.findByEmail(email);

            if (member != null) {
                // ★ username（sasaki）を session に保存
                session.setAttribute("loginUser", member.getUsername());
            }
        }

        int pageSize = 9;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        Page<DvdItem> items = itemService.findAll(pageable);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);

        return "home";
    }
}
