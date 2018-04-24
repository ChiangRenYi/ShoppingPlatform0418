package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.object.CourseObject;
import com.example.wmnl_yo.shoppingplatform.object.RollCallObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RollCallFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RollCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RollCallFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayList<RollCallObject> rollcallList = new ArrayList();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    public RollCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RollCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RollCallFragment newInstance(String param1, String param2) {
        RollCallFragment fragment = new RollCallFragment();
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

        RollCallObject.ITEMS.clear();
        RollCallObject dim = new RollCallObject();

        dim.addItem(new RollCallObject.RollCallObjectItem("0001","芭蕾舞", "2017/8/7", "09:00~12:00"));
        dim.addItem(new RollCallObject.RollCallObjectItem("0002","土風舞", "2017/8/16", "10:00~13:00"));
        dim.addItem(new RollCallObject.RollCallObjectItem( "0003","棒球", "2017/8/24", "13:00~15:00"));
        dim.addItem(new RollCallObject.RollCallObjectItem( "0004","籃球", "2017/8/25", "09:00~21:00"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_roll_call, container, false);
        v.setOnTouchListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);

        mAdapter = new MyAdapter(RollCallObject.ITEMS);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

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
        private List<RollCallObject.RollCallObjectItem> RollCallObjectList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvCourseName, tvCourseDate, tvCourseTime;
            public Button btnRollCall;

            public ViewHolder(View v) {
                super(v);
                tvCourseName = (TextView) v.findViewById(R.id.courseName);
                tvCourseDate = (TextView) v.findViewById(R.id.courseDate);
                tvCourseTime = (TextView) v.findViewById(R.id.courseTime);
                btnRollCall = (Button) v.findViewById(R.id.btnRollCall);
            }
        }

        public MyAdapter(List<RollCallObject.RollCallObjectItem> rollCallObjectList) {
            RollCallObjectList = rollCallObjectList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_roll_call, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvCourseName.setText(RollCallObjectList.get(position).CourseName);
            holder.tvCourseDate.setText(RollCallObjectList.get(position).CourseDate);
            holder.tvCourseTime.setText(RollCallObjectList.get(position).CourseTime);

            holder.btnRollCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("courseNumber", RollCallObjectList.get(position).CourseNumber);
                    bundle.putString("courseName", RollCallObjectList.get(position).CourseName);
                    RollCallDetailFragment fragobj = new RollCallDetailFragment();
                    fragobj.setArguments(bundle);
                    ((MainActivity) getContext()).replaceFragment(RollCallDetailFragment.class,fragobj);
//                    ((MainActivity)getContext()).getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.flContent, fragobj).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return RollCallObjectList.size();
        }
    }

}
