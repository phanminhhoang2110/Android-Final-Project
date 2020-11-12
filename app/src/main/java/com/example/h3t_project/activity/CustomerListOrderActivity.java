package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;

public class CustomerListOrderActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_order);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(VietnameseWord.CUSTOMER_LIST_ORDER_ACTIVITY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
