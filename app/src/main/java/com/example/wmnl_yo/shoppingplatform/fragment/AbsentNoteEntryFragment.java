package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetAbsentNoteEntryFragmentBuilding;
import com.example.wmnl_yo.shoppingplatform.database.GetAbsentNoteEntryFragmentClass;
import com.example.wmnl_yo.shoppingplatform.database.GetParentChild;
import com.example.wmnl_yo.shoppingplatform.database.SignUp_AbsentNoteEntryFragment;

import java.util.Calendar;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AbsentNoteEntryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AbsentNoteEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbsentNoteEntryFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView[] tv;
    private int mYear, mMonth, mDay;
    private int count = 0;
    private TextView absent_entry_student ,absent_entry_building,absent_entry_start,absent_entry_class,absent_entry_kind,absent_entry_money;
    private EditText absent_entry_reason;
    public static String[] string_absent_entry_student,string_absent_entry_building,string_absent_entry_class,string_absent_entry_class_new;
    private String[] db_split;
    public static String db_absent_entry_student,db_absent_entry_building,db_absent_entry_start,db_absent_entry_class,db_absent_entry_kind,db_absent_entry_money,db_absent_entry_reason;
    public static String absent_student_leave,absent_entry_student_net="no",absent_entry_class_net="no";
    private OnFragmentInteractionListener mListener;
    ProgressDialog progressDoalog;
    public AbsentNoteEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbsentNoteEntryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AbsentNoteEntryFragment newInstance(String param1, String param2) {
        AbsentNoteEntryFragment fragment = new AbsentNoteEntryFragment();
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
        string_absent_entry_student = null;
        string_absent_entry_building = null;
        string_absent_entry_class = null;
        GetParentChild getParentChild = new GetParentChild();
        getParentChild.execute();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_absent_student, container, false);
        v.setOnTouchListener(this);

        Button btnSend = (Button) v.findViewById(R.id.btnSend);
        absent_entry_student = (TextView)v.findViewById(R.id.absent_student);
        absent_entry_building = (TextView)v.findViewById(R.id.absent_building);
        absent_entry_start = (TextView)v.findViewById(R.id.absent_start);
        absent_entry_class = (TextView)v.findViewById(R.id.absent_class);
        absent_entry_kind = (TextView)v.findViewById(R.id.absent_kind);
        absent_entry_money = (TextView)v.findViewById(R.id.absent_money);
        absent_entry_reason = (EditText)v.findViewById(R.id.absent_reason);

        absent_entry_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1;
                numberPicker(string_absent_entry_student, i - 1);
            }
        });
        absent_entry_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(string_absent_entry_building == null && absent_entry_student_net.equals("no"))
                {
                    Toast.makeText(v.getContext(), "請檢查網路連線訊號", Toast.LENGTH_SHORT).show();
                    absent_entry_student.setText("請選擇");
                }else if(string_absent_entry_building == null && absent_entry_student_net.equals("nothing")) {
                    Toast.makeText(v.getContext(), db_absent_entry_student+"沒有須請假的課程", Toast.LENGTH_SHORT).show();
                }else{
                    int i = 2;
                    numberPicker(string_absent_entry_building, i - 1);
                }
            }
        });
        absent_entry_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 3;
                datePicker(i - 1);
            }
        });
        absent_entry_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 4;
                try {
                    if(db_absent_entry_building.equals("請選擇")) {
                        Toast.makeText(v.getContext(), "請先選擇托育中心", Toast.LENGTH_SHORT).show();
                    }else {
                        if (string_absent_entry_class == null && absent_entry_class_net.equals("no")) {
                            Toast.makeText(v.getContext(), "請檢查網路連線訊號", Toast.LENGTH_SHORT).show();
                            absent_entry_start.setText("請選擇");
                        } else if (string_absent_entry_class == null && absent_entry_class_net.equals("nothing")) {
                            Toast.makeText(v.getContext(), db_absent_entry_student + "在" + db_absent_entry_start + "沒有須請假的課程", Toast.LENGTH_SHORT).show();

                        } else {
                            numberPicker(string_absent_entry_class, i - 1);
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(v.getContext(), "請先選擇前面的選項", Toast.LENGTH_SHORT).show();
                }

            }
        });
        absent_entry_kind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 5;
                numberPicker(getResources().getStringArray(R.array.absentNoteEntryLeaveTypeStudent), i - 1);
            }
        });
        absent_entry_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 6;
                numberPicker(getResources().getStringArray(R.array.absentNoteRefund), i - 1);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db_absent_entry_reason = absent_entry_reason.getText().toString();
                Log.d("55125",db_absent_entry_reason);
                if(db_absent_entry_student==null||db_absent_entry_building==null||db_absent_entry_class==null||db_absent_entry_kind==null||db_absent_entry_start==null||db_absent_entry_money==null||db_absent_entry_reason==null)
                {
                    Toast.makeText(view.getContext(), "有選項沒填，請確認!!!", Toast.LENGTH_SHORT).show();
                }else {
                    SignUp_AbsentNoteEntryFragment signUp_absentNoteEntryFragment = new SignUp_AbsentNoteEntryFragment();
                    signUp_absentNoteEntryFragment.execute();
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
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                switch (absent_student_leave) {
                                    case "signup":
                                        Toast.makeText(getActivity(),
                                                db_absent_entry_student + "請假完成", Toast.LENGTH_SHORT).show();
                                        int class_number_ok = string_absent_entry_class.length;
                                        for (int i = 0; i < class_number_ok; i++) {
                                            if (i == 0)
                                                string_absent_entry_class[i] = "無";
                                            else
                                                string_absent_entry_class[i] = "";
                                        }
                                        break;
                                    case "signupcheck":
                                        Toast.makeText(getActivity(),
                                                db_absent_entry_student + "此課程已請假", Toast.LENGTH_SHORT).show();
                                        int class_number = string_absent_entry_class.length;
                                        for (int i = 0; i < class_number; i++) {
                                            if (i == 0)
                                                string_absent_entry_class[i] = "無";
                                            else
                                                string_absent_entry_class[i] = "";
                                        }
                                        break;
                                }

                            }catch (Exception e){
                                Toast.makeText(getContext(), "請檢查網路連線訊號", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, 3000);
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
                .setCancelable(false).show();

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
                if(position == 0){
                    absent_entry_student_net="no";
                    absent_entry_class_net="no";
                    absent_entry_student.setText(numberPickerView.getContentByCurrValue());
                    db_absent_entry_student = numberPickerView.getContentByCurrValue();
                    Log.e("55125",db_absent_entry_student);
                    string_absent_entry_building = null;
                    string_absent_entry_class = null;
                    absent_entry_building.setText("請選擇");
                    db_absent_entry_building= null;
                    absent_entry_start.setText("請選擇");
                    db_absent_entry_start= null;
                    absent_entry_class.setText("請選擇");
                    db_absent_entry_class= null;
                    absent_entry_kind.setText("請選擇");
                    db_absent_entry_kind= null;
                    absent_entry_money.setText("請選擇");
                    db_absent_entry_money= null;
                    absent_entry_reason.setText("");
                    db_absent_entry_reason= null;
                    GetAbsentNoteEntryFragmentBuilding getAbsentNoteEntryFragmentBuilding = new GetAbsentNoteEntryFragmentBuilding();
                    getAbsentNoteEntryFragmentBuilding.execute();
                }else if(position == 1){
                    absent_entry_class_net="no";
                    if(numberPickerView.getContentByCurrValue().equals("請選擇")){
                        Toast.makeText(getContext(),"請選擇托育中心地點",Toast.LENGTH_SHORT).show();
                        db_absent_entry_building = numberPickerView.getContentByCurrValue();
                        Log.e("55125", db_absent_entry_building);
                    }else {
                        absent_entry_building.setText(numberPickerView.getContentByCurrValue());
                        db_absent_entry_building = numberPickerView.getContentByCurrValue();
                        Log.e("55125", db_absent_entry_building);
                    }
                }else if(position == 3){
                    absent_entry_class.setText(numberPickerView.getContentByCurrValue());
                    int count = string_absent_entry_class_new.length-1;
                    Log.e("55125", String.valueOf(count));
                    for(int i = 0; i < count ; i++){
                        Log.e("55125", string_absent_entry_class_new[i+1]);
                        String[] spilt = string_absent_entry_class_new[i+1].split("-");
                        if(spilt[1].equals(numberPickerView.getContentByCurrValue())){
                            db_absent_entry_class = spilt[0];
                        }
                    }
                    Log.e("55125",db_absent_entry_class);
                }else if(position == 4){
                    absent_entry_kind.setText(numberPickerView.getContentByCurrValue());
                    try {
                        switch (numberPickerView.getContentByCurrValue()) {
                            case "事假":
                                db_absent_entry_kind = "0";
                                break;
                            case "病假":
                                db_absent_entry_kind = "1";
                                break;
                            case "喪假":
                                db_absent_entry_kind = "2";
                                break;
                            case "其他(請備註再原因)":
                                db_absent_entry_kind = "3";
                                break;
                            default:
                                break;
                        }
                    }catch (Exception e){
                        Toast.makeText(getContext(), "請重新選擇一次", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("55125",db_absent_entry_kind);
                }else if(position == 5){
                    absent_entry_money.setText(numberPickerView.getContentByCurrValue());
                    try {
                        switch (numberPickerView.getContentByCurrValue()) {
                            case "退費":
                                db_absent_entry_money = "0";
                                break;
                            case "保留":
                                db_absent_entry_money = "1";
                                break;
                            default:
                                break;
                        }
                        Log.e("55125",db_absent_entry_money);
                    }catch (Exception e){
                        Toast.makeText(getContext(), "請重新選擇一次", Toast.LENGTH_SHORT).show();
                    }
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
                            Thread.sleep(1500);
                            progressDoalog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    public void datePicker(final int position) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                if(position == 2){
                    absent_entry_start.setText(setDateFormat(year, month, day));
                    db_absent_entry_start = setDateFormat(year, month, day);
                    absent_entry_class_net="no";
                    string_absent_entry_class = null;
                    db_absent_entry_class= null;
                    absent_entry_kind.setText("請選擇");
                    db_absent_entry_kind=null;
                    absent_entry_money= null;
                    db_absent_entry_money=null;
                    absent_entry_reason.setText("");
                    db_absent_entry_reason = null;
                    GetAbsentNoteEntryFragmentClass getAbsentNoteEntryFragmentClass = new GetAbsentNoteEntryFragmentClass();
                    getAbsentNoteEntryFragmentClass.execute();
                    Log.e("55125",db_absent_entry_start);
                }
            }
        }, mYear, mMonth, mDay).show();


    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
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
        TextView tvFindType, tvItem;
        EditText reasonInput;
    }
}
