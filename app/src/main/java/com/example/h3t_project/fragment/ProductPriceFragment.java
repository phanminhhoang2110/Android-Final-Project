package com.example.h3t_project.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.h3t_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductPriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductPriceFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ProductPriceFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ProductPriceFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ProductPriceFragment newInstance(String param1, String param2) {
    ProductPriceFragment fragment = new ProductPriceFragment();
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
    return inflater.inflate(R.layout.fragment_product_price, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    String productName = "";
    String sellPrice = "";
    String originPrice = "";
    int discount = 0;
    Bundle bundle = getArguments();
    if (bundle != null){
      productName = this.getArguments().getString("productName");
      sellPrice = this.getArguments().getString("sellPrice");
      originPrice = this.getArguments().getString("originPrice");
      discount = this.getArguments().getInt("discount");
    }
    TextView name = view.findViewById(R.id.nameProduct);
    TextView sell = view.findViewById(R.id.sellPrice);
    TextView origin = view.findViewById(R.id.originPrice);
    TextView dis = view.findViewById(R.id.discountPercent);
    name.setText(productName);
    sell.setText(sellPrice);
    if (originPrice.equalsIgnoreCase(sellPrice)){
      origin.setText("");
    }else {
      origin.setText("Giá thị trường \n"+originPrice);
    }

    if (discount !=0) {
      dis.setText("-" + discount + "%");
    }else {
      dis.setText("");
    }

  }
}
