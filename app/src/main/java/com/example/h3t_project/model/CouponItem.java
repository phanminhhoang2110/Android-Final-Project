package com.example.h3t_project.model;

public class CouponItem {
  private String codeCoupon;
  private String valueCoupon;

  public CouponItem(String codeCoupon, String valueCoupon) {
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

  public String getValueCoupon() {
    return valueCoupon;
  }

  public void setValueCoupon(String valueCoupon) {
    this.valueCoupon = valueCoupon;
  }
}
