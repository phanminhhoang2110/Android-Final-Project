package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.CategoryItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DatabaseManager {
  PreparedStatement preparedStatement;
  ResultSet resultSet = null;

  public List<CategoryItem> getCategories() {
    List<CategoryItem> categoryItems = null;
    String query = "SELECT [id]\n" +
      "      ,[name]\n" +
      "      ,[icon]\n" +
      "  FROM [H3TSTORE].[dbo].[tbl_categories]";
    try {
      connection = connect();
      preparedStatement = connection.prepareStatement(query);
      categoryItems = new ArrayList<>();
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setId(resultSet.getInt("id"));
        categoryItem.setName(resultSet.getString("name"));
        categoryItem.setImage(resultSet.getString("icon"));
        categoryItems.add(categoryItem);
      }
      return categoryItems;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categoryItems;
  }
}
