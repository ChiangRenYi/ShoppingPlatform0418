package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetHealthHeightFragment;
import com.example.wmnl_yo.shoppingplatform.object.HealthHeightObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HealthHeightFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthHeightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthHeightFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    public static MyHealthAdapter hAdapter;
    private List<HealthHeightObject.HealthHeightObjectItem> healthHeightList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public HealthHeightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthHeightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthHeightFragment newInstance(String param1, String param2) {
        HealthHeightFragment fragment = new HealthHeightFragment();
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
        GetHealthHeightFragment getHealthHeightFragment = new GetHealthHeightFragment();
        getHealthHeightFragment.execute();

        //pic, 身高, 單位, 時間, 日期
//        healthHeightList.add(new HealthHeightObject(R.drawable.ic_height,124.0,"公分", "Root","10:05","2017-04-22"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health_height, container, false);
        v.setOnTouchListener(this);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        hAdapter = new MyHealthAdapter(HealthHeightObject.ITEMS);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //分隔線
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());

        Toast.makeText(getActivity(),"請稍後...", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hAdapter.notifyDataSetChanged();
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(hAdapter);
                rv.addItemDecoration(dividerItemDecoration);
            }
        }, 300);

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

    public class MyHealthAdapter extends RecyclerView.Adapter<MyHealthAdapter.ViewHolder> {
        private List<HealthHeightObject.HealthHeightObjectItem> mhealthHeightList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView rv;
            public ImageView ivPic;
            public TextView tvValue, tvUnit, tvDateTime;
            public HealthHeightObject.HealthHeightObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                rv = (RecyclerView) v.findViewById(R.id.rv);
                ivPic = (ImageView) v.findViewById(R.id.ivHealth);
                tvValue = (TextView) v.findViewById(R.id.tvHealthValue);
                tvUnit = (TextView) v.findViewById(R.id.tvHealthUnit);
                tvDateTime = (TextView) v.findViewById(R.id.tvHealthDateTime);
            }
        }

        public MyHealthAdapter(List<HealthHeightObject.HealthHeightObjectItem> healthHeightList) {
            mhealthHeightList = healthHeightList;
        }

        @Override
        public MyHealthAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_listview_health, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.ivPic.setImageResource(mhealthHeightList.get(position).pic);
            holder.tvValue.setText(String.valueOf(mhealthHeightList.get(position).value));
            holder.tvUnit.setText(mhealthHeightList.get(position).unit);
            holder.tvDateTime.setText(mhealthHeightList.get(position).datetime);
        }

        @Override
        public int getItemCount() {
            return mhealthHeightList.size();
        }
    }

}
