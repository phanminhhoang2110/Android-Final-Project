package com.example.h3t_project.model;

public class CategoryItem {

  private int id;
  private String name;
  private int image;

  public CategoryItem(int id, String name, int image) {
    this.id = id;
    this.name = name;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getImage() {
    return image;
  }

  public void setImage(int image) {
    this.image = image;
  }
}
