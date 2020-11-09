//package com.example.h3t_project.DAO;
//
//import com.example.h3t_project.DatabaseM.DatabaseManager;
//import com.example.h3t_project.model.User;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class UserDAO extends DatabaseManager {
//  PreparedStatement ps = null;
//  ResultSet rs = null;
//
//  public User checkLogin(String username, String password) {
//    try {
//      String query = "SELECT [id]" +
//        "      ,[username]\n" +
//        "      ,[password]\n" +
//        "  FROM [H3TSTORE].[dbo].[tbl_users] where username = ? and password = ?";
//      connection = connect();
//      ps = connection.prepareStatement(query);
//      ps.setString(1, username);
//      ps.setString(2, password);
//      rs = ps.executeQuery();
//      while (rs.next()) {
//        User user = new User();
//        user.setId(rs.getInt(""));
//        user.setUsername(rs.getString(""));
//        user.setPassword(rs.getString(""));
//        return user;
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//}
