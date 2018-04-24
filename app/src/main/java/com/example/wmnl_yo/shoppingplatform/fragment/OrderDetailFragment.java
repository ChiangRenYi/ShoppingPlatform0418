package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.DeleteShoppingBigorderlist;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ListView productDetailList;

    private ArrayAdapter arrayAdapter;

    private String orderListNum,orderDate,orderPayway,orderState,orderMoney,test;

    private String[] tmp = new String[14];

    public static String orderTypeNum,orderDes = "",bod_id,payway;
    private String orderType;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetailFragment newInstance(String param1, String param2) {
        OrderDetailFragment fragment = new OrderDetailFragment();
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
//        tmp[0] = orderObject.getBusinessNumber();
//        tmp[1] = orderObject.getProductType();
//        tmp[2] = orderObject.getProductName();
//        tmp[3] = orderObject.getOrderDate().getYear() + 1900 + "/" + orderObject.getOrderDate().getMonth() + "/" + orderObject.getOrderDate().getDate();
//       // tmp[4] = orderObject.getShippingDate().getYear() + 1900 + "/" + orderObject.getShippingDate().getMonth() + "/" + orderObject.getShippingDate().getDate();
//        tmp[4] = orderObject.getPaymentMethod();
//        tmp[5] = orderObject.getShippingMethod();
//        tmp[6] = orderObject.getOrderState();
//        tmp[7] = orderObject.getShippingState();
//        tmp[8] = orderObject.getProductPrice() + "";
//        tmp[9] = orderObject.getProductAmount() + "";
//        tmp[10] = orderObject.getSubtotal() + "";
//        tmp[11] = orderObject.getTotal() + "";
//        tmp[12] = orderObject.getDescription();

//        Log.d("55125-tst",orderTypeNum);





    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_detail, null);
        v.setOnTouchListener(this);

        orderListNum = (String)getArguments().get("orderNumber");
//        orderNumForDB = (String)getArguments().get("orderNumber");
        orderDate = (String)getArguments().get("orderDate");
        orderPayway = (String)getArguments().get("orderPayway");
        orderState = (String)getArguments().get("orderState");
        orderMoney = (String)getArguments().get("orderMoney");
        bod_id = orderListNum;
        payway = (String)getArguments().get("payway");
        Log.d("55125-test",orderListNum+"/"+orderDate+"/"+orderPayway+"/"+orderState);

        switch (orderTypeNum){

            case"0":
                orderType = "課程";
                break;

            case"1":
                orderType = "商品";
                break;
            default:
                break;
        }


        tmp[0] = String.format("%06d",Long.parseLong(orderListNum));
        tmp[1] = orderType;//抓資料庫
        tmp[2] = orderDate;
        tmp[3] = orderPayway;
        tmp[4] = "無";
        tmp[5] = orderState;
        tmp[6] = "無";
        tmp[7] = orderMoney;
        tmp[8] = orderDes;

        productDetailList = (ListView) v.findViewById(R.id.lOrder);

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_listview_order_detail) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ViewHolder holder = new ViewHolder();
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview_order_detail, null);
                    holder.tvProductDetail = (TextView) convertView.findViewById(R.id.textView);
                    holder.tvProductDetailContent = (TextView) convertView.findViewById(R.id.textView2);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tvProductDetail.setText(getContext().getResources().getStringArray(R.array.productDetail)[position]);
                holder.tvProductDetailContent.setText(tmp[position]);

                return convertView;
            }

            @Override
            public int getCount() {
                return getContext().getResources().getStringArray(R.array.productDetail).length;
            }
        };

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_view_search, null);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_odfooter_view, null);

        Button btnGoPay = (Button) footerView.findViewById(R.id.btnOrderPay);
        final Button btnCancelOrder = (Button) footerView.findViewById(R.id.btnCancelOrder);

        btnCancelOrder.setText("取消訂單");

        if(orderState.equals("已付款")){
            btnGoPay.setEnabled(false);
            btnGoPay.getBackground().setColorFilter(0xFF888888,android.graphics.PorterDuff.Mode.MULTIPLY );
            btnCancelOrder.getBackground().setColorFilter(0xFF888888,android.graphics.PorterDuff.Mode.MULTIPLY );
            btnCancelOrder.setEnabled(false);
        }else {
            btnGoPay.setEnabled(true);
            btnCancelOrder.setEnabled(true);
        }

        btnGoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (payway){
                    case "0":
                        ShoppingCarATMFragment.sp_atm_number_data = orderListNum;
                        ShoppingCarATMFragment.get_page_way = "1";
                        ((MainActivity)getContext()).replaceFragment(ShoppingCarATMFragment.class, null);

                        break;

                    case "1":
                        ShoppingFinialFragment.sp_code_number_data = orderListNum;
                        ShoppingFinialFragment.get_page_way = "1";
                        ((MainActivity)getContext()).replaceFragment(ShoppingFinialFragment.class, null);

                        break;

                    case "2":

                        new AlertDialog.Builder(getActivity())
                                .setMessage("請至網頁版使用信用卡結帳")
                                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })

                                .show();

                        break;
                    default:
                        break;
                }

            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("是否確定取消訂單?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DeleteShoppingBigorderlist deleteShoppingBigorderlist = new DeleteShoppingBigorderlist();
                                deleteShoppingBigorderlist.execute();

                                arrayAdapter.notifyDataSetChanged();
                                ((MainActivity)getContext()).replaceFragment(OrderResultFragment.class, null);

                            }
                        })
                        .setNeutralButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                             //留空
                            }
                        })
                        .show();
            }
        });

        productDetailList.setAdapter(arrayAdapter);
        productDetailList.addHeaderView(headerView);
        productDetailList.addFooterView(footerView);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class ViewHolder {
        TextView tvProductDetail, tvProductDetailContent;
    }

}
