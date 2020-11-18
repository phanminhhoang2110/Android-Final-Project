package com.example.h3t_project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.h3t_project.DAO.DestinationDAO;
import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.interfaces.OnSendCouponData;
import com.example.h3t_project.model.Destination;
import com.example.h3t_project.model.User;
import com.example.h3t_project.sessionhelper.SessionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDestinationFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  Spinner spinner;
  ArrayList<Destination> destinations;
  User user;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private OnSendCouponData onSendCouponData;

  public OrderDestinationFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment OrderDestinationFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static OrderDestinationFragment newInstance(String param1, String param2) {
    OrderDestinationFragment fragment = new OrderDestinationFragment();
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
    return inflater.inflate(R.layout.fragment_order_destination, container, false);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    onSendCouponData = (OnSendCouponData) getActivity();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SessionManagement sessionManagement = new SessionManagement(getActivity());
    UserDAO userDAO = new UserDAO();
    int customerId = sessionManagement.getSessionUserId();
    user = userDAO.getUserById(customerId);
    setupOptionDestination(view, customerId);

    TextView nameCustomer = view.findViewById(R.id.nameCustomer);
    TextView phoneCustomer = view.findViewById(R.id.phoneCustomer);

    nameCustomer.setText(user.getFullname());
    phoneCustomer.setText(user.getPhone());

  }

  public void setupOptionDestination(View view, int customerId) {
    DestinationDAO destinationDAO = new DestinationDAO();
    destinations = (ArrayList<Destination>) destinationDAO.getDestinationByUser(customerId);
    List<String> listDestination = new ArrayList<>();
    for (int i = 0; i < destinations.size(); i++) {
      listDestination.add(destinations.get(i).toString());
    }

    spinner = view.findViewById(R.id.destination);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listDestination);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);

    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        handleSpinnerChange();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  public void handleSpinnerChange() {
    int destinationPosition = spinner.getSelectedItemPosition();
    int destinationId = destinations.get(destinationPosition).getId();
    onSendCouponData.onSendDestinationData(user.getFullname(), user.getPhone(), destinationId);
  }

}
