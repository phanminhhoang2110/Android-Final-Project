package com.example.h3t_project.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.h3t_project.DAO.OrderDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.SellerListOrderAdapter;
import com.example.h3t_project.model.Order;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerListOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerListOrderFragment extends Fragment {
    public RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public int status_id;

    public SellerListOrderFragment(int status_id) {
        this.status_id = status_id;
    }
    public SellerListOrderFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellerRecylerViewListOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerListOrderFragment newInstance(String param1, String param2) {
        SellerListOrderFragment fragment = new SellerListOrderFragment();
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
        recyclerView = view.findViewById(R.id.seller_wait_listorder);
        Bundle bundle = getArguments();
        List<Order> orders = new ArrayList<>();
        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.getListOrdersForSeller(status_id);
        for (int i = 0; i < orders.size(); i ++) {
            orders.get(i).getProduct().setImage_id(getResId(orders.get(i).getProduct().getLink_image(), R.drawable.class));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.seller_wait_listorder);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        SellerListOrderAdapter adapter = new SellerListOrderAdapter(orders, getContext());
        recyclerView.setAdapter(adapter);

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
}