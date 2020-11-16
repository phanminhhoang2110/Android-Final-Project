package com.example.h3t_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ScrollView;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.LoginViewPagerAdapter;
import com.example.h3t_project.adapter.SellerListOrderAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityListOrder extends AppCompatActivity {
    Toolbar toolbar;
    ScrollView scrollView;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        toolbar = findViewById(R.id.actionbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpageOrder);
        viewPager.setAdapter(new SellerListOrderAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabOrder);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Chờ lấy hàng");
                        break;
                    case 1:
                        tab.setText("Đang giao hàng");
                        break;
                    case 2:
                        tab.setText("Đã giao hàng");
                        break;
                    default:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

    }
}