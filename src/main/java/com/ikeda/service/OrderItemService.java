package com.ikeda.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikeda.entity.OrderItem;
import com.ikeda.repository.OrderItemRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> findAll() {
        return repository.findAll();
    }
}
