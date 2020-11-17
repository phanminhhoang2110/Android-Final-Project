package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.h3t_project.R;

public class ActivityListOrder extends AppCompatActivity {
    Toolbar toolbar;
    Button btnWaiting, btnShipping, btnShipped, btnCanceled, btnAll;
    ListOrderSellerFragment sellerListOrderFragment;
    FragmentManager manager;
    FragmentTransaction transaction;
    int status_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        toolbar = findViewById(R.id.actionbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();

        btnWaiting = findViewById(R.id.btn_seller_waiting);
        btnShipping = findViewById(R.id.btn_seller_shipping);
        btnShipped = findViewById(R.id.btn_seller_done);
        btnCanceled = findViewById(R.id.btn_seller_cancel);
        btnAll = findViewById(R.id.btn_seller_all_order);

        replaceFragment(status_id);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = -1;
                replaceFragment(status_id);
            }
        });

        btnWaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 3;
                replaceFragment(status_id);
            }
        });

        btnShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 4;
                replaceFragment(status_id);
            }
        });

        btnShipped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 5;
                replaceFragment(status_id);
            }
        });

        btnCanceled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 6;
                replaceFragment(status_id);
            }
        });

    }

    public void replaceFragment(int status_id){
        transaction = manager.beginTransaction();
        sellerListOrderFragment = new ListOrderSellerFragment(status_id);
        transaction.replace(R.id.framelayout_seller_list_order, sellerListOrderFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(this, SellerHomeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}