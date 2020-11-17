package com.example.h3t_project.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CartDAO;
import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.MyCartAdapter;
import com.example.h3t_project.model.ItemCartDetail;
import com.example.h3t_project.model.Product;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCartFragment extends Fragment implements MyCartAdapter.ResetAdapter {

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
  RecyclerView recyclerView;
  ArrayList<ItemCartDetail> itemCarts;
  int totalPrice = 0;
  TextView viewTotalMoney;
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewTotalMoney = view.findViewById(R.id.total_money_price);
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    SessionManagement sessionManagement = new SessionManagement(getActivity());
    int roleId = sessionManagement.getSessionUserId();
    CartDAO cartDAO = new CartDAO();
    itemCarts = cartDAO.getAllCartDetail(roleId);
    for (ItemCartDetail itemCart : itemCarts) {
        totalPrice += itemCart.getSellPrice() * itemCart.getQuantity();
    }
    viewTotalMoney.setText(decimalFormat.format(totalPrice) + " đ");


    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    recyclerView = view.findViewById(R.id.my_cart_recycler_view);
    recyclerView.setLayoutManager(linearLayoutManager);
    final MyCartAdapter myCartAdapter = new MyCartAdapter(getActivity(), itemCarts, viewTotalMoney, this);
    recyclerView.setItemAnimator(null);
    recyclerView.setAdapter(myCartAdapter);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_my_cart, container, false);
  }

  @Override
  public void reset(int quantity,int indexProduct) {
    totalPrice = 0;
    itemCarts.get(indexProduct).setQuantity(quantity);
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    for (ItemCartDetail itemCart : itemCarts) {
      totalPrice += itemCart.getSellPrice() * itemCart.getQuantity();
    }
    viewTotalMoney.setText(decimalFormat.format(totalPrice) + " đ");
    MyCartAdapter myCartAdapter = new MyCartAdapter(getActivity(),itemCarts,viewTotalMoney,this);
    recyclerView.setAdapter(myCartAdapter);
  }
}
