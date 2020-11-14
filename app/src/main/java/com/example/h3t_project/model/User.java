package com.example.h3t_project.model;

import java.sql.Date;

public class User {
  private int id;
  private String username;
  private String password;
  private String fullname;
  private Date date;
  private String phone;
  private String email;
  private int gender;
  private Role roleId;

  public User() {
  }

  public User(int id, String name, String password) {
    this.id = id;
    this.username = name;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return username;
  }

  public void setUsername(String name) {
    this.username = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public Role getRoleId() {
    return roleId;
  }

  public void setRoleId(Role roleId) {
    this.roleId = roleId;
  }
}
