package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.DeleteShoppingListObject;
import com.example.wmnl_yo.shoppingplatform.database.GetBuildingblnumber;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingList;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingListAll;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingListBuilding;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingListGoods;
import com.example.wmnl_yo.shoppingplatform.object.ShoppingCarObject;

import java.util.ArrayList;
import java.util.List;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingListFragment extends Fragment implements View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    public static MyAdapter shoppingAdapter;
    private List<ShoppingCarObject.ShoppingCarObjectItem> mShoppingCarObjectList;
    private TextView list_kind, car_object_kind_t2;
    private Button shoppingcheck;
    private ImageButton dataredo;
    public static int priceTotal = 0;
    public static String goods = "";
    public static int goodsnumber;
    public static String db_shoppinglist_kind = "請選擇";
    public static String[] values;
    public static ArrayList shoppinglistbuy;
    public static String deleteitem = "";
    public static String deleteitemcheck = "";
    public static String itemvalue = "no";
    ProgressDialog progressDoalog;
    public ShoppingListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingListFragment newInstance(String param1, String param2) {
        ShoppingListFragment fragment = new ShoppingListFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        v.setOnTouchListener(this);
        deleteitem = "";
        deleteitemcheck = "";
        goods = "";
        itemvalue = "no";
        db_shoppinglist_kind = "請選擇";
        values = null;
        GetShoppingList getShoppingListObject = new GetShoppingList();
        getShoppingListObject.execute();
        GetShoppingListAll getShoppingListAll = new GetShoppingListAll();
        getShoppingListAll.execute();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.car_rv);
        shoppingcheck = (Button) v.findViewById(R.id.car_check);
        shoppingAdapter = new MyAdapter(ShoppingCarObject.ITEMS);
        dataredo = (ImageButton) v.findViewById(R.id.dataredo);
        car_object_kind_t2 = (TextView) v.findViewById(R.id.car_object_kind_t2);
        shoppingAdapter.notifyDataSetChanged();
        car_object_kind_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{numberPicker(values);
                }catch (Exception e){
                    if(itemvalue.equals("no")) {
                        Toast.makeText(getContext(), "請檢查網路連線訊號", Toast.LENGTH_SHORT).show();
                    }else if(itemvalue.equals("nothing")) {
                        Toast.makeText(getContext(), "請重新整理\n如沒有需要結帳的商品購物車將是空的", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }

            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(shoppingAdapter);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        dataredo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteitem = "";
                deleteitemcheck = "";
                goods = "";
                db_shoppinglist_kind = "請選擇";
                values = null;
                itemvalue = "no";
                car_object_kind_t2.setText("請選擇");
                ShoppingCarObject.ITEMS.clear();
                GetShoppingList getShoppingListObject = new GetShoppingList();
                getShoppingListObject.execute();
                GetShoppingListAll getShoppingListAll = new GetShoppingListAll();
                getShoppingListAll.execute();

                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setMessage("載入中，請稍後...");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setCancelable(false);
                progressDoalog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            progressDoalog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shoppingAdapter.notifyDataSetChanged();
                    }
                },3000);

            }
        });


        shoppingcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setMessage("載入中，請稍後...");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setCancelable(false);
                progressDoalog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            progressDoalog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (db_shoppinglist_kind == "請選擇") {

                            final Dialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                            alertDialog.show();
                            alertDialog.setCanceledOnTouchOutside(false);
                            Window window = alertDialog.getWindow();
                            window.setContentView(R.layout.shopping_pushtext);
                            Button btn_cancel=(Button) window.findViewById(R.id.btn_cancel);//确定按钮
                            btn_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });

                        } else {

                            GetBuildingblnumber getBuildingblnumber = new GetBuildingblnumber();
                            getBuildingblnumber.execute();

                            String[] deletegoods = goods.split(",");
                            int count = deletegoods.length;
                            for (int i = goodsnumber - 1; i >= 0; i--) {
                                int q = 0;

                                for (int p = 0; p < count; p++) {
//                        Log.d("55125","#"+mShoppingCarObjectList.get(i).goodsCount.trim()+"&"+deletegoods[p].toString()+"#");
                                    if (mShoppingCarObjectList.get(i).goodsCount.trim().equals(deletegoods[p].toString().trim())) {
                                        q = 1;
                                    }
//                        Log.d("55125",String.valueOf(q));
                                }
                                if (q == 0) {
//                        Log.d("55125",String.valueOf(i));
                                    ShoppingCarObject.ITEMS.remove(i);
                                }
                            }
                            ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingPaymentFragment.class, null);
                        }
                    }
                }, 1500);


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView ll;
            public CheckBox checkBox;
            public TextView tvGoodsName, tvGoodsNumber, tvGoodsPrice,tvGoodWarehouse,tvGoodChildname,tvGoodBuild;
            public Button btnplus, btnminus;
            public ShoppingCarObject.ShoppingCarObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                ll = (RecyclerView) v.findViewById(R.id.car_rv);
                checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                tvGoodsName = (TextView) v.findViewById(R.id.goodsName);
                tvGoodsNumber = (TextView) v.findViewById(R.id.goodsNumber);
                tvGoodsPrice = (TextView) v.findViewById(R.id.goodsPrice);
                btnplus = (Button) v.findViewById(R.id.shoppingcar_plus);
                btnminus = (Button) v.findViewById(R.id.shoppingcar_minus);
                tvGoodWarehouse = (TextView)v.findViewById(R.id.shoppingcar_warehouse);
                tvGoodBuild = (TextView)v.findViewById(R.id.shoppingcar_build);
            }


        }

        public MyAdapter(List<ShoppingCarObject.ShoppingCarObjectItem> ShoppingCarObjectList) {
            mShoppingCarObjectList = ShoppingCarObjectList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_shoppingcar_query, parent, false);

            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            try {
                if(mShoppingCarObjectList.get(position).goodskind.equals("0")){
                    holder.tvGoodsName.setText(mShoppingCarObjectList.get(position).goodschildname.trim()+"："+mShoppingCarObjectList.get(position).goods.trim());
                }else{
                    holder.tvGoodsName.setText(mShoppingCarObjectList.get(position).goods.trim());
                }
            }catch (Exception e){}


            holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber.trim());
            priceTotal = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice.trim()).intValue()
                    * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber.trim()).intValue();

            holder.tvGoodsPrice.setText(String.valueOf(priceTotal).trim());
            holder.tvGoodWarehouse.setText(mShoppingCarObjectList.get(position).goodswarehouse.trim());
            try {
                holder.btnplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            int newgoodsnumber = Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber) + 1;
                            if (newgoodsnumber > Integer.valueOf(mShoppingCarObjectList.get(position).goodswarehouse) && mShoppingCarObjectList.get(position).goodskind.equals("1")) {
                                newgoodsnumber = Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber);
                                mShoppingCarObjectList.get(position).goodsnumber = String.valueOf(newgoodsnumber);
                                int newgoodsprice = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice.trim()).intValue() * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber.trim()).intValue();
                                holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
                                holder.tvGoodsPrice.setText(String.valueOf(newgoodsprice));
                            } else if (mShoppingCarObjectList.get(position).goodskind.equals("0")) {
                                newgoodsnumber = 1;
                                mShoppingCarObjectList.get(position).goodsnumber = String.valueOf(newgoodsnumber);
                                int newgoodsprice = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice.trim()).intValue() * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber.trim()).intValue();
                                holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
                                holder.tvGoodsPrice.setText(String.valueOf(newgoodsprice));
                            } else if (newgoodsnumber <= Integer.valueOf(mShoppingCarObjectList.get(position).goodswarehouse) && mShoppingCarObjectList.get(position).goodskind.equals("1")) {
                                mShoppingCarObjectList.get(position).goodsnumber = String.valueOf(newgoodsnumber);
                                int newgoodsprice = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice.trim()).intValue() * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber.trim()).intValue();
                                holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
                                holder.tvGoodsPrice.setText(String.valueOf(newgoodsprice));
                            }
                        }catch (Exception e){
                            shoppingAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
                        }
