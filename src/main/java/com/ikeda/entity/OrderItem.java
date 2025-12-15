package com.ikeda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//Order と Product を結びつける形になる
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
 // ★ 修正箇所: DB側のカラム名 dvd_item_id に合わせる
    @JoinColumn(name = "dvd_item_id") 
    private DvdItem product; // フィールド名は product のままでOKです

    private Integer quantity;
    private Integer price;

    // --- getter / setter ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DvdItem getProduct() {
        return product;
    }

    public void setProduct(DvdItem product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
