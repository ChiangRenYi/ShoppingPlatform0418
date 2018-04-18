package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetOrderList;

import java.util.ArrayList;
import java.util.Calendar;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderManageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderManageFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView lvOrder;

    private Button btnOrderSearch;

    private Boolean[] tmp = {false, false, false, false};

    private Calendar c;

    private ArrayList<String> year, month, day;

    private int mYear, mMonth, mDay;

    private TextView[] tv;

    private View v;

    public static String sYear ="",sStartD="",sEndD="",sType="";

    private String[] divideS = new String[3];
    private String[] divideE = new String[3];

    public OrderManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderManageFragment newInstance(String param1, String param2) {
        OrderManageFragment fragment = new OrderManageFragment();
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

        tv = new TextView[getResources().getStringArray(R.array.orderFindType).length];

        c = Calendar.getInstance();
        year = new ArrayList<String>();
//        month = new ArrayList<String>();
        year.add("不限");
//        month.add("無不限");

        for (int index = c.get(Calendar.YEAR); index >= 2015; index--) {
            year.add(index + "年");
        }
//        for (int index = 1; index <= 12; index++) {
//            month.add(index + "月");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_manage, null);
            v.setOnTouchListener(this);
            ((MainActivity) getActivity()).setSubTitle(" > 訂單管理");

            lvOrder = (ListView) v.findViewById(R.id.lOrder);
            btnOrderSearch = (Button) v.findViewById(R.id.btnOrderSearch);

            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_listview_order, R.id.textView, getResources().getStringArray(R.array.orderFindType)) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ViewHolder viewHolder = new ViewHolder();
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview_order, null);
                        viewHolder.tvFindType = (TextView) convertView.findViewById(R.id.textView);
                        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.textView2);
                        tv[position] = viewHolder.tvItem;
                        convertView.setTag(viewHolder);
                    } else {
                        viewHolder = (ViewHolder) convertView.getTag();
                        tv[position] = viewHolder.tvItem;
                    }

                    viewHolder.tvFindType.setText(getResources().getStringArray(R.array.orderFindType)[position]);

                    return convertView;
                }
            };



            btnOrderSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("55123",sYear + "/" + sStartD + "/" + sEndD + "/" + sType);
                    Bundle bundle = new Bundle();

//                     String[] divideS = new String[3];
//                     String[] divideE = new String[3];

                    divideS = sStartD.split("-");
                    divideE = sEndD.split("-");

                    Log.d("55123",divideS[0]+divideS[1]+divideS[2]);

                    if(Integer.valueOf(divideS[0]) > Integer.valueOf(divideE[0]) || Integer.valueOf(divideS[1]) > Integer.valueOf(divideE[1]) || Integer.valueOf(divideS[2]) > Integer.valueOf(divideE[2])){
                        Toast.makeText(getActivity(),"日期有誤，請重新選擇!",Toast.LENGTH_SHORT).show();
                    } else if (Integer.valueOf(divideS[0]) <= Integer.valueOf(divideE[0]) && Integer.valueOf(divideS[1]) <= Integer.valueOf(divideE[1]) && Integer.valueOf(divideS[2]) <= Integer.valueOf(divideE[2]))
                    {
//                        GetOrderList getOrderList = new GetOrderList();
//                        getOrderList.execute();


                        OrderResultFragment fragobj = new OrderResultFragment();
                        fragobj.setArguments(bundle);
                        ((MainActivity) getContext()).replaceFragment(OrderResultFragment.class, fragobj);
                    }



                }
            });
            lvOrder.setAdapter(arrayAdapter);
            lvOrder.setOnItemClickListener(this);
            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_view_search, null);
            lvOrder.addHeaderView(headerView);

        }
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (i == 1) {
            numberPicker(year.toArray(new String[0]), i-1);
        }else if (i == 2) {
            datePicker(i - 1);
        } else if (i == 3) {
            datePicker(i - 1);
        } else if (i == 4) {
            numberPicker(getResources().getStringArray(R.array.category), i-1);
        }

//        if (i == 0) {
//            numberPicker(year.toArray(new String[0]), i, view);
//        } else if (i == 1) {
//            if (!tmp[0]) {
//                Toast.makeText(getContext(), R.string.year_no_choose, Toast.LENGTH_SHORT).show();
//            } else {
//                numberPicker(month.toArray(new String[0]), i, view);
//            }
//
//        } else if (i == 2) {
//            if (!tmp[0] && !tmp[1]) {
//                Toast.makeText(getContext(), R.string.year_month_no_choose, Toast.LENGTH_SHORT).show();
//            } else if (!tmp[1]) {
//                Toast.makeText(getContext(), R.string.month_no_choose, Toast.LENGTH_SHORT).show();
//            } else {
//                numberPicker(day.toArray(new String[0]), i, view);
//            }
//        } else if (i == 3) {
//            numberPicker(getResources().getStringArray(R.array.category), i, view);
//        }
    }

    public void datePicker(final int position) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                tv[position].setText(setDateFormat(year, month, day));
                if(position == 1){
                    sStartD = setDateFormat(year, month, day);
                }
                if(position == 2){
                    sEndD = setDateFormat(year, month, day);
                }
            }
        }, mYear, mMonth, mDay).show();
    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
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

    public void numberPicker(String[] data, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_numberpicker, null);
        Button btnSuccess = (Button) view.findViewById(R.id.btnSuccess);
        final NumberPickerView numberPickerView = (NumberPickerView) view.findViewById(R.id.picker);
        numberPickerView.setDisplayedValues(data);
        numberPickerView.setMaxValue(data.length - 1);
        numberPickerView.setMinValue(0);

        final AlertDialog ad = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .show();

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
//                Log.d("55124",String.valueOf(position));
                tv[position].setText(numberPickerView.getContentByCurrValue());
                if(position == 0){
                    sYear = numberPickerView.getContentByCurrValue();
                }
                if(position == 3){
                    sType = numberPickerView.getContentByCurrValue();
                }
//                if (numberPickerView.getContentByCurrValue().equals("無") && position == 0) {
//                    for (int index = position; index < tmp.length - 1; index++) {
//                        tmp[index] = false;
//                        try {
//                            tv[index].setText(numberPickerView.getContentByCurrValue());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else if (numberPickerView.getContentByCurrValue().equals("無") && position == 1) {
//                    for (int index = position; index < tmp.length - 1; index++) {
//                        tmp[index] = false;
//                        try {
//                            tv[index].setText(numberPickerView.getContentByCurrValue());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else if (position == 1 && !numberPickerView.getContentByCurrValue().equals("無")) {
//                    tmp[position] = true;
//                    c.set(Calendar.YEAR, Integer.parseInt(tv[1].getText().toString().split("年")[0]));
//                    c.set(Calendar.MONTH, Integer.parseInt(tv[2].getText().toString().split("月")[0]) - 1);
//                    day = new ArrayList<String>();
//                    for (int index = 1; index <= c.getActualMaximum(Calendar.DAY_OF_MONTH); index++)
//                        day.add(index + "日");
//                    try {
//                        if (!day.contains(tv[3].getText().toString())) {
//                            tv[3].setText("無");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else if (!numberPickerView.getContentByCurrValue().equals("無")) {
//                    tmp[position] = true;
//                } else {
//                    tmp[position] = false;
//
//                }
            }
        });
    }

    class ViewHolder {
        TextView tvFindType, tvItem;
    }

}
