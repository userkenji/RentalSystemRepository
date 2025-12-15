package com.ikeda.service;

import java.util.List;
import java.util.Optional; // 【必須】Optional をインポート

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.presentation.form.ProductForm;
import com.ikeda.repository.DvdItemRepository;

@Service
public class DvdItemService {

    private final DvdItemRepository dvdItemRepository;

    // コンストラクタでRepositoryを注入
    public DvdItemService(DvdItemRepository dvdItemRepository) {
        this.dvdItemRepository = dvdItemRepository;
    }
    
    /**
     * 商品一覧で商品情報を更新したり新規登録したりして保存する
     */
    public void saveFromForm(ProductForm form) {

        DvdItem item;

        // 編集 or 新規 判定
        if (form.getId() != null) {
            // 編集：既存データを取得
            item = findById(form.getId());
        } else {
            // 新規
            item = new DvdItem();
        }

        // Form → Entity へコピー
        item.setTitle(form.getTitle());
        item.setDescription(form.getDescription());

        Integer notRented = (form.getNotRentedStock() != null) ? form.getNotRentedStock() : 0;
        Integer rented = (form.getRentedStock() != null) ? form.getRentedStock() : 0;

        item.setNotRentedStock(notRented);
        item.setRentedStock(rented);

        // 総在庫は自動計算（事故防止）
        item.setStock(notRented + rented);

        // 画像（今回は変更なし／既存保持）
        if (form.getImageFileName() != null) {
            item.setImageFileName(form.getImageFileName());
        } else {
            item.setImageFileName("noimage.png");
        }

        dvdItemRepository.save(item);
    }

    /**
     * DVD 全件取得（ページング対応）
     */
    public Page<DvdItem> findAll(Pageable pageable) {
        return dvdItemRepository.findAll(pageable);
    }
    /**
     * DVD 全件取得（一覧表示用）
     */
    public List<DvdItem> findAll() {
        return dvdItemRepository.findAll();
    }

    /**
     * DVD 新規登録 / 更新
     */
    public DvdItem save(DvdItem dvdItem) {
        return dvdItemRepository.save(dvdItem);
    }
    
    /**
     * IDを使って商品（DvdItem）を1件取得する
     */
    public DvdItem findById(Integer id) {
        return dvdItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("商品が見つかりません: " + id));
    }

    /**
     * DVD 詳細を ID で取得：
     */
    public Optional<DvdItem> findById(Long id) {
        return dvdItemRepository.findById(id);
    }

    /**
     * DVD を削除する機能：時間の都合上保留
     */
    public void deleteById(Long id) {
        dvdItemRepository.deleteById(id);
    }
}
