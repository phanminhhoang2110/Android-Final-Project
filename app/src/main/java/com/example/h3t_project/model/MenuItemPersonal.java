package com.example.h3t_project.model;

public class MenuItemPersonal {
  private int itemName;
  private int iconMenu;

  public MenuItemPersonal(int itemName, int iconMenu) {
    this.itemName = itemName;
    this.iconMenu = iconMenu;
  }

  public int getItemName() {
    return itemName;
  }

  public void setItemName(int itemName) {
    this.itemName = itemName;
  }

  public int getIconMenu() {
    return iconMenu;
  }

  public void setIconMenu(int iconMenu) {
    this.iconMenu = iconMenu;
  }
}
