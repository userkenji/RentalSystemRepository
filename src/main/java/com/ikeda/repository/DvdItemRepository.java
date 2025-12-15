package com.ikeda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikeda.entity.DvdItem;

public interface DvdItemRepository extends JpaRepository<DvdItem, Long> {

}
