package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetSatisfacationFragment;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationSurveyObject;

import java.util.ArrayList;
import java.util.List;

public class SatisfactionSurveyFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    public static MyAdapter rAdapter;

    public static String questionNaire;
    public static String childName="";
    public static String childID="";
    public static List<String> teacherList= new ArrayList<>();
    public static List<String> courseList= new ArrayList<>();
    public static List<String> questionnaireList= new ArrayList<>();
    public static List<String> satisfacationList= new ArrayList<>();
    private static List<String> question = new ArrayList<>();///andy for group information
    public static List <SatisfacationSurveyObject.SatisfacationSurveyObjectItem> mSurveyRecordList;

    Thread thread;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SatisfactionSurveyFragment newInstance(String param1, String param2) {
        SatisfactionSurveyFragment fragment = new SatisfactionSurveyFragment();
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
///////////////////////////////////////////////////////////////////////////////andywork bundle
//        Bundle bundle = getArguments();
//
//        childID=bundle.getString("childID");
//        Log.d("andygetchildID",childID);//andytemp for donnot know how crash
//
//        GetSatisfacationFragment GetSatisfacationFragmentFragmentResult = new GetSatisfacationFragment();
//        GetSatisfacationFragmentFragmentResult.execute();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
//        try{                                   //andytemp
//            thread.sleep(100);
//
//        }catch (Exception e)
//        {
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ///////////////////////////////////////////////////////////////andy doing connect server during create view for popbackstack at SatisfacationQuestionnaireFragment in btnConfirm.setOnClickListener function
        Bundle bundle = getArguments();

        childID=bundle.getString("childID");
        Log.d("andygetchildID",childID);//andytemp for donnot know how crash

        GetSatisfacationFragment GetSatisfacationFragmentFragmentResult = new GetSatisfacationFragment();
        GetSatisfacationFragmentFragmentResult.execute();
        //////////////////////////////////////////////////////////////////////
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_satisfaction_survey, container, false);
        ((MainActivity) getActivity()).setSubTitle(" > 問卷類別");

        v.setOnTouchListener(this);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);
//        Log.d("andytemp",GetSatisfacationFragment.satisfacation_teacher.toString());
//        Log.d("andyonCreateView",GetSatisfacationFragment.satisfacation_teacher[1]);
//        satisfacationList = Arrays.asList(GetSatisfacationFragment.satisfacation);

        rAdapter = new MyAdapter(SatisfacationSurveyObject.ITEMS);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(rAdapter);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

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
//        private List <SatisfacationSurveyObject.SatisfacationSurveyObjectItem> mSurveyRecordList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvTeacherName, tvCourseName, tvQuestionnaire,tvCoursePlace;
            public    SatisfacationSurveyObject.SatisfacationSurveyObjectItem mItem;


            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.ll);
                tvTeacherName = (TextView) v.findViewById(R.id.tvTeacherName);
                tvCourseName = (TextView) v.findViewById(R.id.tvCourseName);
                tvQuestionnaire = (TextView) v.findViewById(R.id.tvQuestionnaire);
                tvCoursePlace = (TextView) v.findViewById(R.id.tvCoursePlace);

            }
        }

        public MyAdapter(List<SatisfacationSurveyObject.SatisfacationSurveyObjectItem> satisfacationSurveyList) {
            mSurveyRecordList = satisfacationSurveyList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_satisfacation_survey_query_result, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo info=connManager.getActiveNetworkInfo();

                        if (info == null || !info.isConnected())
                        {
                            Toast.makeText(getActivity(),"請檢查網路,再重新嘗試點擊",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Bundle bundle = new Bundle();
                            Log.d("andyQuestionID",mSurveyRecordList.get(position).rQuestionID);
                            bundle.putString("questionNaire", mSurveyRecordList.get(position).rQuestionID);
                            SatisfacationQuestionnaireFragment fragobj = new SatisfacationQuestionnaireFragment();
                            fragobj.setArguments(bundle);
                            ((MainActivity) getContext()).replaceFragment(SatisfacationQuestionnaireFragment.class, fragobj);
                        }

                    }
                });
//            teacherList = Arrays.asList(GetSatisfacationFragment.satisfacation_teacher);//andytemp array to list
//            courseList = Arrays.asList(GetSatisfacationFragment.satisfacation_course);
//            questionnaireList = Arrays.asList(GetSatisfacationFragment.satisfacation_questionnaire);
//            Log.d("andyteacher",GetSatisfacationFragment.satisfacation_course[0]);


                holder.mItem = mSurveyRecordList.get(position);
                holder.tvTeacherName.setText(mSurveyRecordList.get(position).rTeacher);
                holder.tvCourseName.setText(mSurveyRecordList.get(position).rCourse);
                holder.tvQuestionnaire.setText(mSurveyRecordList.get(position).rQuestionnaire);
                holder.tvCoursePlace.setText(mSurveyRecordList.get(position).rCoursePlace);


        }

        @Override
        public int getItemCount() {
            return mSurveyRecordList.size();
        }
    }
}