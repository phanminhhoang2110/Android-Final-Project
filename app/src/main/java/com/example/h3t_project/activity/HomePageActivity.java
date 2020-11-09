package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.CategoryMenuAdapter;
import com.example.h3t_project.model.CategoryItem;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_page);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setupCategory();
  }

  private void setupCategory() {
    RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
    String[] nameCategory = {"bag", "book", "boot", "car", "comestic", "food_hamburder", "house_pan", "laptop", "mobile_phone"};
    ArrayList<CategoryItem> categoryItems = new ArrayList<>();
    for (int i = 0; i < nameCategory.length; i++) {
      categoryItems.add(new CategoryItem(i, nameCategory[i], 0));
    }
    CategoryMenuAdapter categoryMenuAdapter = new CategoryMenuAdapter(categoryItems);
    categoryRecyclerView.setAdapter(categoryMenuAdapter);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
    categoryRecyclerView.setLayoutManager(gridLayoutManager);
//    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//    categoryRecyclerView.setLayoutManager(layoutManager);
  }

  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home_page, menu);
    return true;
  }
}
