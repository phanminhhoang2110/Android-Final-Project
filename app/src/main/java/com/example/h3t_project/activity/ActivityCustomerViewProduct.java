package com.example.h3t_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.fragment.CustomerViewProductASCFragment;
import com.example.h3t_project.fragment.CustomerViewProductDSCFragment;
import com.example.h3t_project.fragment.CustomerViewProductFragment;

public class ActivityCustomerViewProduct extends AppCompatActivity {
  Toolbar toolbar;
  Button btnAsc;
  Button btnDesc;
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
    String searchText = null;

    if (intent.getIntExtra("categoryId", 0) != 0) {
      categoryId = intent.getIntExtra("categoryId", 0);
    }
    if (intent.getIntExtra("categoryBack", 0) != 0) {
      categoryId = intent.getIntExtra("categoryBack", 0);
    }
    if (intent.getStringExtra("searchText") != null) {
      searchText = intent.getStringExtra("searchText");
    }

    toolbar = findViewById(R.id.actionbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle(VietnameseWord.VIEW_LIST_PRODUCT);

    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    btnAsc = findViewById(R.id.asc_price_customer_view_product);
    btnDesc = findViewById(R.id.desc_price_customer_view_product);
    manager = getSupportFragmentManager();

    bundle = new Bundle();
    if (categoryId != 0) {
      bundle.putInt("categoryId", categoryId);
    }
    if (searchText != null) {
      bundle.putString("searchText", searchText);
    }
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
    final Menu m = menu;
    final MenuItem item = menu.findItem(R.id.action_cart);
    item.getActionView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        m.performIdentifierAction(item.getItemId(), 1);
        Intent intentForCart = new Intent(ActivityCustomerViewProduct.this, ActivityMyCart.class);
        startActivity(intentForCart);
      }
    });


    return true;
  }


}
