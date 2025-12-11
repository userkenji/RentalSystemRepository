package com.ikeda.presentation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikeda.entity.Member;
import com.ikeda.service.MemberService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    private final MemberService memberService;

    public AdminUserController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 一覧
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", memberService.findAll());
        return "admin/user-list";
    }

    // 編集画面表示
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Member user = memberService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-edit";
    }

    // 編集内容の保存
    @PostMapping("/edit/{id}")
    public String saveEdit(@PathVariable Long id,
                           @ModelAttribute("user") Member formUser) {

        memberService.update(id, formUser);
        return "redirect:/admin/user";
    }

    // 削除処理（GETでもOK）
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        memberService.delete(id);
        redirectAttributes.addFlashAttribute("success", "ユーザーを削除しました");

        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        Member user = memberService.findById(id);

        if (user == null) {
            // 見つからない場合 → 一覧へ戻す
            return "redirect:/admin/user";
        }

        model.addAttribute("user", user);
        return "admin/user-edit";   // ← 編集画面のHTML
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Member formUser, RedirectAttributes redirectAttributes) {

        memberService.update(formUser.getId(), formUser);

        // ★ 成功メッセージを渡す
        redirectAttributes.addFlashAttribute("success", "ユーザー情報を更新しました");

        return "redirect:/admin/user/list";
    }


}
