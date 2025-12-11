package com.ikeda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

@Service
public class DvdItemService {

    private final DvdItemRepository dvdItemRepository;

    // コンストラクタでRepositoryを注入
    public DvdItemService(DvdItemRepository dvdItemRepository) {
        this.dvdItemRepository = dvdItemRepository;
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
     * DVD 詳細を ID で取得：
     */
    /*  public Optional<DvdItem> findById(Integer id) {
        return dvdItemRepository.findById(id);
    }*/

    /**
     * DVD を削除する機能：時間の都合上保留
     */
    /*  public void deleteById(Integer id) {
        dvdItemRepository.deleteById(id);
    }*/
}
