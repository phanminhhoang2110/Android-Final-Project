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
import android.widget.FrameLayout;


import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.fragment.CustomerListOrderFragment;

public class CustomerListOrderActivity extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager manager;
    FragmentTransaction transaction;
    CustomerListOrderFragment customerListOrderFragment;
    Button btnCustomerAllOrder;
    Button btnCustomerWaiting;
    Button btnCustomerShipping;
    Button btnCustomerReceived;
    Button btnCustomerCancel;
    int status_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_order);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(VietnameseWord.CUSTOMER_LIST_ORDER_ACTIVITY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.framelayout_seller_list_order);

        replaceFragment(status_id);

        btnCustomerAllOrder = findViewById(R.id.btn_customer_all_order);
        btnCustomerAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = -1;
                replaceFragment(status_id);
            }
        });

        btnCustomerWaiting = findViewById(R.id.btn_customer_waiting);
        btnCustomerWaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 3;
                replaceFragment(status_id);
            }
        });

        btnCustomerShipping = findViewById(R.id.btn_customer_shipping);
        btnCustomerShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 4;
                replaceFragment(status_id);
            }
        });

        btnCustomerReceived = findViewById(R.id.btn_customer_done);
        btnCustomerReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 5;
                replaceFragment(status_id);
            }
        });

        btnCustomerCancel = findViewById(R.id.btn_customer_cancel);
        btnCustomerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 6;
                replaceFragment(status_id);
            }
        });

    }

    public void replaceFragment(int status_id){
        transaction = manager.beginTransaction();
        customerListOrderFragment = new CustomerListOrderFragment(status_id);
        transaction.replace(R.id.framelayout_customer_list_order, customerListOrderFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
