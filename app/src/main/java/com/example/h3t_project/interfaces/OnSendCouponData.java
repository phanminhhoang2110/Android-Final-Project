package com.example.h3t_project.interfaces;

import com.example.h3t_project.model.CouponItem;

public interface OnSendCouponData {
  void onSendCouponData(CouponItem couponItem);

  void onSendDestinationData(String name, String phone, int destinationId);
}
