package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.wmnl_yo.shoppingplatform.database.GetCourseRecordFragmentResult;
import com.example.wmnl_yo.shoppingplatform.object.CourseRecordObject;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseRecordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseRecordFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    public static MyAdapter rAdapter;
    ProgressDialog progressDoalog;

    public static String checkCourseRecord = "";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseRecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseRecordFragment newInstance(String param1, String param2) {
        CourseRecordFragment fragment = new CourseRecordFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_course_record, container, false);
        v.setOnTouchListener(this);
        checkCourseRecord = "";
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);

        rAdapter = new MyAdapter(CourseRecordObject.ITEMS);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GetCourseRecordFragmentResult getCourseRecordFragmentResult = new GetCourseRecordFragmentResult();
        getCourseRecordFragmentResult.execute();

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
                Log.d("55125",checkCourseRecord+"123");
                if(checkCourseRecord.equals("") ){
                    Toast.makeText(getContext(),"請檢查網路連線訊號",Toast.LENGTH_SHORT).show();
                }else if(checkCourseRecord.equals("nothing")){
                    Toast.makeText(getContext(),"沒有購買課程的紀錄",Toast.LENGTH_SHORT).show();
                }else {
                    rAdapter.notifyDataSetChanged();
                }
            }
        },3000);

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
        private List<CourseRecordObject.CourseRecordObjectItem> mCourseRecordList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvCourseName, tvCourseTeacher, tvCourseDate, tvCourseTime;
            public CourseRecordObject.CourseRecordObjectItem mItem;


            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.ll);
                tvCourseName = (TextView) v.findViewById(R.id.courseName);
                tvCourseTeacher = (TextView) v.findViewById(R.id.courseTeacher);
                tvCourseDate = (TextView) v.findViewById(R.id.courseDate);
            }
        }

        public MyAdapter(List<CourseRecordObject.CourseRecordObjectItem> courseRecordList) {
            mCourseRecordList = courseRecordList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_course_query_result, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("courseRecordDetail",mCourseRecordList.get(position));
                    CourseRecordDetailFragment fragobj = new CourseRecordDetailFragment();
                    fragobj.setArguments(bundle);
                    ((MainActivity) getContext()).replaceFragment(CourseRecordDetailFragment.class, fragobj);
                }
            });
            holder.mItem = mCourseRecordList.get(position);
            holder.tvCourseName.setText(mCourseRecordList.get(position).rClassname);
            holder.tvCourseTeacher.setText(mCourseRecordList.get(position).rTeacher);
            holder.tvCourseDate.setText(mCourseRecordList.get(position).rSDate+"~"+mCourseRecordList.get(position).rEDate);
        }

        @Override
        public int getItemCount() {
            return mCourseRecordList.size();
        }
    }

}
