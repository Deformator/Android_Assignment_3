package com.example.andriidamm.andriidamm_mapd711_onlinepurchase.models;

/**
 * Model class for Product.
 */

public class Product {

    private int productId;
    private String productName;
    private int price;
    private int quantity;
    private String category;
    private String image;

    public Product(int productId, String image, String productName, String category, int price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Product(String productName, String category, int price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public Product(String image, String productName, String category, int price) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
