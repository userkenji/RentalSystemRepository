package com.ikeda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikeda.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
