package com.example.h3t_project.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.h3t_project.DAO.SellerProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.SellerProductAdapter;
import com.example.h3t_project.model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SellerProductActivity extends AppCompatActivity {

    RecyclerView listSellerProduct;
    SellerProductDAO sellerProductDAO = new SellerProductDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product);

        Toolbar toolbar = findViewById(R.id.toolbarSellerViewProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listSellerProduct = findViewById(R.id.recyclerView);
        List<Product> products = sellerProductDAO.getProducts();
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < products.size(); i+=3) {
            Product p = new Product();
            p.setId(products.get(i).getId());
            p.setName(products.get(i).getName());
            p.setImage_id(getResId(products.get(i).getLink_image(), R.drawable.class));
            p.setOrigin_price(products.get(i).getOrigin_price());
            p.setSell_price(products.get(i).getSell_price());
            p.setQuantity(products.get(i).getQuantity());
            list.add(p);
        }

        //Nhieu loai Layout
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        listSellerProduct.setLayoutManager(layoutManager);
        SellerProductAdapter adapter = new SellerProductAdapter(list);
        listSellerProduct.setAdapter(adapter);
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