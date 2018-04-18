package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetOrderList;
import com.example.wmnl_yo.shoppingplatform.database.GetOrderListDetail;
import com.example.wmnl_yo.shoppingplatform.object.OrderObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderResultFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    List<OrderObject> orderObjectList = new ArrayList<>();


    private  String payway,s_payway,state,s_state;
    private RecyclerView recyclerView;
    public static String NumForDB;
    private MyAdapter myAdapter;

    public OrderResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderResultFragment newInstance(String param1, String param2) {
        OrderResultFragment fragment = new OrderResultFragment();
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
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            orderObjectList.add(new OrderObject("2342344546", "課程", "課程", sdf.parse("2017-06-03"), "ATM", "無", "未結帳", "無", 5000, 1, 5000, 5000, "孩子較為內向"));
//            orderObjectList.add(new OrderObject("2342344547", "課程", "課程", sdf.parse("2017-06-04"), "ATM", "無", "未結帳", "無", 5000, 1, 5000, 5000, "孩子較為內向"));
//            orderObjectList.add(new OrderObject("2342344548", "課程", "商品", sdf.parse("2017-06-05"), "ATM", "無", "未結帳", "無", 5000, 1, 5000, 5000, "孩子較為內向"));
//            orderObjectList.add(new OrderObject("2342344549", "課程", "商品", sdf.parse("2017-12-15"), "ATM", "無", "未結帳", "無", 5000, 1, 5000, 5000, "孩子較為內向"));
//         //   orderObjectList.add(new OrderObject("2342344550", "幼兒用品", "尿布", sdf.parse("2017-06-07"), sdf.parse("2017-06-10"), "ATM", "7-11貨到付款", "未結帳", "已出貨", 1000, 3, 3000, 3000, "要白色的"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_result, container, false);
        v.setOnTouchListener(this);
        ((MainActivity) getActivity()).setSubTitle(" > 訂單管理");
        GetOrderList getOrderList = new GetOrderList();
        getOrderList.execute();

        recyclerView = (RecyclerView) v.findViewById(R.id.ol_recyclerview);

        myAdapter = new MyAdapter(OrderObject.ITEMS);
        myAdapter.notifyDataSetChanged();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //分隔線
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myAdapter);
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        }, 300);

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

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<OrderObject.OrderObjectItem> OrderObjectList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvDate,tvNum,tvState,tvPayway;

            //  public AnnouncementObject.AnnouncementObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.orderll);
                tvDate = (TextView) v.findViewById(R.id.olDate);
                tvNum = (TextView) v.findViewById(R.id.olNum);
                tvState = (TextView) v.findViewById(R.id.olState);
                tvPayway = (TextView) v.findViewById(R.id.olPayway);
            }
        }

        public MyAdapter(List<OrderObject.OrderObjectItem> orderObjectList) {
            OrderObjectList = orderObjectList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_listview_order_result, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }



        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            switch (OrderObjectList.get(position).paymentMethod){
                case "0":
                    payway = "ATM";
                    break;

                case "1":
                    payway = "條碼付款";
                    break;

                case "2":
                    payway = "信用卡";
                    break;
                default:
                    break;
            }

            switch (OrderObjectList.get(position).orderState){
                case "0":
                    state = "未付款";
                    holder.tvState.setTextColor(Color.parseColor("#FF0000"));
                    break;

                case "1":
                    state = "已付款";
                    break;
                default:
                    break;
            }

            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NumForDB = OrderObjectList.get(position).businessNumber;
                    Log.d("55125-DB",NumForDB);
                    //Toast.makeText(view.getContext(),"aaaa"+position,Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("orderNumber", OrderObjectList.get(position).businessNumber);
                    bundle.putString("orderDate", OrderObjectList.get(position).orderDate);
                    bundle.putString("payway",OrderObjectList.get(position).paymentMethod);
                    switch (OrderObjectList.get(position).paymentMethod){
                        case "0":
                            s_payway = "ATM";
                            break;

                        case "1":
                            s_payway = "條碼付款";
                            break;

                        case "2":
                            s_payway = "信用卡";
                            break;

                        default:
                            break;
                    }

                    switch (OrderObjectList.get(position).orderState){
                        case "0":
                            s_state = "未付款";
                            break;

                        case "1":
                            s_state = "已付款";
                            break;
                        default:
                            break;
                    }

                    bundle.putString("orderPayway", s_payway);
                    bundle.putString("orderState", s_state);
                    bundle.putString("orderMoney", OrderObjectList.get(position).total);
                    final OrderDetailFragment  fragobj = new OrderDetailFragment();
                    fragobj.setArguments(bundle);


                    GetOrderListDetail getOrderListDetail = new GetOrderListDetail();
                    getOrderListDetail.execute();
                    OrderDetailFragment.orderDes = "";


                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity) getContext()).replaceFragment(OrderDetailFragment.class, fragobj);
                        }
                    }, 100);

//                    ((MainActivity) getContext()).replaceFragment(OrderDetailFragment.class, fragobj);
                }
            });






            holder.tvDate.setText(OrderObjectList.get(position).orderDate);
            holder.tvNum.setText(String.format("%06d",Long.parseLong(OrderObjectList.get(position).businessNumber)));
            Log.d("55125-read",payway);
            holder.tvPayway.setText(payway);
            holder.tvState.setText(state);
        }



        @Override
        public int getItemCount() {
            return OrderObjectList.size();
        }
    }

}
