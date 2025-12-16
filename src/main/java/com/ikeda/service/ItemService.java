package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

@Service
public class ItemService {

    @Autowired
    private DvdItemRepository dvdItemRepository;

    /**
     * DVD一覧取得（ページネーションあり）
     */
    public Page<DvdItem> findAll(Pageable pageable) {
        return dvdItemRepository.findAll(pageable);
    }
}
