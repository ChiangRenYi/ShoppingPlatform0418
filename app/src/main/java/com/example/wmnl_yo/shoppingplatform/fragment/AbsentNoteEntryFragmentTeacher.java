package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetAbsentNoteEntryFragmentTeacherMan;
import com.example.wmnl_yo.shoppingplatform.database.SignUp_AbsentNoteEntryFragmentTeacher;

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
public class AbsentNoteEntryFragmentTeacher extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView[] tv;
    private int mYear, mMonth, mDay;
    private int mHour, mMinute;
    private EditText et_reason, ed_things;
    private String AbTeachertime ;
    private TextView absent_entry_id, absent_entry_building, absent_entry_start, absent_entry_starttime, absent_entry_end, absent_entry_endtime, absent_entry_kind, absent_entry_man;
    private EditText absent_entry_reason, absent_entry_things;
    public static String[] string_absent_entry_teacher_id, string_absent_entry_teacher_building, string_absent_entry_teacher_start, string_absent_entry_teacher_starttime,
            string_absent_entry_teacher_end, string_absent_entry_teacher_endtime, string_absent_entry_teacher_kind, string_absent_entry_teacher_man;
    private String[] db_split;
    public static String db_absent_entry_teacher_id, db_absent_entry_teacher_building, db_absent_entry_teacher_start, db_absent_entry_teacher_starttime,
            db_absent_entry_teacher_end, db_absent_entry_teacher_endtime, db_absent_entry_teacher_kind, db_absent_entry_teacher_reason, db_absent_entry_teacher_man, db_absent_entry_teacher_things;
    public static String absent_teacher_leave;//
    private OnFragmentInteractionListener mListener;

    public AbsentNoteEntryFragmentTeacher() {
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
        GetAbsentNoteEntryFragmentTeacherMan getAbsentNoteEntryFragmentTeacherMan = new GetAbsentNoteEntryFragmentTeacherMan();
        getAbsentNoteEntryFragmentTeacherMan.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_absent_teacher, container, false);
        v.setOnTouchListener(this);
        Button btnSend = (Button) v.findViewById(R.id.btnSend);

        absent_entry_id = (TextView) v.findViewById(R.id.absent_teacher_id);
        absent_entry_building = (TextView) v.findViewById(R.id.absent_building);
        absent_entry_start = (TextView) v.findViewById(R.id.absent_start);
        absent_entry_starttime = (TextView) v.findViewById(R.id.absent_starttime);
        absent_entry_end = (TextView) v.findViewById(R.id.absent_end);
        absent_entry_endtime = (TextView) v.findViewById(R.id.absent_endtime);
        absent_entry_kind = (TextView) v.findViewById(R.id.absent_kind);
        absent_entry_man = (TextView) v.findViewById(R.id.absent_man);
        absent_entry_reason = (EditText) v.findViewById(R.id.absent_reason);
        absent_entry_things = (EditText) v.findViewById(R.id.absent_things);
        absent_entry_id.setText(db_absent_entry_teacher_id);
        absent_entry_building.setText(db_absent_entry_teacher_building);
        /*absent_entry_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 2;
                numberPicker(string_absent_entry_teacher_building, i - 1);
            }
        });*/
        absent_entry_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 3;
                datePicker(i - 1);
            }
        });
        absent_entry_starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 4;
                timePicker(i - 1);
            }
        });
        absent_entry_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 5;
                datePicker(i - 1);
            }
        });
        absent_entry_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 6;
                timePicker(i - 1);
            }
        });
        absent_entry_kind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 7;
                numberPicker(getResources().getStringArray(R.array.absentNoteEntryLeaveTypeTeacher), i - 1);
            }
        });
        absent_entry_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 9;
                numberPicker(string_absent_entry_teacher_man, i - 1);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db_absent_entry_teacher_reason = absent_entry_reason.getText().toString();
                db_absent_entry_teacher_things = absent_entry_things.getText().toString();
                Toast.makeText(getActivity(), "請稍後...", Toast.LENGTH_SHORT).show();
                if(db_absent_entry_teacher_kind==null||db_absent_entry_teacher_reason==null||db_absent_entry_teacher_man==null||db_absent_entry_teacher_things==null||db_absent_entry_teacher_start==null||db_absent_entry_teacher_starttime==null||db_absent_entry_teacher_end==null||db_absent_entry_teacher_endtime==null)
                {
                    Toast.makeText(view.getContext(), "有選項沒填，請確認!!!", Toast.LENGTH_SHORT).show();
                }else {
                    SignUp_AbsentNoteEntryFragmentTeacher signUp_absentNoteEntryFragmentTeacher = new SignUp_AbsentNoteEntryFragmentTeacher();
                    signUp_absentNoteEntryFragmentTeacher.execute();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            switch (absent_teacher_leave) {

                                case "signup":
                                    Toast.makeText(getActivity(), "請假完成", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }

                        }
                    }, 500);
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
                if (position == 1) {
                    absent_entry_building.setText(numberPickerView.getContentByCurrValue());
                    db_absent_entry_teacher_building = numberPickerView.getContentByCurrValue();
                    Log.e("55125", db_absent_entry_teacher_building);
                } else if (position == 8) {
                    absent_entry_man.setText(numberPickerView.getContentByCurrValue());
                    db_absent_entry_teacher_man = numberPickerView.getContentByCurrValue();
                    Log.e("55125", db_absent_entry_teacher_man);
                } else if (position == 6) {
                    absent_entry_kind.setText(numberPickerView.getContentByCurrValue());
                    switch (numberPickerView.getContentByCurrValue()) {
                        case "事假":
                            db_absent_entry_teacher_kind = "0";
                            break;
                        case "病假":
                            db_absent_entry_teacher_kind = "1";
                            break;
                        case "公假":
                            db_absent_entry_teacher_kind = "2";
                            break;
                        case "婚假":
                            db_absent_entry_teacher_kind = "3";
                            break;
                        case "特休假":
                            db_absent_entry_teacher_kind = "4";
                            break;
                        case "喪假":
                            db_absent_entry_teacher_kind = "5";
                            break;
                        case "產假":
                            db_absent_entry_teacher_kind = "6";
                            break;
                        case "生理假":
                            db_absent_entry_teacher_kind = "7";
                            break;
                        case "其他(請備註再原因)":
                            db_absent_entry_teacher_kind = "8";
                            break;
                        default:
                            break;
                    }
                    Log.e("55125", db_absent_entry_teacher_kind);
                }

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
                if (position == 2) {
                    absent_entry_start.setText(setDateFormat(year, month, day));
                    db_absent_entry_teacher_start = setDateFormat(year, month, day);
                    Log.e("55125", db_absent_entry_teacher_start);
                } else if (position == 4) {
                    absent_entry_end.setText(setDateFormat(year, month, day));
                    db_absent_entry_teacher_end = setDateFormat(year, month, day);
                    Log.e("55125", db_absent_entry_teacher_end);
                }
            }
        }, mYear, mMonth, mDay).show();
    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }

    public void timePicker(final int position) {
        // 設定初始時間
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // 跳出時間選擇器
        TimePickerDialog tpd = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (position == 3) {
                    absent_entry_starttime.setText(setTimeFormat(hourOfDay, minute));
                    db_absent_entry_teacher_starttime = setTimeFormat(hourOfDay, minute);
                    Log.e("55125", db_absent_entry_teacher_starttime);
                } else if (position == 5) {
                    absent_entry_endtime.setText(setTimeFormat(hourOfDay, minute));
                    db_absent_entry_teacher_endtime = setTimeFormat(hourOfDay, minute);
                    Log.e("55125", db_absent_entry_teacher_endtime);
                }
            }
        }, mHour, mMinute, false);
        tpd.show();
    }

    private String setTimeFormat(int hourOfDay, int minute) {
        if (hourOfDay < 10 && minute < 10) {
            AbTeachertime = "0" + String.valueOf(hourOfDay) + ":" + "0" + String.valueOf(minute);
        } else if (hourOfDay < 10 && minute >= 10) {
            AbTeachertime = "0" + String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
        } else if (hourOfDay >= 10 && minute < 10) {
            AbTeachertime = String.valueOf(hourOfDay) + ":" + "0" + String.valueOf(minute);
        } else if (hourOfDay >= 10 && minute >= 10) {
            AbTeachertime = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
        }
        return AbTeachertime;
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
