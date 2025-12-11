package com.ikeda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 注文したユーザー
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 注文日の例
   // private LocalDateTime orderDate;

    // --- getter / setter ---
    
}
