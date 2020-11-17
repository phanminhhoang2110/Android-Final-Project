package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Address;
import com.example.h3t_project.model.Order;
import com.example.h3t_project.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
      preparedStatement.setInt(1, customerId);
      resultSet = preparedStatement.executeQuery();
      orders = new ArrayList<>();
      while (resultSet.next()) {
        Order order = new Order();
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setOrderId(resultSet.getInt("order_id"));
        order.setProductId(resultSet.getInt("product_id"));
        order.setProductName(resultSet.getString("name"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setSellPrice(resultSet.getInt("sell_price"));
        order.setStatusId(resultSet.getInt("status_id"));
        Address address = new Address();
        address.setAddress(resultSet.getString("address"));
        order.setAddress(address);
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

  public ArrayList<Order> getDetailOrdersForSeller(int orderId, int statusId) {
    ArrayList<Order> orders = null;
    String query = "SELECT [order_id]\n" +
      "                   ,tbl_products.[name] as product_name\n" +
      "                   ,sell_price \n" +
      "                   ,[tbl_order_product].quantity \n" +
      "                   ,customer_id \n" +
      "                   ,tbl_status.id as status_id\n" +
      "                   ,tbl_users.fullname\n" +
      "                   ,tbl_destinations.address + ' - ' + tbl_destinations.ward+ ' - ' + tbl_destinations.district+ ' - ' + tbl_destinations.province as address\t\t   \n" +
      "                   ,tbl_images.link\n" +
      "                  FROM [dbo].[tbl_order_product] \n" +
      "                  inner join (select product_id, max(image_id) image_id from tbl_product_image group by product_id) as tbl_one_image_product \n" +
      "                  ON tbl_order_product.product_id = tbl_one_image_product.product_id\n" +
      "                  inner join tbl_images\n" +
      "                  ON tbl_one_image_product.image_id = tbl_images.id \n" +
      "                  inner join dbo.tbl_products \n" +
      "                  ON tbl_order_product.product_id = tbl_products.id \n" +
      "                  inner join dbo.tbl_orders \n" +
      "                  ON order_id = tbl_orders.id \n" +
      "                  inner join dbo.tbl_status\n" +
      "                  ON status_id = tbl_status.id\n" +
      "                  inner join dbo.tbl_users\n" +
      "                  ON customer_id = tbl_users.id\n" +
      "                  inner join dbo.tbl_destinations\n" +
      "                  ON destination_id = tbl_destinations.id\n" +
      "              Where tbl_order_product.order_id = ? and tbl_status.id = ?";
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, orderId);
      preparedStatement.setInt(2, statusId);
      resultSet = preparedStatement.executeQuery();
      orders = new ArrayList<>();
      while (resultSet.next()) {
        Order order = new Order();
        order.setCustomerId(resultSet.getInt("customer_id"));
        order.setOrderId(resultSet.getInt("order_id"));
        order.setProductId(resultSet.getInt("product_id"));
        order.setProductName(resultSet.getString("product_name"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setSellPrice(resultSet.getInt("sell_price"));
        order.setStatusId(resultSet.getInt("status_id"));
        Product product = new Product();
        product.setLink_image(resultSet.getString("link"));
        order.setProduct(product);
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

  public ArrayList<Order> getListOrdersForSeller(int statusId) {
    ArrayList<Order> orders = null;
    String query = "SELECT distinct tbl_order_product.order_id\n" +
      "            ,tbl_products.id as product_id\n" +
      "            ,tbl_products.[name]  \n" +
      "            ,[tbl_order_product].quantity  \n" +
      "            ,tbl_status.id as status_id\n" +
      "            ,tbl_status.status\n" +
      "            ,tbl_images.link\n" +
      "            FROM (select order_id, max(product_id) product_id from tbl_order_product group by order_id) as tbl_one_order \n" +
      "            inner join (select product_id, max(image_id) image_id from tbl_product_image group by product_id) as tbl_one_image_product \n" +
      "            ON tbl_one_order.product_id = tbl_one_image_product.product_id\n" +
      "            inner join tbl_images\n" +
      "            ON tbl_one_image_product.image_id = tbl_images.id \n" +
      "            inner join dbo.tbl_products \n" +
      "            ON tbl_one_order.product_id = tbl_products.id \n" +
      "            inner join dbo.tbl_order_product\n" +
      "            ON tbl_one_order.product_id = tbl_order_product.product_id\n" +
      "            inner join dbo.tbl_orders\n" +
      "            ON tbl_order_product.order_id = tbl_orders.id\n" +
      "            inner join dbo.tbl_status\n" +
      "            ON status_id = tbl_status.id ";
    if (statusId != -1) {
      query += " where status_id = ? ";
    }
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      if (statusId != -1){
        preparedStatement.setInt(1, statusId);
      }
      resultSet = preparedStatement.executeQuery();
      orders = new ArrayList<>();
      while (resultSet.next()) {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setProductId(resultSet.getInt("product_id"));
        order.setProductName(resultSet.getString("name"));
        order.setQuantity(resultSet.getInt("quantity"));
        order.setStatusId(resultSet.getInt("status_id"));
        Product product = new Product();
        product.setLink_image(resultSet.getString("link"));
        order.setProduct(product);
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

  public boolean newOrder(int customerId, int destinationId, List<Integer> products) {
    String insertOrder = "INSERT INTO [dbo].[tbl_orders] VALUES (? , ? , ?)";
    String insertOrderProduct = "INSERT INTO [dbo].[tbl_order_product] VALUES (? , ? , ?)";
    try {
      PreparedStatement insertOrderStatement,insertOrderProductStatement;
      connection = connect();
      connection.setAutoCommit(false);
      insertOrderStatement = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
      insertOrderStatement.setInt(1, customerId);
      insertOrderStatement.setInt(2,3);
      insertOrderStatement.setInt(3,destinationId);
      insertOrderStatement.executeUpdate();
      int idLatest = 0;
      try (ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          idLatest = (int) generatedKeys.getLong(1);
        }
      }
      for (int i = 0; i < products.size(); i++) {
        insertOrderProductStatement = connection.prepareStatement(insertOrderProduct);
        insertOrderProductStatement.setInt(1, idLatest);
        insertOrderProductStatement.setInt(2, products.get(i));
        insertOrderProductStatement.setInt(3, 1);
        insertOrderProductStatement.executeUpdate();
      }

      connection.commit();

    } catch (Exception e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      return false;
    }
    return true;
  }
}
