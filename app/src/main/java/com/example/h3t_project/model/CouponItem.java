package com.example.h3t_project.model;

public class CouponItem {
  private String codeCoupon;
  private int valueCoupon;

  public CouponItem(String codeCoupon, int valueCoupon) {
    this.codeCoupon = codeCoupon;
    this.valueCoupon = valueCoupon;
  }

  public CouponItem() {
  }

  public String getCodeCoupon() {
    return codeCoupon;
  }

  public void setCodeCoupon(String codeCoupon) {
    this.codeCoupon = codeCoupon;
  }

  public int getValueCoupon() {
    return valueCoupon;
  }

  public void setValueCoupon(int valueCoupon) {
    this.valueCoupon = valueCoupon;
  }
}
