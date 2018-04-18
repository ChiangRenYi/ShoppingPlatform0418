package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetOpinionMessageFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OpinionMessengerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OpinionMessengerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpinionMessengerFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static String AUId="";
    public static String ERId="";
    public static String OMText="";
    public static String OMDate="";
    public static String OMTime="";

    private List<Fragment> fragmentList = new ArrayList<>();
    public EditText etMessage;
    private ViewPager vp;
    private FloatingActionButton fab_bt;
    private MyPagerAdapter mAdapter;
    private OnFragmentInteractionListener mListener;
    public static String[] tmp1;


    public OpinionMessengerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpinionAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpinionMessengerFragment newInstance(String param1, String param2) {
        OpinionMessengerFragment fragment = new OpinionMessengerFragment();
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

        //接收打包資料進行拆解
        Bundle bundle = getArguments();

        AUId = bundle.getString("AUId");
        ERId = bundle.getString("ERId");

        Log.d("55125", AUId+","+ERId);

        fragmentList.add(OpinionMessageAddFragment.newInstance("",""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_opinion_add, container, false);
        v.setOnTouchListener(this);

        vp = (ViewPager) v.findViewById(R.id.vp);
        fab_bt = (FloatingActionButton)v.findViewById(R.id.fab_print);
        etMessage = (EditText) v.findViewById(R.id.etMessage);

        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mAdapter);

        fab_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat hm=new SimpleDateFormat("HH:mm");
                OMDate = ymd.format(new java.util.Date());
                OMTime = hm.format(new java.util.Date());
                OMText = etMessage.getText().toString();
//                Log.d("55125", String.valueOf(OMDate));
//                Log.d("55125", String.valueOf(OMTime));
//                Log.d("55125", String.valueOf(OMText));

                GetOpinionMessageFragment getOpinionMessageFragment = new GetOpinionMessageFragment();
                getOpinionMessageFragment.execute();

//                Toast.makeText(getActivity(),"訊息已送出", Toast.LENGTH_SHORT).show();

                etMessage.setText("");
                OMText="";
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
