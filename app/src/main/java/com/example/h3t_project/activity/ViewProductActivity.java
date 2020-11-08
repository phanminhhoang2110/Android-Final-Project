package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuDetailProductAdapter;
import com.example.h3t_project.adapter.SlideViewProductAdapter;
import com.example.h3t_project.model.DetailProductItem;

import java.util.ArrayList;


public class ViewProductActivity extends AppCompatActivity {

  ViewPager viewPager;
  SlideViewProductAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_product);
    Toolbar toolbar = findViewById(R.id.toolbarViewProduct);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    viewPager = findViewById(R.id.viewPage);
    adapter = new SlideViewProductAdapter(this);
    viewPager.setAdapter(adapter);
    setupDetailProduct();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar_view_product ,menu);
    return true;
  }

  public void setupDetailProduct(){
    RecyclerView recyclerView = findViewById(R.id.detailProductRecyclerView);
    ArrayList<DetailProductItem> detailProductItems = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      detailProductItems.add(new DetailProductItem("name " + i, "content "+ i));
    }
    MenuDetailProductAdapter adapter = new MenuDetailProductAdapter(detailProductItems);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

}
