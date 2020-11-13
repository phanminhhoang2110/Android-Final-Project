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

    public List<Product> getAllProductViewByCustomer(String category_name, String product_name, int price_DESC, int price_ASC) {
        List<Product> products = null;
        try {
            String query = "SELECT  [dbo].[tbl_products].[id]\n" +
                    "      ,[dbo].[tbl_products].[name]\n" +
                    "      ,[dbo].[tbl_products].[origin_price]\n" +
                    "      ,[dbo].[tbl_products].[sell_price]\n" +
                    "\t  ,[dbo].[tbl_categories].[name] as [category_name]\n" +
                    "\t  ,[dbo].[tbl_images].[link]\n" +
                    "  FROM [dbo].[tbl_products] \n" +
                    "  inner join [dbo].[tbl_product_image] on [dbo].[tbl_product_image].[product_id] = [dbo].[tbl_products].[id]\n" +
                    "  inner join [dbo].[tbl_images] on [dbo].[tbl_images].[id] = [dbo].[tbl_product_image].[image_id]\n" +
                    "  inner join [dbo].[tbl_categories] on [dbo].[tbl_categories].[id] = [dbo].[tbl_products].[catergory_id]\n" +
                    "\n" +
                    "  WHERE [dbo].[tbl_categories].[name] like N'%" + category_name + "%' and [dbo].[tbl_products].[name] like N'%" + product_name + "%' ";
            if (price_ASC == 1){
                price_DESC = 0;
                query += "ORDER BY [dbo].[tbl_products].[origin_price] ASC";
            }else if (price_DESC == 1){
                price_ASC = 0;
                query += "ORDER BY [dbo].[tbl_products].[origin_price] DESC";
            }
            connection = connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()){
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
}
