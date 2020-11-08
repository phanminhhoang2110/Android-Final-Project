package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.h3t_project.R;

public class ActivityDetailOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        getSupportActionBar().setTitle("Chi tiết đơn hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}