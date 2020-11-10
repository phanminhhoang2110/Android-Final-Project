package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.CouponItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CouponDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  public boolean addCoupon(CouponItem couponItem) {
    try {
      String query = "INSERT INTO [dbo].[tbl_coupons] VALUES ( ?, ? )";
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, couponItem.getCodeCoupon());
      preparedStatement.setInt(2, couponItem.getValueCoupon());
      preparedStatement.execute();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
