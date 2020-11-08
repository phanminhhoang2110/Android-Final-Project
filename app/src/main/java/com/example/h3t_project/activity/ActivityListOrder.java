package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.h3t_project.R;

public class ActivityListOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        getSupportActionBar().setTitle("Danh sách đơn đặt hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}