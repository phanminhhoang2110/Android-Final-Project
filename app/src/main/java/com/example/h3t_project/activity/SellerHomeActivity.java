package com.example.h3t_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.h3t_project.R;
import com.example.h3t_project.sessionhelper.SessionManagement;

public class SellerHomeActivity extends AppCompatActivity {

  Button productButton, orderButton, couponButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seller_home);

    productButton = findViewById(R.id.btnProduct);
    productButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SellerHomeActivity.this, SellerProductActivity.class));
      }
    });

    orderButton = findViewById(R.id.btnSellerOrder);
    orderButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SellerHomeActivity.this, ActivityListOrder.class));
      }
    });

    couponButton = findViewById(R.id.btnCoupon);
    couponButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(SellerHomeActivity.this, CouponActivity.class));
      }
    });
  }

  public void logout(View view) {
    SessionManagement sessionManagement = new SessionManagement(SellerHomeActivity.this);
    sessionManagement.removeSession();
    userMoveToHomePage();
  }

  private void userMoveToHomePage() {
    Intent intent = new Intent(SellerHomeActivity.this, HomePageActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }
}
