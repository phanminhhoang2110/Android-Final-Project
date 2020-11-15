package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.example.h3t_project.DatabaseM.DatabaseManager.connect;

public class CustomerViewProductDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  ResultSet resultSet = null;

  public List<Product> getAllProductViewByCustomer(int categoryId, int price_DESC, int price_ASC) {
    List<Product> products = null;
    try {
      String query = "SELECT  [dbo].[tbl_products].[id]\n" +
        "                    ,[dbo].[tbl_products].[name]\n" +
        "                    ,[dbo].[tbl_products].[origin_price]\n" +
        "                    ,[dbo].[tbl_products].[sell_price]\n" +
        "                    ,[dbo].[tbl_images].[link]\n" +
        "                    FROM [dbo].[tbl_products]\n" +
        "                    inner join [dbo].[tbl_product_image] on [dbo].[tbl_product_image].[product_id] = [dbo].[tbl_products].[id]\n" +
        "                    inner join [dbo].[tbl_images] on [dbo].[tbl_images].[id] = [dbo].[tbl_product_image].[image_id]\n";
      if (categoryId != -1) {
        query += " WHERE catergory_id = ? ";
      }
      if (price_ASC == 1) {
        price_DESC = 0;
        query += " ORDER BY [dbo].[tbl_products].[origin_price] ASC";
      } else if (price_DESC == 1) {
        price_ASC = 0;
        query += " ORDER BY [dbo].[tbl_products].[origin_price] DESC";
      }
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      if (categoryId != -1) {
        preparedStatement.setInt(1, categoryId);
      }
      resultSet = preparedStatement.executeQuery();
      products = new ArrayList<>();
      while (resultSet.next()) {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setOrigin_price(resultSet.getInt("origin_price"));
        product.setSell_price(resultSet.getInt("sell_price"));
        product.setLink_image(resultSet.getString("link"));
        products.add(product);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return products;
  }

  public List<Product> getProductById(int productId){
    List<Product> products = null;
      try {
        String query = "SELECT [dbo].[tbl_products].[id]\n" +
                "      ,[dbo].[tbl_products].[name]\n" +
                "      ,[dbo].[tbl_products].[origin_price]\n" +
                "      ,[dbo].[tbl_products].[sell_price]\n" +
                "      ,[dbo].[tbl_products].[catergory_id]\n" +
                "\t  ,[dbo].[tbl_categories].[name] as [category_name]\n" +
                "      ,[dbo].[tbl_products].[model]\n" +
                "      ,[dbo].[tbl_products].[description]\n" +
                "      ,[dbo].[tbl_products].[guarantee]\n" +
                "      ,[dbo].[tbl_products].[brand]\n" +
                "      ,[dbo].[tbl_products].[origin]\n" +
                "      ,[dbo].[tbl_products].[color]\n" +
                "      ,[dbo].[tbl_products].[height]\n" +
                "      ,[dbo].[tbl_products].[size_id]\n" +
                "\t  ,[dbo].[tbl_size].[size]\n" +
                "      ,[dbo].[tbl_products].[material]\n" +
                "      ,[dbo].[tbl_products].[quantity]\n" +
                "      ,[dbo].[tbl_products].[coupon_code]\n" +
                "\t  ,[dbo].[tbl_coupons].[value] as [coupon_value]\n" +
                "\t  ,[dbo].[tbl_images].[link]\n" +
                "  FROM [H3TSTORE].[dbo].[tbl_products] \n" +
                "  inner join [dbo].[tbl_product_image] on[dbo].[tbl_product_image].[product_id] = [dbo].[tbl_products].[id]\n" +
                "  inner join [dbo].[tbl_images] on [dbo].[tbl_images].[id] = [dbo].[tbl_product_image].[image_id]\n" +
                "  inner join [dbo].[tbl_categories] on [dbo].[tbl_categories].[id] = [dbo].[tbl_products].[catergory_id]\n" +
                "  left join [dbo].[tbl_coupons] on [H3TSTORE].[dbo].[tbl_coupons].[code] = [dbo].[tbl_products].[coupon_code]\n" +
                "  left join [dbo].[tbl_size] on [dbo].[tbl_size].[id] = [dbo].[tbl_products].[size_id]\n" +
                "  WHERE [dbo].[tbl_products].[id] = ? ";
        connection = connect();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, productId);
        resultSet = preparedStatement.executeQuery();
        products = new ArrayList<>();
        while (resultSet.next()){
          Product product = new  Product();
          product.setId(resultSet.getInt("id"));
          product.setName(resultSet.getString("name"));
          product.setOrigin_price(resultSet.getInt("origin_price"));
          product.setSell_price(resultSet.getInt("sell_price"));
          product.setCategory_name(resultSet.getString("category_name"));
          product.setDescription(resultSet.getString("description"));
          product.setGuarantee(resultSet.getString("guarantee"));
          product.setBrand(resultSet.getString("brand"));
          product.setQuantity(resultSet.getInt("quantity"));
          product.setCoupon_value(resultSet.getInt("coupon_value"));
          product.setLink_image(resultSet.getString("link"));
          products.add(product);
        }

      }catch (Exception e){

      }
      return products;
  }

}
