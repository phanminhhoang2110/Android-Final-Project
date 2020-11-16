package com.example.h3t_project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.h3t_project.fragment.LoginFragment;
import com.example.h3t_project.fragment.RegisterFragment;

import java.util.List;

public class SellerListOrderAdapter extends FragmentStateAdapter {
    public SellerListOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LoginFragment();
            case 1:
                return new LoginFragment();
            case 2:
                return new LoginFragment();
            default:
                return new RegisterFragment();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position, @NonNull List<Object> payloads) {

        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
