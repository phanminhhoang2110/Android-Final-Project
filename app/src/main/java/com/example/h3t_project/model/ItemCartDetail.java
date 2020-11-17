package com.example.h3t_project.model;

public class ItemCartDetail extends ItemCart {
  private String image;
  private String name;
  private int sellPrice;

  public ItemCartDetail(int customerId, int productId, int quantity, String image, String name, int sellPrice) {
    super(customerId, productId, quantity);
    this.image = image;
    this.name = name;
    this.sellPrice = sellPrice;
  }

  public ItemCartDetail(String image, String name, int sellPrice) {
    this.image = image;
    this.name = name;
    this.sellPrice = sellPrice;
  }

  public ItemCartDetail() {

  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(int sellPrice) {
    this.sellPrice = sellPrice;
  }

}
