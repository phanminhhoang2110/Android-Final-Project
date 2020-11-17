package com.example.h3t_project.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MyCartAdapter;
import com.example.h3t_project.model.Product;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public MyCartFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MyCartFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MyCartFragment newInstance(String param1, String param2) {
    MyCartFragment fragment = new MyCartFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
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

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    List<Product> products = new ArrayList<>();
    List<Product> temp;
    TextView viewTotalMoney = view.findViewById(R.id.total_money_price);
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    int totalPrice = 0;
    CustomerViewProductDAO dao = new CustomerViewProductDAO();
    SessionManagement sessionManagement = new SessionManagement(getActivity());
    int roleId = sessionManagement.getSessionUserId();
    String nameForCart = "mycart" + roleId;
    SharedPreferences preferences = this.getActivity().getSharedPreferences(nameForCart, Context.MODE_PRIVATE);
    Map<String, ?> listCart = preferences.getAll();
    Set<String> productsId = listCart.keySet();
    for (String productId : productsId) {
      Product product = new Product();
      temp = dao.getProductById(Integer.parseInt(productId));
      product.setId(temp.get(0).getId());
      product.setName(temp.get(0).getName());
      product.setSell_price(temp.get(0).getSell_price());
      product.setImage_id(getResId(temp.get(0).getLink_image(), R.drawable.class));
      product.setQuantityInCart(1);
      products.add(product);
      totalPrice += temp.get(0).getSell_price();
    }
    viewTotalMoney.setText(decimalFormat.format(totalPrice) + " đ");

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    RecyclerView recyclerView = view.findViewById(R.id.my_cart_recycler_view);
    recyclerView.setLayoutManager(linearLayoutManager);
    final MyCartAdapter myCartAdapter = new MyCartAdapter(getActivity(), products, viewTotalMoney);
    recyclerView.setItemAnimator(null);
    recyclerView.setAdapter(myCartAdapter);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_my_cart, container, false);
  }
}
