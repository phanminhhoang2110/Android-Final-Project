package com.example.h3t_project.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h3t_project.DAO.OrderDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.ListOrderSellerAdapter;
import com.example.h3t_project.common.ResourceFunction;
import com.example.h3t_project.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListOrderSellerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListOrderSellerFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  public int status_id;
  RecyclerView recyclerView;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ListOrderSellerFragment(int status_id) {
    this.status_id = status_id;
  }

  public ListOrderSellerFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ListOrderSellerFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ListOrderSellerFragment newInstance(String param1, String param2) {
    ListOrderSellerFragment fragment = new ListOrderSellerFragment();
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
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    List<Order> orders = new ArrayList<>();
    OrderDAO orderDAO = new OrderDAO();
    orders = orderDAO.getListOrdersForSeller(status_id);

    for (int i = 0; i < orders.size(); i++) {
      orders.get(i).getProduct().setImage_id(ResourceFunction.getResId(orders.get(i).getProduct().getLink_image(), R.drawable.class));
    }

    recyclerView = view.findViewById(R.id.recycler_view_seller_list_order);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setNestedScrollingEnabled(true);
    ListOrderSellerAdapter adapter = new ListOrderSellerAdapter(orders, getContext());
    recyclerView.setAdapter(adapter);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_list_order_seller, container, false);
  }
}
