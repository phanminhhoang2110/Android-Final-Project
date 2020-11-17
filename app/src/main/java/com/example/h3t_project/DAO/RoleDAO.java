package com.example.h3t_project.DAO;

import com.example.h3t_project.DatabaseM.DatabaseManager;
import com.example.h3t_project.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO extends DatabaseManager {
  public List<Role> getRoles() {
    List<Role> roles = new ArrayList<>();
    try {
      String sql = "select id, role from tbl_roles ";
      connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Role role = new Role();
        role.setRoleId(rs.getInt("id"));
        role.setRoleName(rs.getString("role"));
        roles.add(role);
      }
    } catch (Exception ex) {
      Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return roles;
  }
}
