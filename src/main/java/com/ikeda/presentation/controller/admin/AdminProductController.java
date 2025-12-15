package com.ikeda.presentation.controller.admin;

import java.time.LocalDateTime; // 必須追加: タイムスタンプ用

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikeda.entity.DvdItem; // DvdItem Entity をインポート
import com.ikeda.presentation.form.ProductForm;
import com.ikeda.service.DvdItemService;

@Controller
public class AdminProductController {
	
	@Autowired
	private DvdItemService productService;

    // 商品一覧
    @GetMapping("/admin/products")
    public String showProductList(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "admin/product-list";//商品一覧ページへ遷移する
    }

    // 商品新規登録フォーム
    @GetMapping("/admin/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductForm());
        return "admin/product-form";//新規商品登録ページへ遷移する
    }
    
    
    
    
 // 商品新規登録実行 (POST /admin/product/save)
    @PostMapping("/admin/products/save")
    public String registerProduct(
            @ModelAttribute("product") ProductForm form, 
            RedirectAttributes redirectAttributes) {
        
        // 1. Form -> Entity への変換
        DvdItem dvdItem = new DvdItem();
        
        // データのコピー (IDは新規登録なので不要)
        dvdItem.setTitle(form.getTitle());
        dvdItem.setDescription(form.getDescription());
<<<<<<< HEAD
     // 修正 1-1: setPrice を使用し、form.getPrice() から値を取得
        dvdItem.setPrice(form.getPrice()); 
=======
//        dvdItem.setPricePerDay(form.getPricePerDay());
>>>>>>> refs/heads/main
        dvdItem.setStock(form.getStock());
        
     //  修正 1-2: 新しい在庫フィールドの初期化(使う場合はコメントアウトを解除)
        dvdItem.setRentedStock(0);
        dvdItem.setNotRentedStock(form.getStock());
        
        
     // ★ 修正 3: 画像ファイルの処理
        String imageFileName = "placeholder.jpg"; // デフォルト値
        if (form.getImageFile() != null && !form.getImageFile().isEmpty()) {
            // ファイル名を取得（実際の保存処理はスキップ）
            imageFileName = form.getImageFile().getOriginalFilename();
        }
        // 画像ファイルの処理は今回はスキップし、仮のファイル名を設定
        // ※ 実際のアプリケーションでは、ここでファイルアップロード処理と MultipartFile が必要
        dvdItem.setImageFileName(imageFileName);
        
     // ★ 修正 4: タイムスタンプの追加
        dvdItem.setCreatedAt(LocalDateTime.now());
        dvdItem.setUpdatedAt(LocalDateTime.now());

        // 2. Serviceへの保存委譲
        productService.save(dvdItem);
        
        // 3. 成功メッセージをリダイレクト先へ渡し、一覧ページへ遷移
        redirectAttributes.addFlashAttribute("success", "商品を新規登録しました。");
        return "redirect:/admin/products"; 
    }
 // --- 【追加箇所 1: 削除確認画面表示 (GET)】 ---
<<<<<<< HEAD
    @GetMapping("/admin/products/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {    	// 1. Optional<DvdItem> として取得
        DvdItem product = productService.findById(id).orElse(null);
     // 2. nullチェックでエラーハンドリングし、見つからなければ一覧へリダイレクト
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "指定された商品が見つかりませんでした。");
            return "redirect:/admin/products"; 
        }
        // 2. データを Model に格納
        model.addAttribute("product", product);
        
        // 3. 削除確認画面へ遷移
        return "admin/product-delete-confirm"; // HTMLファイル名に合わせて修正
    }
=======
//    @GetMapping("/admin/products/delete/{id}")
//    public String showDeleteConfirm(@PathVariable Integer id, Model model) {
//        // 1. Service を使ってIDから商品情報を取得 (OptionalなのでorElseThrowなどで処理)
//        DvdItem product = productService.findById(id)
//                                        .orElseThrow(() -> new IllegalArgumentException("無効な商品IDです: " + id));
//        
//        // 2. データを Model に格納
//        model.addAttribute("product", product);
//        
//        // 3. 削除確認画面へ遷移
//        return "admin/product-delete-confirm"; // HTMLファイル名に合わせて修正
//    }
>>>>>>> refs/heads/main

    // --- 【追加箇所 2: 削除実行 (POST)】 ---
<<<<<<< HEAD
    @PostMapping("/admin/product-delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        
        // 1. Service へ削除処理を委譲
        productService.deleteById(id);
        
        // 2. 成功メッセージをセットし、一覧へリダイレクト
        redirectAttributes.addFlashAttribute("success", "商品 ID: " + id + " を削除しました。");
        return "redirect:/admin/products"; 
    }
=======
//    @PostMapping("/admin/product-delete/{id}")
//    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
//        
//        // 1. Service へ削除処理を委譲
//        productService.deleteById(id);
//        
//        // 2. 成功メッセージをセットし、一覧へリダイレクト
//        redirectAttributes.addFlashAttribute("success", "商品 ID: " + id + " を削除しました。");
//        return "redirect:/admin/products"; 
//    }
>>>>>>> refs/heads/main
}