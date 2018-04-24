package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingListAll;
import com.example.wmnl_yo.shoppingplatform.database.Insert_newbigorderlist;
import com.example.wmnl_yo.shoppingplatform.database.Update_bigorderlist;
import com.example.wmnl_yo.shoppingplatform.database.Update_orderlist;
import com.example.wmnl_yo.shoppingplatform.object.ShoppingCarObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingPaymentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingPaymentFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static int priceTotal =0;
    public static int priceTotal_all =0;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    public static MyAdapter shoppingpaymentAdapter;
    private Button shoppingpaymentcheck;
    private RadioGroup payment_rg;
    private TextView priceTotal_text;
    public static String code_1,code_2,code_3,bigorderlistnumber;
    private String codedate_str_7;
    public static String codedate_str_8,bod_payment;
    public static String shoppingcar_goodscount,shoppingcar_goodsnumber,shoppingcar_goodsprice;
    public static String pay;


    public ShoppingPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingListCheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingPaymentFragment newInstance(String param1, String param2) {
        ShoppingPaymentFragment fragment = new ShoppingPaymentFragment();
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
        View v = inflater.inflate(R.layout.fragment_shopping_payment, container, false);
        v.setOnTouchListener(this);
//    GetAbsentNoteEntryFragmentResult getAbsentNoteEntryFragmentResult = new GetAbsentNoteEntryFragmentResult();
//        getAbsentNoteEntryFragmentResult.execute();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.payment_rv);
        shoppingpaymentcheck = (Button)v.findViewById(R.id.payment_check);
        shoppingpaymentAdapter = new MyAdapter(ShoppingCarObject.ITEMS);
        priceTotal_text = (TextView)v.findViewById(R.id.priceTotal_all);

        payment_rg = (RadioGroup)v.findViewById(R.id.payment_rg);
        //依選取項目顯示不同訊息
        priceTotal_all =0;
        shoppingcar_goodscount= "";
        shoppingcar_goodsnumber= "";
        shoppingcar_goodsprice= "";



        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(shoppingpaymentAdapter);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                priceTotal_text.setText(String.valueOf(priceTotal_all));
            }
        },300);

        shoppingpaymentcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(payment_rg.getCheckedRadioButtonId()){
                    case R.id.payment_code:
                        bod_payment = "1";
                        if(priceTotal_all == 0){
                            Toast.makeText(getContext(), "沒有需要下訂單的東西", Toast.LENGTH_SHORT).show();
                        }else {


                            //codedate_str_6 15碼前6碼 3碼代碼 6V6 6V7 6V8
                            Calendar date = Calendar.getInstance();
                            date.add(Calendar.DAY_OF_MONTH, 7);
                            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
                            codedate_str_8 = bartDateFormat.format(date.getTime());
                            String codemoney_str_3 = "";
                            int codedate_int = Integer.valueOf(codedate_str_8) - 19110000;

                            codedate_str_7 = String.valueOf(codedate_int);
                            String codedate_str_6 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length());
                            if (priceTotal_all <= 20000) {
                                codemoney_str_3 = "6V6";
                                code_1 = codedate_str_6 + codemoney_str_3;
                            } else if (20000 < priceTotal_all && priceTotal_all <= 40000) {
                                codemoney_str_3 = "6V7";
                                code_1 = codedate_str_6 + codemoney_str_3;
                            } else if (40000 < priceTotal_all) {
                                codemoney_str_3 = "6V8";
                                code_1 = codedate_str_6 + codemoney_str_3;
                            }
                            Insert_newbigorderlist insert_newbigorderlist = new Insert_newbigorderlist();
                            insert_newbigorderlist.execute();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bigorderlistnumber = String.format("%06d", Long.parseLong(bigorderlistnumber));
                                    String code2plus = "000900842" + bigorderlistnumber;
                                    int sum = 0;//將變數初始化
                                    char[] ch_array = code2plus.toCharArray();//將字串轉成字元陣列
                                    for (int i = 0; i < ch_array.length; i++) {
                                        int c = ch_array[i] - '0';//將字元做計算轉int
                                        sum += c;//加總
                                    }
                                    sum = (sum * 13) % 11;
                                    if (sum == 10)
                                        sum = 0;
                                    code_2 = "000900842" + bigorderlistnumber + String.valueOf(sum);
                                    String codedate_str_4 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length() - 2);

                                    String checklittleseven_1 = "";
                                    String checklittleseven_2 = "";
                                    int odd = 0, even = 0;
                                    code_3 = codedate_str_4 + "00" + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                    char[] code_1_str = code_1.toCharArray();
                                    char[] code_2_str = code_2.toCharArray();
                                    char[] code_3_str = code_3.toCharArray();//將字串轉成字元陣列
                                    int sum_code_1_odd = 0;
                                    for (int i = 0; i < code_1_str.length; i = i + 2) {
                                        int c = code_1_str[i] - '0';//將字元做計算轉int
                                        sum_code_1_odd += c;//加總
                                    }
                                    int sum_code_1_even = 0;
                                    for (int i = 1; i < code_1_str.length; i = i + 2) {
                                        if (code_1_str[i] == 'v') {
                                            sum_code_1_even = sum_code_1_even + 5;
                                        } else {
                                            int c = code_1_str[i] - '0';//將字元做計算轉int
                                            sum_code_1_even += c;
                                        }//加總
                                    }
                                    int sum_code_2_odd = 0;
                                    for (int i = 0; i < code_2_str.length; i = i + 2) {
                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                        sum_code_2_odd += c;//加總
                                    }
                                    int sum_code_2_even = 0;
                                    for (int i = 1; i < code_2_str.length; i = i + 2) {
                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                        sum_code_2_even += c;//加總
                                    }
                                    int sum_code_3_odd = 0;
                                    for (int i = 0; i < code_3_str.length; i = i + 2) {
                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                        sum_code_3_odd += c;//加總
                                    }
                                    int sum_code_3_even = 0;
                                    for (int i = 1; i < code_3_str.length; i = i + 2) {
                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                        sum_code_3_even += c;//加總
                                    }
                                    odd = (sum_code_1_odd + sum_code_2_odd + sum_code_3_odd) % 11;
                                    if (odd == 0)
                                        checklittleseven_1 = "A";
                                    else if (odd == 10)
                                        checklittleseven_1 = "B";
                                    else if (odd != 10 && odd != 0)
                                        checklittleseven_1 = String.valueOf(odd);
                                    even = (sum_code_1_even + sum_code_2_even + sum_code_3_even) % 11;
                                    if (even == 0)
                                        checklittleseven_2 = "X";
                                    else if (even == 10)
                                        checklittleseven_2 = "Y";
                                    else if (even != 10 && odd != 0)
                                        checklittleseven_2 = String.valueOf(even);
                                    code_3 = codedate_str_4 + checklittleseven_1 + checklittleseven_2 + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                    Log.d("55125", code_1);
                                    Log.d("55125", code_2);
                                    Log.d("55125", String.valueOf(sum_code_1_odd) + "," + String.valueOf(sum_code_1_even) + "," + String.valueOf(sum_code_2_odd) + "," + String.valueOf(sum_code_2_even) + "," + String.valueOf(sum_code_3_odd) + "," + String.valueOf(sum_code_3_even));
                                    Log.d("55125", code_3);

                                    Update_bigorderlist update_bigorderlist = new Update_bigorderlist();
                                    update_bigorderlist.execute();
                                    Update_orderlist update_orderlist = new Update_orderlist();
                                    update_orderlist.execute();
                                    Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            switch (pay) {
                                                case "success": {
                                                    Log.d("55125", shoppingcar_goodscount + "," + shoppingcar_goodsnumber + "," + shoppingcar_goodsprice);
                                                    ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingFinialFragment.class, null);
                                                    break;
                                                }
                                                case "fail": {
                                                    Toast.makeText(getContext(), "請查看商品庫存量或課程剩餘人數", Toast.LENGTH_SHORT).show();
                                                    break;
                                                }
                                                default:
                                                    break;
                                            }

                                        }
                                    }, 600);


                                }
                            }, 300);

                        }

                        break;
                    case R.id.payment_ATM:

                        bod_payment = "0";
                        if(priceTotal_all == 0){
                            Toast.makeText(getContext(), "沒有需要下訂單的東西", Toast.LENGTH_SHORT).show();
                        }else {
                            //codedate_str_6 15碼前6碼 3碼代碼 6V6 6V7 6V8
                            Calendar date_1 = Calendar.getInstance();
                            date_1.add(Calendar.DAY_OF_MONTH, 7);
                            SimpleDateFormat bartDateFormat_1 = new SimpleDateFormat("yyyyMMdd");
                            codedate_str_8 = bartDateFormat_1.format(date_1.getTime());
                            String codemoney_str_3_1 = "";
                            int codedate_int_1 = Integer.valueOf(codedate_str_8) - 19110000;

                            codedate_str_7 = String.valueOf(codedate_int_1);
                            String codedate_str_6_1 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length());
                            if (priceTotal_all <= 20000) {
                                codemoney_str_3_1 = "6V6";
                                code_1 = codedate_str_6_1 + codemoney_str_3_1;
                            } else if (20000 < priceTotal_all && priceTotal_all <= 40000) {
                                codemoney_str_3_1 = "6V7";
                                code_1 = codedate_str_6_1 + codemoney_str_3_1;
                            } else if (40000 < priceTotal_all) {
                                codemoney_str_3_1 = "6V8";
                                code_1 = codedate_str_6_1 + codemoney_str_3_1;
                            }
                            Insert_newbigorderlist insert_newbigorderlist_1 = new Insert_newbigorderlist();
                            insert_newbigorderlist_1.execute();
                            final Handler handler_1 = new Handler();
                            handler_1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bigorderlistnumber = String.format("%06d", Long.parseLong(bigorderlistnumber));
                                    String code2plus = "000900842" + bigorderlistnumber;
                                    int sum = 0;//將變數初始化
                                    char[] ch_array = code2plus.toCharArray();//將字串轉成字元陣列
                                    for (int i = 0; i < ch_array.length; i++) {
                                        int c = ch_array[i] - '0';//將字元做計算轉int
                                        sum += c;//加總
                                    }
                                    sum = (sum * 13) % 11;
                                    if (sum == 10)
                                        sum = 0;
                                    code_2 = "000900842" + bigorderlistnumber + String.valueOf(sum);
                                    String codedate_str_4 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length() - 2);

                                    String checklittleseven_1 = "";
                                    String checklittleseven_2 = "";
                                    int odd = 0, even = 0;
                                    code_3 = codedate_str_4 + "00" + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                    char[] code_1_str = code_1.toCharArray();
                                    char[] code_2_str = code_2.toCharArray();
                                    char[] code_3_str = code_3.toCharArray();//將字串轉成字元陣列
                                    int sum_code_1_odd = 0;
                                    for (int i = 0; i < code_1_str.length; i = i + 2) {
                                        int c = code_1_str[i] - '0';//將字元做計算轉int
                                        sum_code_1_odd += c;//加總
                                    }
                                    int sum_code_1_even = 0;
                                    for (int i = 1; i < code_1_str.length; i = i + 2) {
                                        if (code_1_str[i] == 'v') {
                                            sum_code_1_even = sum_code_1_even + 5;
                                        } else {
                                            int c = code_1_str[i] - '0';//將字元做計算轉int
                                            sum_code_1_even += c;
                                        }//加總
                                    }
                                    int sum_code_2_odd = 0;
                                    for (int i = 0; i < code_2_str.length; i = i + 2) {
                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                        sum_code_2_odd += c;//加總
                                    }
                                    int sum_code_2_even = 0;
                                    for (int i = 1; i < code_2_str.length; i = i + 2) {
                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                        sum_code_2_even += c;//加總
                                    }
                                    int sum_code_3_odd = 0;
                                    for (int i = 0; i < code_3_str.length; i = i + 2) {
                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                        sum_code_3_odd += c;//加總
                                    }
                                    int sum_code_3_even = 0;
                                    for (int i = 1; i < code_3_str.length; i = i + 2) {
                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                        sum_code_3_even += c;//加總
                                    }
                                    odd = (sum_code_1_odd + sum_code_2_odd + sum_code_3_odd) % 11;
                                    if (odd == 0)
                                        checklittleseven_1 = "A";
                                    else if (odd == 10)
                                        checklittleseven_1 = "B";
                                    else if (odd != 10 && odd != 0)
                                        checklittleseven_1 = String.valueOf(odd);
                                    even = (sum_code_1_even + sum_code_2_even + sum_code_3_even) % 11;
                                    if (even == 0)
                                        checklittleseven_2 = "X";
                                    else if (even == 10)
                                        checklittleseven_2 = "Y";
                                    else if (even != 10 && odd != 0)
                                        checklittleseven_2 = String.valueOf(even);
                                    code_3 = codedate_str_4 + checklittleseven_1 + checklittleseven_2 + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                    Log.d("55125", code_1);
                                    Log.d("55125", code_2);
                                    Log.d("55125", String.valueOf(sum_code_1_odd) + "," + String.valueOf(sum_code_1_even) + "," + String.valueOf(sum_code_2_odd) + "," + String.valueOf(sum_code_2_even) + "," + String.valueOf(sum_code_3_odd) + "," + String.valueOf(sum_code_3_even));
                                    Log.d("55125", code_3);

                                    Update_bigorderlist update_bigorderlist = new Update_bigorderlist();
                                    update_bigorderlist.execute();
                                    Update_orderlist update_orderlist = new Update_orderlist();
                                    update_orderlist.execute();
                                    Handler handler1 = new Handler();
                                    handler1.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            switch (pay) {
                                                case "success": {
                                                    Log.d("55125", shoppingcar_goodscount + "," + shoppingcar_goodsnumber + "," + shoppingcar_goodsprice);
                                                    ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingCarATMFragment.class, null);
                                                    break;
                                                }
                                                case "fail": {
                                                    Toast.makeText(getContext(), "請查看商品庫存量或課程剩餘人數", Toast.LENGTH_SHORT).show();
                                                    break;
                                                }
                                                default:
                                                    break;
                                            }
                                        }
                                    }, 600);


                                }
                            }, 300);
                        }
                        break;
                    case R.id.payment_card:

                        bod_payment = "2";
                        if(priceTotal_all == 0){
                            Toast.makeText(getContext(), "沒有需要下訂單的東西", Toast.LENGTH_SHORT).show();
                        }else {
                            new AlertDialog.Builder(getActivity()).setTitle("確定使用信用卡結帳?")
                                    .setMessage("請至網頁版使用信用卡結帳")
                                    .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getContext(), "請依選擇方式進行繳費", Toast.LENGTH_SHORT).show();

                                            //codedate_str_6 15碼前6碼 3碼代碼 6V6 6V7 6V8
                                            Calendar date = Calendar.getInstance();
                                            date.add(Calendar.DAY_OF_MONTH, 7);
                                            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
                                            codedate_str_8 = bartDateFormat.format(date.getTime());
                                            String codemoney_str_3 = "";
                                            int codedate_int = Integer.valueOf(codedate_str_8) - 19110000;

                                            codedate_str_7 = String.valueOf(codedate_int);
                                            String codedate_str_6 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length());
                                            if (priceTotal_all <= 20000) {
                                                codemoney_str_3 = "6V6";
                                                code_1 = codedate_str_6 + codemoney_str_3;
                                            } else if (20000 < priceTotal_all && priceTotal_all <= 40000) {
                                                codemoney_str_3 = "6V7";
                                                code_1 = codedate_str_6 + codemoney_str_3;
                                            } else if (40000 < priceTotal_all) {
                                                codemoney_str_3 = "6V8";
                                                code_1 = codedate_str_6 + codemoney_str_3;
                                            }
                                            Insert_newbigorderlist insert_newbigorderlist = new Insert_newbigorderlist();
                                            insert_newbigorderlist.execute();
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    bigorderlistnumber = String.format("%06d", Long.parseLong(bigorderlistnumber));
                                                    String code2plus = "000900842" + bigorderlistnumber;
                                                    int sum = 0;//將變數初始化
                                                    char[] ch_array = code2plus.toCharArray();//將字串轉成字元陣列
                                                    for (int i = 0; i < ch_array.length; i++) {
                                                        int c = ch_array[i] - '0';//將字元做計算轉int
                                                        sum += c;//加總
                                                    }
                                                    sum = (sum * 13) % 11;
                                                    if (sum == 10)
                                                        sum = 0;
                                                    code_2 = "000900842" + bigorderlistnumber + String.valueOf(sum);
                                                    String codedate_str_4 = codedate_str_7.substring(codedate_str_7.length() - 6, codedate_str_7.length() - 2);

                                                    String checklittleseven_1 = "";
                                                    String checklittleseven_2 = "";
                                                    int odd = 0, even = 0;
                                                    code_3 = codedate_str_4 + "00" + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                                    char[] code_1_str = code_1.toCharArray();
                                                    char[] code_2_str = code_2.toCharArray();
                                                    char[] code_3_str = code_3.toCharArray();//將字串轉成字元陣列
                                                    int sum_code_1_odd = 0;
                                                    for (int i = 0; i < code_1_str.length; i = i + 2) {
                                                        int c = code_1_str[i] - '0';//將字元做計算轉int
                                                        sum_code_1_odd += c;//加總
                                                    }
                                                    int sum_code_1_even = 0;
                                                    for (int i = 1; i < code_1_str.length; i = i + 2) {
                                                        if (code_1_str[i] == 'v') {
                                                            sum_code_1_even = sum_code_1_even + 5;
                                                        } else {
                                                            int c = code_1_str[i] - '0';//將字元做計算轉int
                                                            sum_code_1_even += c;
                                                        }//加總
                                                    }
                                                    int sum_code_2_odd = 0;
                                                    for (int i = 0; i < code_2_str.length; i = i + 2) {
                                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                                        sum_code_2_odd += c;//加總
                                                    }
                                                    int sum_code_2_even = 0;
                                                    for (int i = 1; i < code_2_str.length; i = i + 2) {
                                                        int c = code_2_str[i] - '0';//將字元做計算轉int
                                                        sum_code_2_even += c;//加總
                                                    }
                                                    int sum_code_3_odd = 0;
                                                    for (int i = 0; i < code_3_str.length; i = i + 2) {
                                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                                        sum_code_3_odd += c;//加總
                                                    }
                                                    int sum_code_3_even = 0;
                                                    for (int i = 1; i < code_3_str.length; i = i + 2) {
                                                        int c = code_3_str[i] - '0';//將字元做計算轉int
                                                        sum_code_3_even += c;//加總
                                                    }
                                                    odd = (sum_code_1_odd + sum_code_2_odd + sum_code_3_odd) % 11;
                                                    if (odd == 0)
                                                        checklittleseven_1 = "A";
                                                    else if (odd == 10)
                                                        checklittleseven_1 = "B";
                                                    else if (odd != 10 && odd != 0)
                                                        checklittleseven_1 = String.valueOf(odd);
                                                    even = (sum_code_1_even + sum_code_2_even + sum_code_3_even) % 11;
                                                    if (even == 0)
                                                        checklittleseven_2 = "X";
                                                    else if (even == 10)
                                                        checklittleseven_2 = "Y";
                                                    else if (even != 10 && odd != 0)
                                                        checklittleseven_2 = String.valueOf(even);
                                                    code_3 = codedate_str_4 + checklittleseven_1 + checklittleseven_2 + String.format("%09d", Long.parseLong(String.valueOf(priceTotal_all)));
                                                    Log.d("55125", code_1);
                                                    Log.d("55125", code_2);
                                                    Log.d("55125", String.valueOf(sum_code_1_odd) + "," + String.valueOf(sum_code_1_even) + "," + String.valueOf(sum_code_2_odd) + "," + String.valueOf(sum_code_2_even) + "," + String.valueOf(sum_code_3_odd) + "," + String.valueOf(sum_code_3_even));
                                                    Log.d("55125", code_3);

                                                    Update_bigorderlist update_bigorderlist = new Update_bigorderlist();
                                                    update_bigorderlist.execute();
                                                    Update_orderlist update_orderlist = new Update_orderlist();
                                                    update_orderlist.execute();
                                                    Handler handler1 = new Handler();
                                                    handler1.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            switch (pay) {
                                                                case "success": {
                                                                    Log.d("55125", shoppingcar_goodscount + "," + shoppingcar_goodsnumber + "," + shoppingcar_goodsprice);
                                                                    ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingListFragment.class, null);
                                                                    break;
                                                                }
                                                                case "fail": {
                                                                    Toast.makeText(getContext(), "請查看商品庫存量或課程剩餘人數", Toast.LENGTH_SHORT).show();
                                                                    break;
                                                                }
                                                                default:
                                                                    break;
                                                            }
                                                        }
                                                    }, 600);
                                                }
                                            }, 300);


                                        }
                                    })
                                    .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getContext(), R.string.cancel, Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }
                        break;
                    default:
                        Toast.makeText(getContext(),"請選擇付款方式",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    //主界面获取焦点
    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // 监听到返回按钮点击事件
                    Log.d("55123","返回鍵");
                    ShoppingListFragment.goods = "";
                    ShoppingListFragment.db_shoppinglist_kind = "請選擇";
                    GetShoppingListAll getShoppingListAll = new GetShoppingListAll();
                    getShoppingListAll.execute();
                    ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingListFragment.class, null);

                    return true;
                }
                return false;
            }
        });
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
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
        private List<ShoppingCarObject.ShoppingCarObjectItem> mShoppingCarObjectList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView ll;
            public TextView tvGoodsName,tvGoodsNumber, tvGoodsPrice;
            public ShoppingCarObject.ShoppingCarObjectItem mItem;
            public ViewHolder(View v) {
                super(v);
                ll = (RecyclerView) v.findViewById(R.id.payment_rv);
                tvGoodsName = (TextView) v.findViewById(R.id.paymenygoodsName);
                tvGoodsNumber = (TextView) v.findViewById(R.id.paymenygoodsNumber);
                tvGoodsPrice = (TextView) v.findViewById(R.id.paymenygoodsPrice);
            }
        }

        public MyAdapter(List<ShoppingCarObject.ShoppingCarObjectItem> ShoppingCarObjectList) {
            mShoppingCarObjectList = ShoppingCarObjectList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_shoppingpayment_query, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.tvGoodsName.setText(mShoppingCarObjectList.get(position).goods);
            holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
            priceTotal = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice).intValue()*Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber).intValue();
            holder.tvGoodsPrice.setText(String.valueOf(priceTotal));
            shoppingcar_goodscount = mShoppingCarObjectList.get(position).goodsCount+"@#"+shoppingcar_goodscount;
            shoppingcar_goodsnumber = mShoppingCarObjectList.get(position).goodsnumber+"@#"+shoppingcar_goodsnumber;
            shoppingcar_goodsprice = priceTotal+"@#"+priceTotal;
            priceTotal_all = priceTotal_all + priceTotal;

        }

        @Override
        public int getItemCount() {
            return mShoppingCarObjectList.size();
        }
    }
}
