package com.example.h3t_project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.h3t_project.fragment.LoginFragment;
import com.example.h3t_project.fragment.RegisterFragment;

public class LoginViewPagerAdapter extends FragmentStateAdapter {

  public LoginViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
    super(fragmentActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    switch (position) {
      case 0:
        return new LoginFragment();
      default:
        return new RegisterFragment();
    }
  }

  @Override
  public int getItemCount() {
    return 2;
  }
}
