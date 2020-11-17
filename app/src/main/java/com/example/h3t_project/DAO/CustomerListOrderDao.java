package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerListOrderDao extends DatabaseManager {
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public List<Order> getListOrdersForCustomer(int customerId, int statusId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT distinct tbl_order_product.order_id\n" +
                "                      ,tbl_products.id as product_id\n" +
                "                       ,tbl_products.[name], \n" +
                "\t\t\t\t\t   tbl_products.[sell_price]\n" +
                "                       ,[tbl_order_product].quantity \n" +
                "                     ,tbl_status.id as status_id\n" +
                "                       ,tbl_status.status\n" +
                "                      ,tbl_images.link\n" +
                "\t\t\t\t\t  ,tbl_orders.customer_id\n" +
                "                      FROM (select order_id, max(product_id) product_id from tbl_order_product group by order_id) as tbl_one_order \n" +
                "                      inner join (select product_id, min(image_id) image_id from tbl_product_image group by product_id) as tbl_one_image_product\n" +
                "                     ON tbl_one_order.product_id = tbl_one_image_product.product_id\n" +
                "                      inner join tbl_images\n" +
                "                      ON tbl_one_image_product.image_id = tbl_images.id \n" +
                "                      inner join dbo.tbl_products \n" +
                "                       ON tbl_one_order.product_id = tbl_products.id \n" +
                "                       inner join dbo.tbl_order_product\n" +
                "                      ON tbl_one_order.product_id = tbl_order_product.product_id\n" +
                "                       inner join dbo.tbl_orders\n" +
                "                     ON tbl_order_product.order_id = tbl_orders.id\n" +
                "                      inner join dbo.tbl_status\n" +
                "                      ON status_id = tbl_status.id";
        if (customerId != -1 && statusId != -1){
            query += " WHERE customer_id = ? and status_id = ?";
        }

        try {
            connection = connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, statusId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setProductName(resultSet.getString("name"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setSellPrice(resultSet.getInt("sell_price"));
                order.setStatusId(resultSet.getInt("status_id"));
                order.setImageLink(resultSet.getString("link"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