//                    Toast.makeText(getContext(),position+"plus"+mShoppingCarObjectList.get(position).goodsnumber,Toast.LENGTH_LONG).show();
                    }
                });
            }catch (Exception e){
                shoppingAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
            }
            try {
                holder.btnminus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            int newgoodsnumber = Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber) - 1;
                            if (newgoodsnumber <= 0) {
                                newgoodsnumber = Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber);
                                mShoppingCarObjectList.get(position).goodsnumber = String.valueOf(newgoodsnumber);
                                holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
                                int newgoodsprice = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice).intValue() * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber).intValue();
                                holder.tvGoodsPrice.setText(String.valueOf(newgoodsprice));
                            } else {
                                mShoppingCarObjectList.get(position).goodsnumber = String.valueOf(newgoodsnumber);
                                int newgoodsprice = Integer.valueOf(mShoppingCarObjectList.get(position).goodsprice).intValue() * Integer.valueOf(mShoppingCarObjectList.get(position).goodsnumber).intValue();
                                holder.tvGoodsNumber.setText(mShoppingCarObjectList.get(position).goodsnumber);
                                holder.tvGoodsPrice.setText(String.valueOf(newgoodsprice));
//                    Toast.makeText(getContext(),"minus"+mShoppingCarObjectList.get(position).goodsnumber,Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            shoppingAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }catch (Exception e){
                shoppingAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
            }
            holder.checkBox.setChecked(false);
            try{
                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (holder.checkBox.isChecked()) {
                                goods = goods + mShoppingCarObjectList.get(position).goodsCount.toString() + ",";
//                          Log.e("55125", goods);
                            } else {
                                String[] goodsarray = goods.split(",");
                                goods = "";
                                ArrayList goodsarraylist = new ArrayList();
                                for (int t = 0; t < goodsarray.length; t++) {
                                    goodsarraylist.add(goodsarray[t]);
                                }
                                goodsarraylist.remove(mShoppingCarObjectList.get(position).goodsCount.toString());
                                for (int t = 0; t < goodsarraylist.size(); t++) {
                                    goods = goods + goodsarraylist.get(t).toString() + ",";
                                }
                            }
                        }catch (Exception e){
                            shoppingAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
                        }
