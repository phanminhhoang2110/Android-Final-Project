package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.h3t_project.R;

public class SellerHomeActivity extends AppCompatActivity {

    Button productButton, orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

        productButton = (Button) findViewById(R.id.btnProduct);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerHomeActivity.this, SellerProductActivity.class));
            }
        });

        orderButton = (Button) findViewById(R.id.btnSellerOrder);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerHomeActivity.this, ActivityListOrder.class));
            }
        });
    }
}