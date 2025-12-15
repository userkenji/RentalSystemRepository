package com.ikeda.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

@Service
public class ItemService {
	@Autowired
    private DvdItemRepository dvdItemRepository;

    public List<DvdItem> findAll() {
        return dvdItemRepository.findAll();
    }


}
