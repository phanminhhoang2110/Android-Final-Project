package com.example.h3t_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.h3t_project.DAO.SellerProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuDetailProductAdapter;
import com.example.h3t_project.adapter.SlideViewProductAdapter;
import com.example.h3t_project.common.ResourceFunction;
import com.example.h3t_project.fragment.DescriptionProductFragment;
import com.example.h3t_project.fragment.ProductPriceFragment;
import com.example.h3t_project.model.DetailProductItem;
import com.example.h3t_project.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailSellerProductActivity extends AppCompatActivity {

  ViewPager viewPager;
  SlideViewProductAdapter adapter;
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_seller_product);

    Toolbar toolbar = findViewById(R.id.toolbarSellerViewDetailProduct);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Chi tiết sản phẩm");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    final int productId = intent.getIntExtra("productId", 0);

    setUpImage(productId);
    setupDetailProduct(productId);
    setupDescriptionProduct(productId);
    setupPriceProduct(productId);

    button = (Button) findViewById(R.id.btnGoToEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SellerEditProductActivity.class);
                intent.putExtra("productId", productId);
                startActivity(intent);
            }
        });

  }

  public void setIntentBack() {
    Intent intentBack = new Intent(this, SellerProductActivity.class);
  }

  public void setUpImage(int product_id) {
    SellerProductDAO sellerProductDAO = new SellerProductDAO();
    List<Product> products = sellerProductDAO.getProductById(product_id);
    for (int i = 0; i < products.size(); i++) {
      products.get(i).setImage_id(ResourceFunction.getResId(products.get(i).getLink_image(), R.drawable.class));
    }
    viewPager = findViewById(R.id.viewPage);
    adapter = new SlideViewProductAdapter(this, products);
    viewPager.setAdapter(adapter);
  }

  public void setupPriceProduct(int product_id) {
    SellerProductDAO sellerProductDAO = new SellerProductDAO();
    List<Product> products = sellerProductDAO.getProductById(product_id);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    Bundle bundle = new Bundle();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    bundle.putString("productName", products.get(0).getName());
    bundle.putString("sellPrice", decimalFormat.format(products.get(0).getSell_price()) + " đ");
    bundle.putString("originPrice", decimalFormat.format(products.get(0).getOrigin_price()) + " đ");
    int discount = (products.get(0).getOrigin_price() - products.get(0).getSell_price()) * 100 / products.get(0).getOrigin_price();
    bundle.putInt("discount", discount);
    ProductPriceFragment fragment = new ProductPriceFragment();
    fragment.setArguments(bundle);
    transaction.replace(R.id.framePrice, fragment);
    transaction.commit();
  }

  public void setupDetailProduct(int product_id) {
    RecyclerView recyclerView = findViewById(R.id.detailProductRecyclerView);
    SellerProductDAO sellerProductDAO = new SellerProductDAO();
    List<Product> products = sellerProductDAO.getProductById(product_id);
    List<DetailProductItem> detailProductItems = new ArrayList<>();
    detailProductItems.add(new DetailProductItem("Hạng mục", products.get(0).getCategory_name()));
    detailProductItems.add(new DetailProductItem("Thương hiệu", products.get(0).getBrand()));
    detailProductItems.add(new DetailProductItem("Bảo Hành", products.get(0).getGuarantee()));
    detailProductItems.add(new DetailProductItem("Số lượng sản phẩm", products.get(0).getQuantity() + " sp"));
    detailProductItems.add(new DetailProductItem("Màu sắc", products.get(0).getColor()));

    if (products.get(0).getHeight() <= 0) {
      detailProductItems.add(new DetailProductItem("Chiều cao", "cm"));
    } else {
      detailProductItems.add(new DetailProductItem("Chiều cao", products.get(0).getHeight() + "cm"));
    }

    detailProductItems.add(new DetailProductItem("Chất liệu", products.get(0).getMaterial()));

    MenuDetailProductAdapter adapter = new MenuDetailProductAdapter(detailProductItems);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

  public void setupDescriptionProduct(int product_id) {
    SellerProductDAO sellerProductDAO = new SellerProductDAO();
    List<Product> products = sellerProductDAO.getProductById(product_id);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    Bundle bundle = new Bundle();
    bundle.putString("description", products.get(0).getDescription());
    DescriptionProductFragment fragment = new DescriptionProductFragment();
    fragment.setArguments(bundle);
    transaction.replace(R.id.frameDescription, fragment);
    transaction.commit();

  }

}
