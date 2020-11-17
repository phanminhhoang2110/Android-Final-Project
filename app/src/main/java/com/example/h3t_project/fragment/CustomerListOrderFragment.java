package com.example.h3t_project.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h3t_project.DAO.CustomerListOrderDao;
import com.example.h3t_project.R;
import com.example.h3t_project.adapter.CustomerListOrderAdapter;
import com.example.h3t_project.model.Order;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListOrderFragment extends Fragment {
    public RecyclerView recyclerView;
    public int status_id;

    public CustomerListOrderFragment(int status_id) {
        this.status_id = status_id;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerListOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerListOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerListOrderFragment newInstance(String param1, String param2) {
        CustomerListOrderFragment fragment = new CustomerListOrderFragment();
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
        CustomerListOrderDao customerListOrderDao = new CustomerListOrderDao();
        SessionManagement sessionManagement = new SessionManagement(getContext());
        int customerId = sessionManagement.getSessionUserId();
        orders = customerListOrderDao.getListOrdersForCustomer(customerId, status_id);
        for (int i = 0; i < orders.size(); i ++) {
            orders.get(i).setImageId(getResId(orders.get(i).getImageLink(), R.drawable.class));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recycler_view_customer_list_order);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        CustomerListOrderAdapter adapter = new CustomerListOrderAdapter(orders, getContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_list_order, container, false);
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