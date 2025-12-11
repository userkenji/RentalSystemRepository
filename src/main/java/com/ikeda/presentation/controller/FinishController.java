package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ikeda.presentation.form.MemberForm;
import com.ikeda.service.LoginService;

@Controller
public class FinishController {

    @Autowired
    private LoginService loginService;  // DB 保存用（まだ無ければ後で作る）

    // POST /complete → DB登録 → トップページへ
    @PostMapping("/complete")
    public String complete(@ModelAttribute MemberForm memberForm) {

        // TODO: DB保存処理（なければコメントアウトでOK）
        // memberService.save(memberForm);

        // 登録完了後トップページへ遷移
        return "redirect:/index";  // ← トップページ（あなたの設計に合わせて変更）
    }
}
