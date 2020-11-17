package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.CouponItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  ResultSet resultSet = null;

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

  public List<CouponItem> getCoupons() {
    List<CouponItem> couponItems = null;
    try {
      String query = "SELECT [code],[value] FROM [dbo].[tbl_coupons]";
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      resultSet = preparedStatement.executeQuery();
      couponItems = new ArrayList<>();
      while (resultSet.next()) {
        CouponItem couponItem = new CouponItem();
        couponItem.setCodeCoupon(resultSet.getString("code"));
        int valueCoupon = Integer.parseInt(resultSet.getString("value"));
        couponItem.setValueCoupon(valueCoupon);
        couponItems.add(couponItem);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return couponItems;
  }

  public int deleteCoupon(String code) {
    try {
      String query = "DELETE FROM [dbo].[tbl_coupons] WHERE code = ?";
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, code);
      int rowAffected = preparedStatement.executeUpdate();
      return rowAffected;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}
