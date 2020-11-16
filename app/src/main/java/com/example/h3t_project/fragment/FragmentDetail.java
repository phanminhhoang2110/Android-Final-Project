package com.example.h3t_project.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.h3t_project.R;

public class FragmentDetail extends Fragment {
    EditText textBrand, textQuantity, textGuarantee, textColor, textHeight, textMaterial, textDes;

    public FragmentDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        textBrand = view.findViewById(R.id.etxtAddBrand);
        textQuantity = view.findViewById(R.id.etxtAddQuantity);
        textGuarantee = view.findViewById(R.id.etxtAddGuarantee);
        textColor = view.findViewById(R.id.etxtAddColor);
        textHeight = view.findViewById(R.id.etxtAddHeight);
        textMaterial = view.findViewById(R.id.etxtAddMaterial);
        textDes = view.findViewById(R.id.etxtAddDes);

        return view;
    }
}