//
                    }
                });
            }catch (Exception e){
                shoppingAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
            }
            try {
                holder.tvGoodsName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            new AlertDialog.Builder(getActivity()).setTitle("是否刪除 " + mShoppingCarObjectList.get(position).goods.toString() + " 商品")
                                    .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            deleteitem = mShoppingCarObjectList.get(position).goodsCount.toString().trim();

                                            DeleteShoppingListObject deleteShoppingListObject = new DeleteShoppingListObject();
                                            deleteShoppingListObject.execute();
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    switch (deleteitemcheck) {
                                                        case "deletegoods":
                                                            Log.d("55125-1", deleteitemcheck);
                                                            Toast.makeText(getActivity(), "您刪除了" + mShoppingCarObjectList.get(position).goods.toString() + "商品", Toast.LENGTH_SHORT).show();
                                                            ShoppingCarObject.ITEMS.remove(position);
                                                            shoppingAdapter.notifyDataSetChanged();
                                                            break;
                                                        case "deletecourse":
                                                            Log.d("55125-1", deleteitemcheck);
                                                            Toast.makeText(getActivity(), "請至課程管理與查詢的已選課程取消", Toast.LENGTH_SHORT).show();
                                                            break;
                                                        default:
                                                            break;
                                                    }
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

                        }catch (Exception e){
                            shoppingAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }catch (Exception e){
                shoppingAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "重新整理，請稍後", Toast.LENGTH_SHORT).show();
            }

            holder.tvGoodBuild.setText(mShoppingCarObjectList.get(position).goodsbuild.trim());
        }

        @Override
        public int getItemCount() {
            return mShoppingCarObjectList.size();
        }
    }

    public void numberPicker(String[] data) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_numberpicker, null);
        Button btnSuccess = (Button) view.findViewById(R.id.btnSuccess);
        final NumberPickerView numberPickerView = (NumberPickerView) view.findViewById(R.id.picker);
        numberPickerView.setDisplayedValues(data);
        numberPickerView.setMaxValue(data.length - 1);
        numberPickerView.setMinValue(0);

        final android.app.AlertDialog ad = new android.app.AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false).show();

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
                car_object_kind_t2.setText(numberPickerView.getContentByCurrValue());
                db_shoppinglist_kind = numberPickerView.getContentByCurrValue();
                Log.e("55125", db_shoppinglist_kind);

                if (db_shoppinglist_kind == "嬰幼兒商品") {
                    goodsnumber = 0;
                    GetShoppingListGoods getShoppingListGoods = new GetShoppingListGoods();
                    getShoppingListGoods.execute();
                    goods = "";

                } else {
                    goodsnumber = 0;
                    GetShoppingListBuilding getShoppingListBuilding = new GetShoppingListBuilding();
                    getShoppingListBuilding.execute();
                    goods = "";
                }

                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setMessage("載入中，請稍後...");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setCancelable(false);
                progressDoalog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            progressDoalog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        shoppingAdapter.notifyDataSetChanged();
                    }
                },3000);

            }
        });
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
                    return true;
                }
                return false;
            }
        });
    }
}
