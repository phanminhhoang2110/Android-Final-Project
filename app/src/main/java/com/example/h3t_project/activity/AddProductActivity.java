package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.h3t_project.R;
import com.example.h3t_project.fragment.FragmentDetail;
import com.example.h3t_project.fragment.FragmentImage;
import com.example.h3t_project.fragment.FragmentShort;
import com.example.h3t_project.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AddProductActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = findViewById(R.id.toolbarSellerAddProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewPageAddProduct);

        addTabs(viewPager);

        //Chuyen sang TabLayout
        ((TabLayout)findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }

    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new FragmentShort(), "1.Tổng quan");
        adapter.add(new FragmentDetail(), "2.Chi tiết");
        adapter.add(new FragmentImage(), "3.Hình ảnh");

        viewPager.setAdapter(adapter);
    }

}