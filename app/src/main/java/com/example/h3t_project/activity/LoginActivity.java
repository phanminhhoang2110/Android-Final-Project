package com.example.h3t_project.activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.LoginViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {
  ViewPager2 viewPager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    viewPager = findViewById(R.id.viewPager);
    viewPager.setAdapter(new LoginViewPagerAdapter(this));

    TabLayout tabLayout = findViewById(R.id.tabLayout);
    TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
      @Override
      public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        switch (position) {
          case 0:
            tab.setText("Đăng nhập");
            break;
          default:
            tab.setText("Đăng ký");
            break;
        }
      }
    });
    tabLayoutMediator.attach();


  }


}
