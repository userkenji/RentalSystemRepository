package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ikeda.entity.Member;
import com.ikeda.presentation.form.MemberForm;
import com.ikeda.service.RegisterService;

@Controller
public class FormController {

	
    @Autowired
    private RegisterService registerService;
    // GET /form → form.html を表示
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(MemberForm memberForm) {

        // MemberForm → Member（Entity）へ変換
        Member member = new Member();
        member.setFirstName(memberForm.getFirstName());
        member.setLastName(memberForm.getLastName());
        member.setUsername(memberForm.getUsername());
        member.setEmail(memberForm.getEmail());
        member.setAddress(memberForm.getAddress());
        member.setZip(memberForm.getZip());
        member.setPassword(memberForm.getPassword()); // 平文 → Serviceでハッシュ化

        registerService.register(member);
        // ここで Service を呼んで DB 登録などを行う
        // memberService.register(memberForm);
        return "redirect:/"; // 登録完了後にトップ画面へ
    }
}
