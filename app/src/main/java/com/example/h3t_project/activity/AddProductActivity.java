package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.h3t_project.DAO.SellerProductDAO;
import com.example.h3t_project.R;

public class AddProductActivity extends AppCompatActivity {

    EditText textName, textOriginPrice, textPrice, textBrand, textQuantity, textGuarantee,
            textColor, textHeight, textMaterial, textDes;
    TextView txtNote;
    RadioButton rdFood, rdPhone, rdShoes, rdBook, rdFerniture, rdBag, rdBeauty, rdLaptop, rdClothes;
    Button btnAdd;
    static int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = findViewById(R.id.toolbarSellerAddProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thêm sản phẩm mới");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        textId = findViewById(R.id.etxtAddId);
        textName = findViewById(R.id.etxtAddName);
        textOriginPrice = findViewById(R.id.etxtAddOriginPrice);
        textPrice = findViewById(R.id.etxtAddPrice);

        rdFood = findViewById(R.id.rdFood);
        rdPhone = findViewById(R.id.rdPhone);
        rdShoes = findViewById(R.id.rdShoes);
        rdBook = findViewById(R.id.rdBook);
        rdFerniture = findViewById(R.id.rdFerniture);
        rdBag = findViewById(R.id.rdBag);
        rdBeauty = findViewById(R.id.rdBeuty);
        rdLaptop = findViewById(R.id.rdLaptop);
        rdClothes = findViewById(R.id.rdClothes);

        textBrand = findViewById(R.id.etxtAddBrand);
        textQuantity = findViewById(R.id.etxtAddQuantity);
        textGuarantee = findViewById(R.id.etxtAddGuarantee);
        textColor = findViewById(R.id.etxtAddColor);
        textHeight = findViewById(R.id.etxtAddHeight);
        textMaterial = findViewById(R.id.etxtAddMaterial);
        textDes = findViewById(R.id.etxtAddDes);
        txtNote = findViewById(R.id.txtNote);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerProductDAO sellerProductDAO = new SellerProductDAO();
                //int id = Integer.parseInt(textId.getText().toString());
                String name = textName.getText().toString();
                int oPrice = Integer.parseInt(textOriginPrice.getText().toString());
                int price = Integer.parseInt(textPrice.getText().toString());
                String brand = textBrand.getText().toString();
                int quantity = Integer.parseInt(textQuantity.getText().toString());
                String guarantee = textGuarantee.getText().toString();
                String color = textColor.getText().toString();
                int height = Integer.parseInt(textHeight.getText().toString());
                String material = textMaterial.getText().toString();
                String des = textDes.getText().toString();

                if(!sellerProductDAO.isDuplicate(name)){
                    int insertProduct = sellerProductDAO.insertProduct(name,category,oPrice,price,brand,quantity,guarantee,color,height,material,des);
                    if(insertProduct != 1){
                        sellerMoveToListProduct();
                    }
                    else {
                        txtNote.setText("Vui lòng không để trống bất kì thông tin nào!");
                    }
                }
                else {
                    txtNote.setText("Username đã tồn tại");
                }
            }
        });
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
    }

    private void sellerMoveToListProduct() {

        Intent intent = new Intent(getApplicationContext(), SellerProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}