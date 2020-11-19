package com.example.h3t_project.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.SellerProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.SellerProductAdapter;
import com.example.h3t_project.common.ResourceFunction;
import com.example.h3t_project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SellerProductActivity extends AppCompatActivity {

  RecyclerView listSellerProduct;
  SellerProductDAO sellerProductDAO = new SellerProductDAO();
  Button button, searchBtn;
  EditText inputSearch;
  List<Product> list, products;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seller_product);

    Toolbar toolbar = findViewById(R.id.toolbarSellerViewProduct);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("List các sản phẩm");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    inputSearch = findViewById(R.id.inputSearch);
    listSellerProduct = findViewById(R.id.recyclerView);
    setupProduct("");

    button = findViewById(R.id.btnGoToAdd);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SellerProductActivity.this, AddProductActivity.class));
      }
    });

    searchBtn = findViewById(R.id.searchBtn);
    searchBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String searchText = inputSearch.getText().toString();
        if (!searchText.isEmpty()) {
          setupProduct(searchText);
        }
      }
    });
  }

  private void setupProduct(String name) {
    products = sellerProductDAO.getProducts(name);
    list = new ArrayList<>();
    for (int i = 0; i < products.size(); i += 3) {
      Product p = new Product();
      p.setId(products.get(i).getId());
      p.setName(products.get(i).getName());
      p.setImage_id(ResourceFunction.getResId(products.get(i).getLink_image(), R.drawable.class));
      p.setOrigin_price(products.get(i).getOrigin_price());
      p.setSell_price(products.get(i).getSell_price());
      p.setQuantity(products.get(i).getQuantity());
      list.add(p);
    }

    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
    listSellerProduct.setLayoutManager(layoutManager);
    listSellerProduct.setNestedScrollingEnabled(true);
    SellerProductAdapter adapter = new SellerProductAdapter(list, getApplicationContext());
    listSellerProduct.setAdapter(adapter);
  }
}
