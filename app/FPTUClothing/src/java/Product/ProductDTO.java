/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

/**
 *
 * @author Acer
 */
public class ProductDTO {
    private int productID;
    private String nameProduct;
    private String description;
    private String category;
    private int price;
    private int quantity;
    private String urlimage;
    private int orderQuantity;
    
    public ProductDTO() {
    }
    public ProductDTO(String nameProduct, String description, String category, int price, int quantity) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(int productID, String nameProduct, String description, String category, int price, int quantity, String urlimage) {
        this.productID = productID;
        this.nameProduct = nameProduct;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.urlimage = urlimage;
    }

    public ProductDTO(int productID, String nameProduct, String description, String category, int price, int quantity) {
        this.productID = productID;
        this.nameProduct = nameProduct;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    

    public ProductDTO(ProductDTO another) {
        this.productID = another.productID;
        this.nameProduct = another.nameProduct;
        this.description = another.description;
        this.category = another.category;
        this.price = another.price;
        this.quantity = another.quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    
    
}
