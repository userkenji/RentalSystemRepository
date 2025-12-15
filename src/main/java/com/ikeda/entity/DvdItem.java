package com.ikeda.entity;

import java.time.LocalDateTime; // 【追加】タイムスタンプ用

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dvd_items")
public class DvdItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title") // 明示的に指定
    private String title;
    
    @Column(name = "image_file_name")
    private String imageFileName;
    
    @Column(name = "description")
    private String description;
    

    private Integer stock; // HTMLに合わせて在庫数を追加

    // 貸出中の在庫数
    @Column(name = "rented_stock")
    private Integer rentedStock;

    // 未貸出（店内在庫）の在庫数
    @Column(name = "not_rented_stock")
    private Integer notRentedStock;
    // ----------------------
    

    @Column(name = "created_at") // ★ 追加: タイムスタンプ
    private LocalDateTime createdAt; 
    
    @Column(name = "updated_at") // ★ 追加: タイムスタンプ
    private LocalDateTime updatedAt;
    
    
     // ... Getter and Setter ...
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFileName() {
        return imageFileName;
    }
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    

//    public Integer getPricePerDay() {
//        return pricePerDay;
//    }
//
//    public void setPricePerDay(Integer pricePerDay) {
//        this.pricePerDay = pricePerDay;
//    }
    
 // --- 【在庫管理: stock の Getter/Setter】 ---

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    // ------------------------------------------------------------------
    //  修正箇所 2: 新規フィールドの Getter/Setter を追加
    // ------------------------------------------------------------------
    public Integer getRentedStock() {
        return rentedStock;
    }
    public void setRentedStock(Integer rentedStock) {
        this.rentedStock = rentedStock;
    }
    
    public Integer getNotRentedStock() {
        return notRentedStock;
    }
    public void setNotRentedStock(Integer notRentedStock) {
        this.notRentedStock = notRentedStock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
