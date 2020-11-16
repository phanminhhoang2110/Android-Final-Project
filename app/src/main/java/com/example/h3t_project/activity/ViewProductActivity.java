package com.example.h3t_project.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuDetailProductAdapter;
import com.example.h3t_project.adapter.SlideViewProductAdapter;
import com.example.h3t_project.fragment.DescriptionProductFragment;
import com.example.h3t_project.fragment.ProductPriceFragment;
import com.example.h3t_project.model.DetailProductItem;
import com.example.h3t_project.model.Product;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ViewProductActivity extends AppCompatActivity {

  ViewPager viewPager;
  SlideViewProductAdapter adapter;
  Button addToCartBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_product);
    Toolbar toolbar = findViewById(R.id.toolbarViewProduct);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Chi tiết sản phẩm");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    int productId = intent.getIntExtra("productId", 0);

    setUpImage(productId);
    setupDetailProduct(productId);
    setupDescriptionProduct(productId);
    setupPriceProduct(productId);

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar_view_product ,menu);
    return true;
  }

  public void setUpImage(int product_id){
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
    for (int i = 0; i < products.size(); i++) {
        products.get(i).setImage_id(getResId(products.get(i).getLink_image(), R.drawable.class));
    }
    viewPager = findViewById(R.id.viewPage);
    adapter = new SlideViewProductAdapter(this, products);
    viewPager.setAdapter(adapter);
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

  public void setupPriceProduct(int product_id){
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    Bundle bundle = new Bundle();
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    bundle.putString("productName", products.get(0).getName());
    bundle.putString("sellPrice", decimalFormat.format(products.get(0).getSell_price())+ " đ" );
    bundle.putString("originPrice", decimalFormat.format(products.get(0).getOrigin_price()) + " đ");
    int discount = (products.get(0).getOrigin_price() - products.get(0).getSell_price())*100/products.get(0).getOrigin_price();
    bundle.putInt("discount", discount);
    ProductPriceFragment fragment = new ProductPriceFragment();
    fragment.setArguments(bundle);
    transaction.replace(R.id.framePrice, fragment);
    transaction.commit();
  }

  public void setupDetailProduct(int product_id){
    RecyclerView recyclerView = findViewById(R.id.detailProductRecyclerView);
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
    List<DetailProductItem> detailProductItems = new ArrayList<>();
    detailProductItems.add(new DetailProductItem("Hạng mục", products.get(0).getCategory_name()));
    detailProductItems.add(new DetailProductItem("Thương hiệu", products.get(0).getBrand()));
    detailProductItems.add(new DetailProductItem("Bảo Hành", products.get(0).getGuarantee()));
    detailProductItems.add(new DetailProductItem("Số lượng sản phẩm", products.get(0).getQuantity() + " sp"));
    MenuDetailProductAdapter adapter = new MenuDetailProductAdapter(detailProductItems);
    recyclerView.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
  }

  public void setupDescriptionProduct(int product_id){
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    Bundle bundle = new Bundle();
    bundle.putString("description", products.get(0).getDescription());
    DescriptionProductFragment fragment = new DescriptionProductFragment();
    fragment.setArguments(bundle);
    transaction.replace(R.id.frameDescription, fragment);
    transaction.commit();

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        // goto back activity from here
        Intent intendCustomerViewProduct = getIntent();
        int categoryId = intendCustomerViewProduct.getIntExtra("categoryId", 0);
        Intent intent = new Intent(this, ActivityCustomerViewProduct.class);
        intent.putExtra("categoryBack", categoryId);
        startActivity(intent);
        finish();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

}
