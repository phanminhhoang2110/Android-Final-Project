package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Role;
import com.example.h3t_project.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DatabaseManager {
  public User login(String username, String password) {
    try {
      String query = "SELECT [id]" +
        "      ,[username]\n" +
        "      ,[password], [role_id]\n" +
        "  FROM [H3TSTORE].[dbo].[tbl_users] where username = ? and password = ?";
      connection = connect();
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, username);
      ps.setString(2, password);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        User user = new User();
        Role role = new Role();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        role.setRoleId(rs.getInt("role_id"));
        user.setRoleId(role);
        return user;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  public boolean isDuplicate(String username) {
    try {
      String sql = "Select username From tbl_users Where username = ? ";
      connection = connect();
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return true;
      }
    } catch (Exception ex) {
      Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  public int insert(String username, String password, String name, String phone, String email, int gender) {
    int result = 0;
    try {
      //insert users table
      String sql = "INSERT INTO tbl_users(username, password, fullname, phone, email, gender, status_id, role_id)" +
              " values(?,?,?,?,?,?,1,1)";
      connection = connect();
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, username);
      ps.setString(2, password);
      ps.setString(3, name);
      ps.setString(4, phone);
      ps.setString(5, email);
      ps.setInt(6, gender);
      ps.executeUpdate();
    } catch (Exception ex) {
      result = 1;
      Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
  }

  public User getUserById(int id){
    User user = new User();
    try {
      String sql = "select fullname, phone, email, dob, username, password  from tbl_users "
              + " where id = ? ";
      connection = connect();
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        user.setFullname(rs.getString("fullname"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setDate(rs.getDate("dob"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
      }
    } catch (Exception ex) {
      Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;

  }

  public int update(String name, String phone, String email, String username, String password) {
    int result = 0;
    try {
      //update tbl_users table
      String sql = "UPDATE tbl_users set fullname = ?, phone=?, email=?,username =?, password = ? ";
      connection = connect();
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, phone);
      ps.setString(3, email);
      ps.setString(4, username);
      ps.setString(5, password);
      ps.executeUpdate();
    } catch (Exception ex) {
      result = 1;
      Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return result;
  }
}
