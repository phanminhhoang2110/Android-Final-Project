package com.example.h3t_project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.CustomerViewProductDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.CustomerVIewProductAdapter;
import com.example.h3t_project.model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerViewProductASCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerViewProductASCFragment extends Fragment {
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  public RecyclerView recyclerView;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public CustomerViewProductASCFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment CustomerViewProductASCFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static CustomerViewProductASCFragment newInstance(String param1, String param2) {
    CustomerViewProductASCFragment fragment = new CustomerViewProductASCFragment();
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

    int categoryId = -1;
    String searchText = null;
    Bundle bundle = getArguments();
    if (bundle != null) {
      categoryId = this.getArguments().getInt("categoryId");
      searchText = this.getArguments().getString("searchText");
    }
    recyclerView = view.findViewById(R.id.customer_view_product_asc_recyclerview);
    List<Product> products = new ArrayList<>();
    CustomerViewProductDAO customerViewProductDAO = new CustomerViewProductDAO();

    if (categoryId != -1) {
      products = customerViewProductDAO.getAllProductViewByCustomer(categoryId, 0, 1);
    }
    if (searchText != null) {
      products = customerViewProductDAO.getAllProductByName(searchText, 0, 1);
    }

    for (int i = 0; i < products.size(); i++) {
      products.get(i).setImage_id(getResId(products.get(i).getLink_image(), R.drawable.class));
    }

    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
    recyclerView.setLayoutManager(layoutManager);
    CustomerVIewProductAdapter adapter = new CustomerVIewProductAdapter(products, getContext());
    recyclerView.setAdapter(adapter);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_customer_view_product_a_s_c, container, false);
  }
}
