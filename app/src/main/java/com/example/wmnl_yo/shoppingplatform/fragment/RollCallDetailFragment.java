package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RollCallDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RollCallDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RollCallDetailFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList personList = new ArrayList();
    private ArrayList personIDList = new ArrayList();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private String courseName,courseNumber;
    private TextView tvCourseNumber, tvCourseName;
    private SwitchCompat mSwitchCompat;

    public RollCallDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RollCallDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RollCallDetailFragment newInstance(String param1, String param2) {
        RollCallDetailFragment fragment = new RollCallDetailFragment();
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
        personList.add("陳在晽");
        personList.add("陳可霖");
        personList.add("張敏淇");
        personList.add("張悅");
        personList.add("許雅婷");

        personIDList.add("1");
        personIDList.add("2");
        personIDList.add("3");
        personIDList.add("4");
        personIDList.add("5");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_roll_call_detail, container, false);
        v.setOnTouchListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);
        tvCourseNumber = (TextView) v.findViewById(R.id.tvCourseNumber);
        tvCourseName = (TextView) v.findViewById(R.id.tvCourseName);

        mAdapter = new MyAdapter(personIDList, personList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        courseNumber = getArguments().getString("courseNumber");
        courseName = getArguments().getString("courseName");
        tvCourseNumber.setText("課程編號 : " + courseNumber);
        tvCourseName.setText("課程名稱 : " + courseName);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
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
        private ArrayList mPersonIDList = new ArrayList<>();
        private ArrayList mPersonList = new ArrayList<>();

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvPersonID, tvPersonName;

            public ViewHolder(View v) {
                super(v);
                tvPersonID = (TextView) v.findViewById(R.id.personID);
                tvPersonName = (TextView) v.findViewById(R.id.personName);
            }
        }

        public MyAdapter(ArrayList personIDList, ArrayList personList) {
            mPersonIDList = personIDList;
            mPersonList = personList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_roll_call_detail, parent, false);

            mSwitchCompat = (SwitchCompat)v.findViewById(R.id.RollCallSwitch);



            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvPersonID.setText(mPersonIDList.get(position).toString());
            holder.tvPersonName.setText(mPersonList.get(position).toString());

            if(mPersonIDList.get(position).equals("1")){
                studentLeave();

            }
        }


        private void studentLeave(){
            mSwitchCompat.setText("請假");
            mSwitchCompat.setClickable(false);
            mSwitchCompat.setChecked(false);

        }

        @Override
        public int getItemCount() {
            return mPersonIDList.size();
        }
    }

}
