package com.example.h3t_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.SellerProductAdapter;
import com.example.h3t_project.model.SellerProduct;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SellerProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<SellerProduct> sellerProducts = new ArrayList<>();
        for (int i = 1; i<11; i++){
            sellerProducts.add(new SellerProduct(i,"Chuột Logitech " +  i,
                    getResId("rectangle_52", R.drawable.class), 300000, i));
        }

        SellerProductAdapter adapter = new SellerProductAdapter(sellerProducts);
        recyclerView.setAdapter(adapter);

        //Nhieu loai Layout
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}