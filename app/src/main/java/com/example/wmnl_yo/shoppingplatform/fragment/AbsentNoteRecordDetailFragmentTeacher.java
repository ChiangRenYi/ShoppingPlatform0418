package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.DeleteAbsentNoteTeacher;
import com.example.wmnl_yo.shoppingplatform.object.AbsentNoteTeacherObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AbsentNoteRecordDetailFragmentTeacher.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AbsentNoteRecordDetailFragmentTeacher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbsentNoteRecordDetailFragmentTeacher extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private AbsentNoteTeacherObject.AbsentNoteTeacherObjectItem absentNoteObject;

    private ArrayAdapter arrayAdapter;

    private ListView lvAbsentNoteRecordDetail;

    private String[] tmp;

    public static String deleteAAid,deleteAAcheck;

    public AbsentNoteRecordDetailFragmentTeacher() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AbsentNoteRecordDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AbsentNoteRecordDetailFragmentTeacher newInstance(String param1, String param2) {
        AbsentNoteRecordDetailFragmentTeacher fragment = new AbsentNoteRecordDetailFragmentTeacher();
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
        absentNoteObject = (AbsentNoteTeacherObject.AbsentNoteTeacherObjectItem) getArguments().getSerializable("absentNoteRecordDetail");

        tmp = new String[getContext().getResources().getStringArray(R.array.absentNoteRecordDetailTeacher).length];

        tmp[0] = "" + absentNoteObject.asNumber;
        deleteAAid = absentNoteObject.asNumber;
        tmp[1] = "" + absentNoteObject.asName;
        tmp[2] = "" + absentNoteObject.asBuilding;
        tmp[3] = "" + absentNoteObject.asSdate;
        tmp[4] = "" + absentNoteObject.asEdate;
        tmp[5] = "" + absentNoteObject.asType;
        tmp[6] = "" + absentNoteObject.asReason;
        tmp[7] = "" + absentNoteObject.asMan;
        tmp[8] = "" + absentNoteObject.asThings;
        tmp[9] = "" + absentNoteObject.asAstatus;
        tmp[10] = "" + absentNoteObject.asAnote;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_absent_note_record_detail, container, false);
        v.setOnTouchListener(this);

        lvAbsentNoteRecordDetail = (ListView) v.findViewById(R.id.lvAbsentNoteRecordDetail);

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_listview_order_detail) {
            @Override
            public int getCount() {
                return getContext().getResources().getStringArray(R.array.absentNoteRecordDetailTeacher).length;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ViewHolder viewHolder = new ViewHolder();
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview_order_detail, null);
                    viewHolder.tvAbsentNoteRecordDetail = (TextView) convertView.findViewById(R.id.textView);
                    viewHolder.tvAbsentNoteRecordDetailContent = (TextView) convertView.findViewById(R.id.textView2);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.tvAbsentNoteRecordDetail.setText(getContext().getResources().getStringArray(R.array.absentNoteRecordDetailTeacher)[position]);
                viewHolder.tvAbsentNoteRecordDetailContent.setText(tmp[position]);


                return convertView;
            }
        };

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_view_search, null);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_footer_view, null);
        Button btnTemp = (Button) footerView.findViewById(R.id.btnOrderSearch);
        btnTemp.setText("取消請假");
        lvAbsentNoteRecordDetail.setAdapter(arrayAdapter);
        lvAbsentNoteRecordDetail.addHeaderView(headerView);
        lvAbsentNoteRecordDetail.addFooterView(footerView);

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"請稍後...", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                DeleteAbsentNoteTeacher deleteAbsentNoteTeacher = new DeleteAbsentNoteTeacher();
                deleteAbsentNoteTeacher.execute();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (deleteAAcheck)
                        {
                            case "delete" :
                                Toast.makeText(getActivity(),
                                         "已取消"+ absentNoteObject.asSdate+"請假", Toast.LENGTH_SHORT).show();
                                ((MainActivity)getContext()).replaceFragment(AbsentNoteRecordFragmentTeacher.class, null);

                                break;
                            default:
                                break;
                        }
                    }
                },600);


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
        TextView tvAbsentNoteRecordDetail, tvAbsentNoteRecordDetailContent;
    }
}
