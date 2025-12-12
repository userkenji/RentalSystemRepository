package com.ikeda.entity;

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
    private Integer id;

    private String title;

    @Column(name = "image_file_name")
    private String imageFileName;
    
    @Column(name = "description")
    private String description;
    
 // --- 【修正・追加箇所 1】 料金フィールドを追加 ---
//    @Column(name = "price_per_day") // DBのカラム名に合わせて設定（仮）
//    private Integer pricePerDay; 
    // ------------------------------------------------
    
 // --- 【在庫管理】 ---
    // 在庫総数
    private Integer stock; // HTMLに合わせて在庫数を追加

    // 貸出中の在庫数
    @Column(name = "rented_stock")
    private Integer rentedStock;

    // 未貸出（店内在庫）の在庫数
    @Column(name = "not_rented_stock")
    private Integer notRentedStock;
    // ----------------------

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public Integer getRentedStock() { return rentedStock; }
    public void setRentedStock(Integer rentedStock) { this.rentedStock = rentedStock; }

    public Integer getNotRentedStock() { return notRentedStock; }
    public void setNotRentedStock(Integer notRentedStock) { this.notRentedStock = notRentedStock; }
    // ----------------------------------------------
}
