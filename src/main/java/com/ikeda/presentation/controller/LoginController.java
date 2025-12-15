package com.ikeda.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import com.ikeda.entity.Member;
import com.ikeda.presentation.form.LoginForm;
//import com.ikeda.service.LoginService;

//import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

//	@Autowired
//	private LoginService loginService;  // インスタンスを注入

    // GET /login → ログイン画面表示
	//
    @GetMapping("/login")
   
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // templates/login.html
    }

/*セキュリティ機能を使用のため
	@PostMapping("/login")
	public String doLogin(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {

		boolean result = loginService.loginCheck(email, password);

		if (result) {
			return "redirect:/home"; // ログイン成功
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが違います");
			return "login";
		}

	}
	*/
}
