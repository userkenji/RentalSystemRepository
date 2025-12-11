package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.LoginService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ikeda.data.ItemData;
import com.ikeda.entity.DvdItem;
import com.ikeda.presentation.form.MemberForm;
import com.ikeda.repository.DvdItemRepository;
import com.ikeda.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RentalSystemController {
	@Autowired
	private LoginService loginService;  // インスタンスを注入

	
	@GetMapping(value = "/gohome")//二つあるので仮のgoに変更してます
	public String toHome( /* HttpSession session, Model model */ ) {
		
//		ItemData itemData = (ItemData) session.getAttribute("itemData");
//		
//		if (itemData == null) {
//			itemData = new ItemData();
//			itemData.setItemName("データベースから取得するタイトル名");
//			session.setAttribute("itemData", itemData);
//		}
//		model.addAttribute("itemData", itemData);
		return "home";
	}
	
//	@GetMapping(value = "/detail")
//	public String toDetail() {
//		return "detail";
//	}
	@GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") Integer id, Model model) {

		DvdItem item = dvdItemRepository.findById(id).orElse(null);

		if (item == null) {
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
		}

        model.addAttribute("item", item);

        return "detail"; // detail.html を表示
    }

	@GetMapping("/gologin")//二つあるので仮のgologinに変更してます
	public String toLogin() {
		return "login"; // templates/login.html を返す
	}

/*	@PostMapping("/login")//LoginController.javaに移植してます
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

	} */
	@Autowired
	private DvdItemRepository dvdItemRepository;
    /*遷移先が二つになるのでコメントアウト
     @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,HttpSession session) {

        int pageSize = 9; // 1ページ9件

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<DvdItem> items = dvdItemRepository.findAll(pageable);

        model.addAttribute("items", items);        // 一覧（ページ情報つき）
        model.addAttribute("currentPage", page);   // 今のページ番号(0始まり)
        
        
        ItemData itemData = (ItemData) session.getAttribute("itemData");
        if (itemData == null) {
            itemData = new ItemData();

            // --- DB の値を入れたいならここで ---
            // 今は仮データ
            itemData.setItemName("タイトル名テスト");
            itemData.setImageFileName("sample.jpg");

            session.setAttribute("itemData", itemData);
        }

        model.addAttribute("itemData", itemData);

        return "index"; // 今の index.html を使う
    }*/
    
    @GetMapping("/cartconfirm")
    public String showCartConfirm() {
        // templates/cartconfirm.html を表示
        return "cartconfirm";
    }
    
    @GetMapping("/rentalForm") // かぶっていたため変更：formをrentalForm
    public String showForm(Model model) {

        model.addAttribute("memberForm", new MemberForm());
        return "form";
    }
    
    @GetMapping("/editform")
    public String showEditForm(Model model) {
    	MemberForm form = new MemberForm();
    	model.addAttribute("memberForm", form);
        return "editForm";
    }
	

}
