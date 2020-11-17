package com.example.h3t_project.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MenuDetailProductAdapter;
import com.example.h3t_project.adapter.SlideViewProductAdapter;
import com.example.h3t_project.fragment.DescriptionProductFragment;
import com.example.h3t_project.fragment.ProductPriceFragment;
import com.example.h3t_project.model.DetailProductItem;
import com.example.h3t_project.model.Product;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ViewProductActivity extends AppCompatActivity {

  ViewPager viewPager;
  SlideViewProductAdapter adapter;
  TextView mCountTv;
  MenuItem mCartIconMenuItem;
  int mCount = 0;

  public static int getResId(String resName, Class<?> c) {
    try {
      Field idField = c.getDeclaredField(resName);
      return idField.getInt(idField);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_product);
    Toolbar toolbar = findViewById(R.id.toolbarViewProduct);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Chi tiết sản phẩm");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    final int productId = intent.getIntExtra("productId", 0);

    setUpImage(productId);
    setupDetailProduct(productId);
    setupDescriptionProduct(productId);
    setupPriceProduct(productId);

    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    final List<Product> products = customerViewProductDAO.getProductById(productId);

    Button buttonAddToCart = findViewById(R.id.btnBuy2);
    SessionManagement sessionManagement = new SessionManagement(this);
    int roleId = sessionManagement.getSessionUserId();
    final String nameForCart = "mycart" + roleId;
    final String quantityForCart = "mycartquantity" + roleId;
    final String countOfCart = "countOfCart" + roleId;

    SharedPreferences preferencesCount = getSharedPreferences(countOfCart, Context.MODE_PRIVATE);
    mCount = preferencesCount.getInt(countOfCart, 0);


    buttonAddToCart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
        SharedPreferences preferencesQuantity = getSharedPreferences(quantityForCart,Context.MODE_PRIVATE);
        if (preferences.getInt(String.valueOf(products.get(0).getId()), -1) == -1) {
          mCount++;
          mCountTv.setText(String.valueOf(mCount));
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(String.valueOf(products.get(0).getId()), products.get(0).getId());
        SharedPreferences.Editor editorQuantity = preferencesQuantity.edit();
        editorQuantity.putInt(String.valueOf(products.get(0).getId()),1);
        editorQuantity.commit();
        editor.commit();
        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_toolbar_view_product, menu);
    mCartIconMenuItem = menu.findItem(R.id.action_cart);
    View cartView = mCartIconMenuItem.getActionView();
    if (cartView != null) {
      mCountTv = cartView.findViewById(R.id.countOfCart);
      SessionManagement sessionManagement = new SessionManagement(this);
      int roleId = sessionManagement.getSessionUserId();
      final String countOfCart = "countOfCart" + roleId;
      SharedPreferences preferencesCount = getSharedPreferences(countOfCart, Context.MODE_PRIVATE);
      mCount = preferencesCount.getInt(countOfCart, 0);
      mCountTv.setText(String.valueOf(mCount));
    }
    final Menu m = menu;
    final MenuItem item = menu.findItem(R.id.action_cart);
    item.getActionView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        m.performIdentifierAction(item.getItemId(), 1);
        Intent intentForCart = new Intent(ViewProductActivity.this, ActivityMyCart.class);
        startActivity(intentForCart);
      }
    });
    return true;
  }




  public void setUpImage(int product_id) {
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
    for (int i = 0; i < products.size(); i++) {
      products.get(i).setImage_id(getResId(products.get(i).getLink_image(), R.drawable.class));
    }
    viewPager = findViewById(R.id.viewPage);
    adapter = new SlideViewProductAdapter(this, products);
    viewPager.setAdapter(adapter);
  }

  public void setupPriceProduct(int product_id) {
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();
    List<Product> products = customerViewProductDAO.getProductById(product_id);
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

  public void setupDescriptionProduct(int product_id) {
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
