package com.example.h3t_project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.h3t_project.DAO.CouponDAO;
import com.example.h3t_project.R;
import com.example.h3t_project.inteface.OnSendCouponData;
import com.example.h3t_project.model.CouponItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CouponFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CouponFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private OnSendCouponData onSendDataCoupon;

  public CouponFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment CouponFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static CouponFragment newInstance(String param1, String param2) {
    CouponFragment fragment = new CouponFragment();
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
    return inflater.inflate(R.layout.fragment_coupon, container, false);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    onSendDataCoupon = (OnSendCouponData) getActivity();
  }

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final EditText couponCodeEdt = view.findViewById(R.id.couponCodeEdt);
    Button applyCouponBtn = view.findViewById(R.id.applyCouponBtn);
    final TextView codeInvalid = view.findViewById(R.id.codeInvalid);

    applyCouponBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String code = couponCodeEdt.getText().toString();

        CouponDAO couponDAO = new CouponDAO();
        final CouponItem couponByCode = couponDAO.getCouponByCode(code);

        if (code.length() == 0) {
          Toast.makeText(view.getContext(), "Xin mời nhập code để được hưởng ưu đãi", Toast.LENGTH_SHORT).show();
        }
        if (couponByCode == null) {
          codeInvalid.setText("Mã khuyến mãi không hợp lệ hoặc chưa đạt đơn hàng tối thiểu!");
        }
        if (couponByCode != null) {
          codeInvalid.setText("");
          onSendDataCoupon.onSendCouponData(couponByCode);
        }
      }
    });
  }
}
