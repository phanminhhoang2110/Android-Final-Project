package com.example.h3t_project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.h3t_project.R;

public class FragmentShort extends Fragment {
    EditText textName, textOriginPrice, textPrice;
    RadioButton rdFood, rdPhone, rdShoes, rdBook, rdFerniture, rdBag, rdBeauty, rdLaptop, rdClothes;
    static int category;

    public FragmentShort() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_short, container, false);

        textName = view.findViewById(R.id.etxtAddName);
        textOriginPrice = view.findViewById(R.id.etxtAddOriginPrice);
        textPrice = view.findViewById(R.id.etxtAddPrice);

        rdFood = view.findViewById(R.id.rdFood);
        rdPhone = view.findViewById(R.id.rdPhone);
        rdShoes = view.findViewById(R.id.rdShoes);
        rdBook = view.findViewById(R.id.rdBook);
        rdFerniture = view.findViewById(R.id.rdFerniture);
        rdBag = view.findViewById(R.id.rdBag);
        rdBeauty = view.findViewById(R.id.rdBeuty);
        rdLaptop = view.findViewById(R.id.rdLaptop);
        rdClothes = view.findViewById(R.id.rdClothes);

        rdFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 1;
            }
        });
        rdPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 2;
            }
        });
        rdShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 3;
            }
        });
        rdBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 4;
            }
        });
        rdFerniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 5;
            }
        });
        rdBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 6;
            }
        });
        rdBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 7;
            }
        });
        rdLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 8;
            }
        });
        rdClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = 9;
            }
        });
        return view;
    }
}