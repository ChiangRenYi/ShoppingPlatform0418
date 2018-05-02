package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
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
import com.example.wmnl_yo.shoppingplatform.database.GetSatisfacationChildFragmentResult;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationChildObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SatisfacationChildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SatisfacationChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SatisfacationChildFragment extends Fragment implements View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static List<SatisfacationChildObject.SatisfacationChildObjectItem> mChildRecordList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    public static MyAdapter rAdapter;

//    public ArrayList<String>  childList= new ArrayList<String>();
    public static String child="";
    public static List<String> childList= new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SatisfacationChildFragment newInstance(String param1, String param2) {
        SatisfacationChildFragment fragment = new SatisfacationChildFragment();
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



//        GetSatisfacationChildFragmentResult getSatisfacationChildFragmentResult = new GetSatisfacationChildFragmentResult();
//        getSatisfacationChildFragmentResult.execute();//執行連網動作

        ///////////設delay 需等待連網取得資料的速度，才會讓後面setText的結果顯示出資料庫抓取的資料，而不是初值。
        int i=0;
//        for (i=0; childList!= null; i++)//andytemp
//        {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                Log.d("Loadchild","timeOUT");
//            }
//            if(i>=100) {
//                Log.d("Loadchild","timeOUT");
//                break;
//            }
//
//        }
//        Log.d("i:", Integer.toString(i));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_satisfacation_child, container, false);
        ((MainActivity) getActivity()).setSubTitle(" > 滿意度調查");

        v.setOnTouchListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);

//        Log.d("string", child);
//        List<String> childArray= Arrays.asList( child.split("<QQ>"));
//        Log.d("string",child);
//        childList=Arrays.asList(GetSatisfacationChildFragmentResult.satisfacation); //andytemp

        rAdapter = new MyAdapter(SatisfacationChildObject.ITEMS);//()內填入需顯示的小孩資訊

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(rAdapter);

        mRecyclerView.addItemDecoration(dividerItemDecoration);
        GetSatisfacationChildFragmentResult getSatisfacationChildFragmentResult = new GetSatisfacationChildFragmentResult();
        getSatisfacationChildFragmentResult.execute();//執行連網動作
//        Log.d("recordlist",String.valueOf(mChildRecordList.size()));

//            Log.d("recordlist",String.valueOf(mChildRecordList.size()));

//            if(mChildRecordList.size()==0)
//            {
//                Toast.makeText(getActivity(),"網路不穩請重新嘗試",Toast.LENGTH_LONG);
//            }
//        if(mChildRecordList.size()==0)
//        {
//            Toast.makeText(getActivity(),"網路不穩請重新嘗試",Toast.LENGTH_LONG);
//        }


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
//        private List<SatisfacationChildObject.SatisfacationChildObjectItem> mChildRecordList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvChildName;
            public  SatisfacationChildObject.SatisfacationChildObjectItem   mItem;


            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.ll);
                tvChildName = (TextView) v.findViewById(R.id.childName);
                Log.d("childlist",String.valueOf(mChildRecordList.size()));

            }
        }

        public MyAdapter(List<SatisfacationChildObject.SatisfacationChildObjectItem> satisfacationChildList) {
            mChildRecordList = satisfacationChildList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_child_query_result, parent, false);
            MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);

            return vh;
        }



        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {


            //holder.mItem = mChildRecordList.get(position);  //andytemp
//            List<String> childArray= Arrays.asList( child.split("<QQ>"));//andytemp
//            child=child.replace("@#","");//andyOldVersion
//            childList =Arrays.asList(child.split("<QQ>"));//andyOldVersion
//            Log.d("andytemp",childList.get(position));//andytemp

//            childArray=child.split("<QQ>");
//            childList.add(child);//andytemp
//            Log.d("andytemp","output "+ childArray.get(position).toString());



  /////////////////////////////////////////////////andytemp
//            Log.d("childName",child);
//            Log.d("abcdefg","abcedfadsf");
//            child=child.replace("<QQ>","");
//            child=child.replace("@#","");
//            if(position!=0)
//            holder.tvChildName.setText(childList.get(position)); //andyOldVersion



//            Log.d("recordlist",String.valueOf(mChildRecordList.size()));


//                holder.tvChildName.setText(childArray[0].toString());
//            holder.tvChildName.setText(childArray.get(1).toString());
//            Log.d("andytest",childArray.get(1).toString());
//            holder.tvChildName.setText(childList.get(0)); //bug here//andytemp
//                Log.d("recordlist",String.valueOf(mChildRecordList.size()));
//
//                if(mChildRecordList.size()==0)
//                {
//                    Toast.makeText(getActivity(),"網路不穩請重新嘗試",Toast.LENGTH_LONG);
//                }

                holder.mItem = mChildRecordList.get(position);
                Log.d("andyOnBindView", mChildRecordList.get(position).rChildName);
                Log.d("childID", mChildRecordList.get(position).rChild_ID);
                holder.tvChildName.setText(mChildRecordList.get(position).rChildName);

                holder.ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("childID", mChildRecordList.get(position).rChild_ID);
//                    Log.d("andysendchildID",childList.get(position));    //get the position for child name
                        SatisfactionSurveyFragment fragobj = new SatisfactionSurveyFragment();
                        fragobj.setArguments(bundle);
                        ((MainActivity) getContext()).replaceFragment(SatisfactionSurveyFragment.class, fragobj);
                    }
                });




        }

        @Override
        public int getItemCount() {
            return mChildRecordList.size();
        }
    }
}
