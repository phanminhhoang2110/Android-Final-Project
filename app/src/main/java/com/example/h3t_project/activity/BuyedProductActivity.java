package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.BuyedAdapter;
import com.example.h3t_project.model.Product;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;
import java.util.List;

public class BuyedProductActivity extends AppCompatActivity {

  Toolbar toolbar;
  RecyclerView recyclerView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_buyed_product);

    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Sản phẩm đã mua");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    recyclerView = findViewById(R.id.buyedProductRecyclerView);
    setupBuyedProduct();
  }

  private void setupBuyedProduct(){
    SessionManagement sessionManagement = new SessionManagement(this);
    int customerId = sessionManagement.getSessionUserId();
    CustomerViewProductDAO viewProductDAO = new CustomerViewProductDAO();
    List<Product> allBuyedProduct = viewProductDAO.getAllBuyedProduct(customerId);
    BuyedAdapter buyedAdapter = new BuyedAdapter((ArrayList<Product>) allBuyedProduct);
    recyclerView.setAdapter(buyedAdapter);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(manager);
  }
}
