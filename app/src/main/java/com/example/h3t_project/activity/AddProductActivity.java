package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.FragmentDetail;
import com.example.h3t_project.adapter.FragmentImage;
import com.example.h3t_project.adapter.FragmentShort;
import com.example.h3t_project.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

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