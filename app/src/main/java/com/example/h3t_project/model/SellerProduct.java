package com.example.h3t_project.model;

public class SellerProduct {
    int id;
    String productName;
    int productImg;
    String brand;
    String origin;
    String unit;
    int quantity;
    int height;
    int width;
    int lenght;
    int weight;
    int originPrice;
    int price;
    int coupon;
    String des;
    String type;

    public SellerProduct() {
    }

    public SellerProduct(int id, String productName, int productImg, String brand, String origin, String unit, int quantity, int height, int width, int lenght, int weight, int price, int originPrice, int coupon, String des, String type) {
        this.id = id;
        this.productName = productName;
        this.productImg = productImg;
        this.brand = brand;
        this.origin = origin;
        this.unit = unit;
        this.quantity = quantity;
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.weight = weight;
        this.price = price;
        this.originPrice = originPrice;
        this.coupon = coupon;
        this.des = des;
        this.type = type;
    }

    public SellerProduct(int id, String productName, int productImg, int price, int quantity) {
        this.id = id;
        this.productName = productName;
        this.productImg = productImg;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(int originPrice) {
        this.originPrice = originPrice;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
