package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.h3t_project.R;
import com.example.h3t_project.constants.VietnameseWord;
import com.example.h3t_project.fragment.CustomerListOrderFragment;
import com.example.h3t_project.fragment.CustomerViewProductASCFragment;

public class CustomerListOrderActivity extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager manager;
    FragmentTransaction transaction;
    CustomerListOrderFragment customerListOrderFragment;
    Button btnCustomerWaiting;
    Button btnCustomerShipping;
    Button btnCustomerReceived;
    Button btnCustomerCancel;
    int status_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_order);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(VietnameseWord.CUSTOMER_LIST_ORDER_ACTIVITY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.framelayout_customer_list_order);

        btnCustomerWaiting = findViewById(R.id.btn_customer_waiting);
        btnCustomerWaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 3;
                transaction = manager.beginTransaction();
                customerListOrderFragment = new CustomerListOrderFragment(status_id);
                transaction.replace(R.id.framelayout_customer_list_order, customerListOrderFragment);
                transaction.commit();
            }
        });

        btnCustomerShipping = findViewById(R.id.btn_customer_shipping);
        btnCustomerShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 4;
                transaction = manager.beginTransaction();
                customerListOrderFragment = new CustomerListOrderFragment(status_id);
                transaction.replace(R.id.framelayout_customer_list_order, customerListOrderFragment);
                transaction.commit();
            }
        });

        btnCustomerReceived = findViewById(R.id.btn_customer_done);
        btnCustomerReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 4;
                transaction = manager.beginTransaction();
                customerListOrderFragment = new CustomerListOrderFragment(status_id);
                transaction.replace(R.id.framelayout_customer_list_order, customerListOrderFragment);
                transaction.commit();
            }
        });

        btnCustomerCancel = findViewById(R.id.btn_customer_cancel);
        btnCustomerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status_id = 4;
                transaction = manager.beginTransaction();
                customerListOrderFragment = new CustomerListOrderFragment(status_id);
                transaction.replace(R.id.framelayout_customer_list_order, customerListOrderFragment);
                transaction.commit();
            }
        });

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
