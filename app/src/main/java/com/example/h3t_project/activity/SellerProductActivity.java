package com.example.h3t_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.h3t_project.R;
import com.example.h3t_project.adapter.SellerProductAdapter;
import com.example.h3t_project.model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SellerProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product);

        Toolbar toolbar = findViewById(R.id.toolbarSellerViewProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 1; i<11; i++){
            products.add(new Product(i,"Chuột Logitech " +  i,
                    getResId("rectangle_52", R.drawable.class), 300000, i));
        }

        SellerProductAdapter adapter = new SellerProductAdapter(products);
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