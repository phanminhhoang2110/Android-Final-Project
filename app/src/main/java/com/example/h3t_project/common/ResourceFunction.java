package com.example.h3t_project.common;

import java.lang.reflect.Field;

public class ResourceFunction {
  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
