package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.ItemCart;
import com.example.h3t_project.model.ItemCartDetail;
import com.example.h3t_project.model.ItemCartWithPrice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  ResultSet rs;

  public boolean insertCart(int customerId, int productId, int quantity) {
    String query = "INSERT INTO [dbo].[tbl_cart] VALUES ( ? , ? ,?)";
    int countAffect = -1;
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      preparedStatement.setInt(2, productId);
      preparedStatement.setInt(3, quantity);
      countAffect = preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return countAffect != -1;
  }

  public ArrayList<ItemCart> getAllCartByUser(int customerId) {
    ArrayList<ItemCart> itemCarts = null;
    String query = "SELECT [customerId],[productId],[quantity] FROM [H3TSTORE].[dbo].[tbl_cart] Where customerId = ?";
    try {
      itemCarts = new ArrayList<>();
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        ItemCart itemCart = new ItemCart();
        itemCart.setCustomerId(rs.getInt("customerId"));
        itemCart.setProductId(rs.getInt("productId"));
        itemCart.setQuantity(rs.getInt("quantity"));
        itemCarts.add(itemCart);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemCarts;
  }

  public ArrayList<ItemCartWithPrice> getAllCartWithPriceByUser(int customerId) {
    ArrayList<ItemCartWithPrice> itemCartWithPrices = null;
    String query = "SELECT [customerId],[productId],[tbl_cart].[quantity],tbl_products.sell_price FROM [dbo].[tbl_cart] " +
      " inner join tbl_products on tbl_cart.productId = tbl_products.id Where customerId = ?";
    try {
      itemCartWithPrices = new ArrayList<>();
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        ItemCartWithPrice itemCartWithPrice = new ItemCartWithPrice();
        itemCartWithPrice.setCustomerId(rs.getInt("customerId"));
        itemCartWithPrice.setProductId(rs.getInt("productId"));
        itemCartWithPrice.setQuantity(rs.getInt("quantity"));
        itemCartWithPrice.setPrice(rs.getInt("sell_price"));
        itemCartWithPrices.add(itemCartWithPrice);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return itemCartWithPrices;
  }


  public ArrayList<ItemCartDetail> getAllCartDetail(int customerId) {
    ArrayList<ItemCartDetail> itemCartDetails = null;
    String query = "Select * from (SELECT [customerId]\n" +
      "      ,[productId]\n" +
      "      ,[tbl_cart].quantity\n" +
      "      ,tbl_products.name\n" +
      "      ,tbl_products.sell_price\n" +
      "      ,min(tbl_product_image.image_id) as image\n" +
      "  FROM [H3TSTORE].[dbo].[tbl_cart] inner join tbl_products\n" +
      "  on productId = tbl_products.id\n" +
      "  inner join tbl_product_image\n" +
      "  on tbl_products.id = tbl_product_image.product_id\n" +
      "  where [tbl_cart].customerId = ?\n" +
      "  group by [tbl_cart].customerId,[productId],[tbl_cart].quantity,tbl_products.name,tbl_products.sell_price\n" +
      " ) tbl_image_product_cart inner join tbl_images on tbl_image_product_cart.image = tbl_images.id";


    try {
      itemCartDetails = new ArrayList<>();
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      rs = preparedStatement.executeQuery();
      while (rs.next()) {
        ItemCartDetail itemCartDetail = new ItemCartDetail();
        itemCartDetail.setCustomerId(customerId);
        itemCartDetail.setProductId(rs.getInt("productId"));
        itemCartDetail.setQuantity(rs.getInt("quantity"));
        itemCartDetail.setName(rs.getString("name"));
        itemCartDetail.setSellPrice(rs.getInt("sell_price"));
        itemCartDetail.setImage(rs.getString("link"));
        itemCartDetails.add(itemCartDetail);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return itemCartDetails;
  }

  public boolean updateQuantityCart(int quantity, int customerId, int productId) {
    int countAffect = 0;
    String query = "UPDATE [dbo].[tbl_cart]\n" +
      "   SET [quantity] = ?\n" +
      " WHERE customerId = ? and productId = ? ";
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, quantity);
      preparedStatement.setInt(2, customerId);
      preparedStatement.setInt(3, productId);
      countAffect = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return countAffect != 0;
  }

  public boolean deleteProductInCart(int customerId, int productId) {
    String query = "DELETE FROM [dbo].[tbl_cart]\n" +
      "      WHERE customerId = ? and productId = ? ";
    int countAffect = 0;
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      preparedStatement.setInt(2, productId);
      countAffect = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return countAffect != 0;
  }

  public boolean deleteCart(int customerId) {
    String query = "DELETE FROM [dbo].[tbl_cart]\n" +
      "      WHERE customerId = ?";
    int countAffect = 0;
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, customerId);
      countAffect = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return countAffect != 0;
  }
}
