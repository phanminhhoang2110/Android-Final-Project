package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.CategoryMenuAdapter;
import com.example.h3t_project.adapter.HomePageSlideAdapter;
import com.example.h3t_project.adapter.SlideViewProductAdapter;
import com.example.h3t_project.model.CategoryItem;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {


  ViewPager viewPager;
  HomePageSlideAdapter adapter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_page);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("H3T Store");
    viewPager = findViewById(R.id.slideBanner);
    adapter = new HomePageSlideAdapter(this);
    viewPager.setAdapter(adapter);
    setupCategory();
  }

  private void setupCategory() {
    RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
    String[] nameCategory = {"book", "boot", "shirt", "comestic", "food", "house", "laptop", "mobile"};
    ArrayList<CategoryItem> categoryItems = new ArrayList<>();
    for (int i = 0; i < nameCategory.length; i++) {
      String image = "ic_iconfinder_" + nameCategory[i];
      categoryItems.add(new CategoryItem(i, nameCategory[i],  getResId(image,R.drawable.class)));
    }
    CategoryMenuAdapter categoryMenuAdapter = new CategoryMenuAdapter(categoryItems);
    categoryRecyclerView.setAdapter(categoryMenuAdapter);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
    categoryRecyclerView.setLayoutManager(gridLayoutManager);
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
