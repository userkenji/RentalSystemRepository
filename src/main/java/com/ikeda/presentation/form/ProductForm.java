package com.ikeda.presentation.form;

<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile; // 画像を受け取るために必要
=======
import com.ikeda.entity.DvdItem;

>>>>>>> refs/heads/main
//用途:商品登録・編集のフォーム入力データ	
//データ：商品名、在庫、料金など
public class ProductForm {

    // ====== DBと紐づく基本情報 ======
    private Integer id;              // 編集時に必須
    private String title;
    private String description;
<<<<<<< HEAD
    private Integer price;// DBのpriceと一致
    private Integer stock;
    private MultipartFile imageFile;// 必須追加: 画像ファイルを受け取るフィールド
    // ※ 画像ファイルを受け取る処理は、通常 MultipartFile を使用しますが、
    //    今回は一旦データフローの核となるフィールドのみ追加します。
    //    private MultipartFile imageFile; 
=======

    // ====== 在庫情報 ======
    private Integer notRentedStock;  // 店内在庫
    private Integer rentedStock;     // 貸出在庫
    private Integer stock;           // 総在庫（表示用）
>>>>>>> refs/heads/main

<<<<<<< HEAD
//    商品名 (title)
//    商品説明 (description)
//    料金/日 (price)
//    在庫 (stock)
=======
    // ====== 画像 ======
    private String imageFileName;    // 既存画像保持用

    // ====== Entity → Form 変換 ======
    public static ProductForm fromEntity(DvdItem item) {
        ProductForm form = new ProductForm();

        form.setId(item.getId());
        form.setTitle(item.getTitle());
        form.setDescription(item.getDescription());
        form.setNotRentedStock(item.getNotRentedStock());
        form.setRentedStock(item.getRentedStock());
        form.setStock(item.getStock());
        form.setImageFileName(item.getImageFileName());

        return form;
    }
>>>>>>> refs/heads/main
    
    // --- Getter and Setter ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

<<<<<<< HEAD
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
=======
    public Integer getNotRentedStock() { return notRentedStock; }
    public void setNotRentedStock(Integer notRentedStock) { this.notRentedStock = notRentedStock; }

    public Integer getRentedStock() { return rentedStock; }
    public void setRentedStock(Integer rentedStock) { this.rentedStock = rentedStock; }
>>>>>>> refs/heads/main

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    
<<<<<<< HEAD
 //  必須追加: imageFile の Getter/Setter
    public MultipartFile getImageFile() { return imageFile; }
    public void setImageFile(MultipartFile imageFile) { this.imageFile = imageFile; }
=======
    public String getImageFileName() { return imageFileName; }
    public void setImageFileName(String imageFileName) { this.imageFileName = imageFileName; }
>>>>>>> refs/heads/main
}