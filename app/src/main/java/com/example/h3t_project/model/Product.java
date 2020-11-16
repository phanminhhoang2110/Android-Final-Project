package com.example.h3t_project.model;

public class Product {
  private int id;
  private String name;
  private int origin_price;
  private int sell_price;
  private int category_id;
  private String category_name;
  private String model;
  private String description;
  private String guarantee;
  private String brand;
  private int weight;
  private int origin;
  private String color;
  private int height;
  private int size_id;
  private String size;
  private String material;
  private int quantity;
  private String coupon_code;
  private int coupon_value;

  public int getQuantityInCart() {
    return quantityInCart;
  }

  public void setQuantityInCart(int quantityInCart) {
    this.quantityInCart = quantityInCart;
  }

  private int image_id;
  private String link_image;
  private int quantityInCart;

  public Product() {
  }

  public Product(int id, String name, int origin_price, int sell_price, int category_id, String category_name, String model, String description, String guarantee, String brand, int weight, int origin, String color, int height, int size_id, String size, String material, int quantity, String coupon_code, int coupon_value, int image_id, String link_image) {
    this.id = id;
    this.name = name;
    this.origin_price = origin_price;
    this.sell_price = sell_price;
    this.category_id = category_id;
    this.category_name = category_name;
    this.model = model;
    this.description = description;
    this.guarantee = guarantee;
    this.brand = brand;
    this.weight = weight;
    this.origin = origin;
    this.color = color;
    this.height = height;
    this.size_id = size_id;
    this.size = size;
    this.material = material;
    this.quantity = quantity;
    this.coupon_code = coupon_code;
    this.coupon_value = coupon_value;
    this.image_id = image_id;
    this.link_image = link_image;
  }

  public Product(int id, String name, int origin_price, int sell_price, String link_image) {
    this.id = id;
    this.name = name;
    this.origin_price = origin_price;
    this.sell_price = sell_price;
    this.link_image = link_image;
  }

  public Product(int id, String name, String link_image, int origin_price, int sell_price, int quantity) {
    this.id = id;
    this.name = name;
    this.link_image = link_image;
    this.origin_price = origin_price;
    this.sell_price = sell_price;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getOrigin_price() {
    return origin_price;
  }

  public void setOrigin_price(int origin_price) {
    this.origin_price = origin_price;
  }

  public int getSell_price() {
    return sell_price;
  }

  public void setSell_price(int sell_price) {
    this.sell_price = sell_price;
  }

  public int getCategory_id() {
    return category_id;
  }

  public void setCategory_id(int category_id) {
    this.category_id = category_id;
  }

  public String getCategory_name() {
    return category_name;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGuarantee() {
    return guarantee;
  }

  public void setGuarantee(String guarantee) {
    this.guarantee = guarantee;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getOrigin() {
    return origin;
  }

  public void setOrigin(int origin) {
    this.origin = origin;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getSize_id() {
    return size_id;
  }

  public void setSize_id(int size_id) {
    this.size_id = size_id;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getCoupon_code() {
    return coupon_code;
  }

  public void setCoupon_code(String coupon_code) {
    this.coupon_code = coupon_code;
  }

  public int getCoupon_value() {
    return coupon_value;
  }

  public void setCoupon_value(int coupon_value) {
    this.coupon_value = coupon_value;
  }

  public String getLink_image() {
    return link_image;
  }

  public void setLink_image(String link_image) {
    this.link_image = link_image;
  }

  public int getImage_id() {
    return image_id;
  }

  public void setImage_id(int image_id) {
    this.image_id = image_id;
  }
}
