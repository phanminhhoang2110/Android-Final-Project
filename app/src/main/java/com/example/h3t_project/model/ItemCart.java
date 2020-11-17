package com.example.h3t_project.model;

public class ItemCart {
  private int customerId;
  private int productId;
  private int quantity;

  public ItemCart(int customerId, int productId, int quantity) {
    this.customerId = customerId;
    this.productId = productId;
    this.quantity = quantity;
  }

  public ItemCart() {
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
