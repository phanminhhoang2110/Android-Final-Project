package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.fragment.CustomerViewProductASCFragment;
import com.example.h3t_project.fragment.CustomerViewProductDSCFragment;
import com.example.h3t_project.fragment.CustomerViewProductFragment;
import com.example.h3t_project.model.CategoryItem;

public class ActivityCustomerViewProduct extends AppCompatActivity {
  Toolbar toolbar;
  Button btnAsc;
  Button btnDesc;
  FrameLayout frameLayout;
  FragmentManager manager;
  FragmentTransaction transaction;
  CustomerViewProductASCFragment ascFragment;
  CustomerViewProductDSCFragment descFragment;
  CustomerViewProductFragment customerViewProductFragment;
  Bundle bundle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_view_product);

    Intent intent = getIntent();
    int categoryId = 0;

    if (intent.getIntExtra("categoryId", 0) != 0){
      categoryId = intent.getIntExtra("categoryId", 0);
    }
    if (intent.getIntExtra("categoryBack", 0) != 0){
      categoryId = intent.getIntExtra("categoryBack", 0);
    }

    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    CategoryItem categoryItem = customerViewProductDAO.getCategorybById(categoryId);

    toolbar = findViewById(R.id.actionbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(categoryItem.getName());

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    frameLayout = findViewById(R.id.framelayout_customer_view_product);
    btnAsc = findViewById(R.id.btnWaiting);
    btnDesc = findViewById(R.id.desc_price_customer_view_product);
    manager = getSupportFragmentManager();

    bundle = new Bundle();

    bundle.putInt("categoryId", categoryId);
    transaction = manager.beginTransaction();
    customerViewProductFragment = new CustomerViewProductFragment();
    customerViewProductFragment.setArguments(bundle);
    transaction.replace(R.id.framelayout_customer_view_product, customerViewProductFragment);
    transaction.commit();

    btnAsc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        transaction = manager.beginTransaction();
        ascFragment = new CustomerViewProductASCFragment();
        ascFragment.setArguments(bundle);
        transaction.replace(R.id.framelayout_customer_view_product, ascFragment);
        transaction.commit();
      }
    });

    btnDesc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        transaction = manager.beginTransaction();
        descFragment = new CustomerViewProductDSCFragment();
        descFragment.setArguments(bundle);
        transaction.replace(R.id.framelayout_customer_view_product, descFragment);
        transaction.commit();
      }
    });
  }

  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_customer_view_product, menu);


    return true;
  }


}
