package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SellerProductDAO extends DatabaseManager {
    PreparedStatement preparedStatement;
    ResultSet resultSet = null;

    public List<Product> getProducts() {
        List<Product> products = null;
        try {
            String query = "SELECT  [dbo].[tbl_products].[id]\n" +
                    "                    ,[dbo].[tbl_products].[name]\n" +
                    "                    ,[dbo].[tbl_products].[origin_price]\n" +
                    "                    ,[dbo].[tbl_products].[sell_price]\n" +
                    "                    ,[dbo].[tbl_products].[quantity]\n" +
                    "                    ,[dbo].[tbl_images].[link]\n" +
                    "                    FROM [dbo].[tbl_products]\n" +
                    "                    inner join [dbo].[tbl_product_image] on [dbo].[tbl_product_image].[product_id] = [dbo].[tbl_products].[id]\n" +
                    "                    inner join [dbo].[tbl_images] on [dbo].[tbl_images].[id] = [dbo].[tbl_product_image].[image_id]\n";
            connection = connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setLink_image(resultSet.getString("link"));
                product.setOrigin_price(resultSet.getInt("origin_price"));
                product.setSell_price(resultSet.getInt("sell_price"));
                product.setQuantity(resultSet.getInt("quantity"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
