package com.example.h3t_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.h3t_project.DAO.CategoryDAO;
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
  ImageView searchBtn;
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
    searchBtn = findViewById(R.id.searchBtnHomePage);
  }

  private void setupCategory() {
    RecyclerView categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
    CategoryDAO categoryDAO = new CategoryDAO();
    ArrayList<CategoryItem> categoryItems = (ArrayList<CategoryItem>) categoryDAO.getCategories();
    CategoryMenuAdapter categoryMenuAdapter = new CategoryMenuAdapter(categoryItems,this);
    categoryRecyclerView.setAdapter(categoryMenuAdapter);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
    categoryRecyclerView.setLayoutManager(gridLayoutManager);
  }

  public void searchButtonHomePageClick(View view){
    view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click_animation));
    EditText searchHomePage = findViewById(R.id.searchHomePage);
    String searchKey = searchHomePage.getText().toString();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home_page, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.action_personal:
        Intent intent = new Intent(this, PersonalActivity.class);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
