package com.example.h3t_project.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.h3t_project.DAO.SellerProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.model.Product;

public class SellerEditProductActivity extends AppCompatActivity {

EditText textName, textOriginPrice, textPrice, textBrand, textQuantity, textGuarantee,
            textColor, textHeight, textMaterial, textDes;
    TextView txtNote;
    RadioButton rdFood, rdPhone, rdShoes, rdBook, rdFerniture, rdBag, rdBeauty, rdLaptop, rdClothes;
    Button btnAdd;
    static int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_edit_product);

        Toolbar toolbar = findViewById(R.id.toolbarSellerEditProduct);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chỉnh sửa sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textName = findViewById(R.id.etxtEditName);
        textOriginPrice = findViewById(R.id.etxtEditOriginPrice);
        textPrice = findViewById(R.id.etxtEditPrice);

        rdFood = findViewById(R.id.rdFood);
        rdPhone = findViewById(R.id.rdPhone);
        rdShoes = findViewById(R.id.rdShoes);
        rdBook = findViewById(R.id.rdBook);
        rdFerniture = findViewById(R.id.rdFerniture);
        rdBag = findViewById(R.id.rdBag);
        rdBeauty = findViewById(R.id.rdBeuty);
        rdLaptop = findViewById(R.id.rdLaptop);
        rdClothes = findViewById(R.id.rdClothes);

        textBrand = findViewById(R.id.etxtEditBrand);
        textQuantity = findViewById(R.id.etxtEditQuantity);
        textGuarantee = findViewById(R.id.etxtEditGuarantee);
        textColor = findViewById(R.id.etxtEditColor);
        textHeight = findViewById(R.id.etxtEditHeight);
        textMaterial = findViewById(R.id.etxtEditMaterial);
        textDes = findViewById(R.id.etxtEditDes);
        txtNote = findViewById(R.id.txtNote);

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

        Intent intent = getIntent();
        final int productId = intent.getIntExtra("productId", 0);

        getProductById(productId);
    }

    public void getProductById(int productId){
        SellerProductDAO sellerProductDAO = new SellerProductDAO();
        Product product = sellerProductDAO.getSellerProductById(productId);
        getDetail(product);
    }

    public void getDetail(Product product){
        textName.setText(product.getName());
        textOriginPrice.setText(((Integer)product.getOrigin_price()).toString());
        textPrice.setText(((Integer)product.getSell_price()).toString());
        textBrand.setText(product.getBrand());
        textQuantity.setText(((Integer)product.getQuantity()).toString());
        textGuarantee.setText(product.getGuarantee());
        textColor.setText(product.getColor());
        textHeight.setText(((Integer)product.getHeight()).toString());
        textMaterial.setText(product.getMaterial());
        textDes.setText(product.getDescription());
    }

    public void changeProduct(View view){
        Intent intent = getIntent();
        final int productId = intent.getIntExtra("productId", 0);
        SellerProductDAO sellerProductDAO = new SellerProductDAO();
        int editProduct = sellerProductDAO.update(productId, textName.getText().toString(), category, Integer.parseInt(textOriginPrice.getText().toString()), Integer.parseInt(textPrice.getText().toString()), textBrand.getText().toString(),
                Integer.parseInt(textQuantity.getText().toString()), textGuarantee.getText().toString(), textColor.getText().toString(), Integer.parseInt(textHeight.getText().toString()), textMaterial.getText().toString(), textDes.getText().toString());
        if(editProduct != 1){
            txtNote.setText("Đổi thông tin thành công");
            txtNote.setTextColor(Color.parseColor("#CC189EFF"));
            sellerMoveToListProduct();
        }
        else {
            txtNote.setText("Đổi thông tin thất bại");
            txtNote.setTextColor(Color.parseColor("#F52929"));
        }

    }
    private void sellerMoveToListProduct() {

        Intent intent = new Intent(getApplicationContext(), SellerProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
