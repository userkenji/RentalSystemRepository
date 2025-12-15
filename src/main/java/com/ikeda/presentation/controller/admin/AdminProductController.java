package com.ikeda.presentation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikeda.entity.DvdItem;
import com.ikeda.presentation.form.ProductForm;
import com.ikeda.service.DvdItemService;

@Controller
public class AdminProductController {

    @Autowired
    private DvdItemService dvdItemService; // ← これ1本に統一

    // 商品一覧（ページング）
    @GetMapping("/admin/products")
    public String showProductList(
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        int pageSize = 9;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());

        Page<DvdItem> products = dvdItemService.findAll(pageable);
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    // 新規登録フォーム
    @GetMapping("/admin/product-form")
    public String showNewForm(Model model) {
    	model.addAttribute("product", new ProductForm());
        return "admin/product-form";
    }

    // 編集フォーム
    @GetMapping("/admin/product-form/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        DvdItem item = dvdItemService.findById(id);

        ProductForm form = ProductForm.fromEntity(item);
        model.addAttribute("product", form);

        return "admin/product-form";
    }

    // 保存（新規・更新 共通）
    @PostMapping("/admin/product-save")
    public String save(
            @ModelAttribute("product") ProductForm form,
            @RequestParam(name="imageFile", required=false) MultipartFile imageFile,
            RedirectAttributes ra
    ) {
        try {
            // 画像が選ばれていたら：保存して、DBに入れるファイル名を確定させる
            if (imageFile != null && !imageFile.isEmpty()) {

                // ★おすすめ：衝突回避でUUID名にする（その場合DBもこの名前を入れる）
                String original = imageFile.getOriginalFilename();
                String ext = "";
                if (original != null && original.contains(".")) {
                    ext = original.substring(original.lastIndexOf("."));
                }
                String fileName = java.util.UUID.randomUUID().toString() + ext;

                java.nio.file.Path savePath =
                        java.nio.file.Paths.get("src/main/resources/static/img/" + fileName);

                java.nio.file.Files.copy(
                        imageFile.getInputStream(),
                        savePath,
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );

                // ✅ DBに入れる値
                form.setImageFileName(fileName);
            }

            dvdItemService.saveFromForm(form);
            ra.addFlashAttribute("success", "保存しました");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "保存に失敗しました: " + e.getMessage());
        }
        return "redirect:/admin/products";
    }
}
