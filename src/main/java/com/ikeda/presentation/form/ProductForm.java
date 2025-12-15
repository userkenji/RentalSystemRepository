package com.ikeda.presentation.form;

//用途:商品登録・編集のフォーム入力データ	
//データ：商品名、在庫、料金など
public class ProductForm {

    private String title;
    private String description;
    private Integer price;
    private Integer stock;

    // ※ 画像ファイルを受け取る処理は、通常 MultipartFile を使用しますが、
    //    今回は一旦データフローの核となるフィールドのみ追加します。
    //    private MultipartFile imageFile; 

//    商品名 (title)
//    商品説明 (description)
//    料金/日 (pricePerDay)
//    在庫 (stock)
    
    // --- Getter and Setter ---

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPrice() { return price; }
    public void setPricePerDay(Integer price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}