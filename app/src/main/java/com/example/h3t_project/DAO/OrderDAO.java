package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  ResultSet resultSet;

  public ArrayList<Order> getOrdersForCustomer(int customerId) {
    ArrayList<Order> orders = null;
    String query = "SELECT [order_id]" +
      "      ,[product_id]" +
      "      ,tbl_products.[name]" +
      "      ,sell_price" +
      "      ,[tbl_order_product].quantity" +
      "      ,customer_id" +
      "      ,status_id" +
      "  FROM [dbo].[tbl_order_product] " +
      "  inner join dbo.tbl_products" +
      "  ON product_id = tbl_products.id" +
      "  inner join dbo.tbl_orders" +
      "  ON order_id = tbl_orders.id" +
      "  Where customer_id = ?";
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1,customerId);
      resultSet = preparedStatement.executeQuery();
      orders = new ArrayList<>();
      while (resultSet.next()){
        Order order = new Order();
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setOrderId(resultSet.getInt("order_id"));
        order.setProductId(resultSet.getInt("product_id"));
        order.setProductName(resultSet.getString("name"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setSellPrice(resultSet.getInt("sell_price"));
        order.setStatusId(resultSet.getInt("status_id"));
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }
}
