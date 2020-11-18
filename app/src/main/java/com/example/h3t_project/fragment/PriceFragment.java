package com.example.h3t_project.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.h3t_project.DAO.CartDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.constants.IntentCode;
import com.example.h3t_project.model.CouponItem;
import com.example.h3t_project.model.ItemCartWithPrice;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  CouponItem couponItem = null;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public PriceFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment PriceFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static PriceFragment newInstance(String param1, String param2) {
    PriceFragment fragment = new PriceFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
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
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_price, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    TextView priceOrderTv = view.findViewById(R.id.priceOrderTv);
    Button orderBtn = view.findViewById(R.id.orderBtn);
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
    int totalPrice = 0;

    SessionManagement sessionManagement = new SessionManagement(getActivity());
    int customerId = sessionManagement.getSessionUserId();
    CartDAO cartDAO = new CartDAO();
    ArrayList<ItemCartWithPrice> products = cartDAO.getAllCartWithPriceByUser(customerId);
    for (ItemCartWithPrice itemCartWithPrice : products) {
      totalPrice += itemCartWithPrice.getPrice() * itemCartWithPrice.getQuantity();
    }
    totalPrice += IntentCode.FEE_SHIPPING;

    priceOrderTv.setText(decimalFormat.format(totalPrice) + " đ");
  }

  public void receiveCoupon(CouponItem couponItem) {
    this.couponItem = couponItem;
    Log.i("HOANGGGG", String.valueOf(couponItem.getValueCoupon()));
  }
}
