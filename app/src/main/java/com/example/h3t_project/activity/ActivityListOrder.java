package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.h3t_project.R;
import com.example.h3t_project.fragment.SellerListOrderFragment;
import com.google.android.material.tabs.TabItem;

public class ActivityListOrder extends AppCompatActivity {
    Toolbar toolbar;
    ScrollView scrollView;
    Button btnWaiting, btnShipping, btnShipped, btnCanceled;
    Bundle bundle;
    SellerListOrderFragment sellerListOrderFragment;
    FrameLayout frameLayout;
    androidx.fragment.app.FragmentManager manager;
    FragmentTransaction transaction;
    int status_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        toolbar = findViewById(R.id.actionbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.frame_order_list_seller);
        btnWaiting = findViewById(R.id.btnWaiting);
        btnShipping = findViewById(R.id.btnShipping);
        btnShipped = findViewById(R.id.btnShipped);
        btnCanceled = findViewById(R.id.btnCanceled);
//        btnWaiting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        int statusId = 0;
//        Intent intent = getIntent();
//        if (intent.getIntExtra("statusId", 0) != 0) {
//            statusId = intent.getIntExtra("statusId", 0);
//        }
//
//        bundle = new Bundle();
//        if (statusId != 0) {
//            bundle.putInt("statusId", statusId);
//        }
    }

    public void waitingOrder(View view){
        Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_LONG).show();
//        status_id = 3;
//        transaction = manager.beginTransaction();
//        sellerListOrderFragment = new SellerListOrderFragment(status_id);
//        transaction.replace(R.id.frame_order_list_seller, sellerListOrderFragment);
//        transaction.commit();

    }

    public void shippingOrder(View view){
        status_id = 4;
        transaction = manager.beginTransaction();
        sellerListOrderFragment = new SellerListOrderFragment(status_id);
        transaction.replace(R.id.frame_order_list_seller, sellerListOrderFragment);
        transaction.commit();
    }
    public void shippedOrder(View view){
        status_id = 5;
        transaction = manager.beginTransaction();
        sellerListOrderFragment = new SellerListOrderFragment(status_id);
        transaction.replace(R.id.frame_order_list_seller, sellerListOrderFragment);
        transaction.commit();

    }
    public void canceledOrder(View view){
        status_id = 6;
        transaction = manager.beginTransaction();
        sellerListOrderFragment = new SellerListOrderFragment(status_id);
        transaction.replace(R.id.frame_order_list_seller, sellerListOrderFragment);
        transaction.commit();
    }
}