package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.wmnl_yo.shoppingplatform.database.GetHealthWeightFragment;
import com.example.wmnl_yo.shoppingplatform.object.HealthWeightObject;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HealthWeightFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthWeightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthWeightFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    public static MyHealthAdapter wAdapter;
    public static List<HealthWeightObject.HealthWeightObjectItem> mhealthWeightList;
//    private List<HealthWeightObject.HealthWeightObjectItem> healthWeightList = new ArrayList<HealthWeightObject.HealthWeightObjectItem>();
    private OnFragmentInteractionListener mListener;

    public HealthWeightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthWeightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthWeightFragment newInstance(String param1, String param2) {
        HealthWeightFragment fragment = new HealthWeightFragment();
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
        //pic, 體重, 單位, 時間, 日期
//        healthWeightList.add(new HealthWeightObject(R.drawable.ic_weight,31.8,"公斤", "Root","10:10","2017-04-22"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_health_weight, container, false);
        v.setOnTouchListener(this);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        wAdapter = new MyHealthAdapter(HealthWeightObject.ITEMS);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //分隔線
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManager.getOrientation());

        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connManager.getActiveNetworkInfo();

        if (info == null || !info.isConnected())
        {
            Toast.makeText(getActivity(),"請檢查網路後,重新進入此頁面",Toast.LENGTH_LONG).show();
        }else{
            GetHealthWeightFragment getHealthWeightFragment = new GetHealthWeightFragment();
            getHealthWeightFragment.execute();
        }

//        Toast.makeText(getActivity(),"請稍後...", Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wAdapter.notifyDataSetChanged();
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(wAdapter);
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
//        private List<HealthWeightObject.HealthWeightObjectItem> mhealthWeightList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView rv;
            public ImageView ivPic;
            public TextView tvValue, tvUnit, tvDateTime;
            public HealthWeightObject.HealthWeightObjectItem mItem;


            public ViewHolder(View v) {
                super(v);
                rv = (RecyclerView) v.findViewById(R.id.rv);
                ivPic = (ImageView) v.findViewById(R.id.ivHealth);
                tvValue = (TextView) v.findViewById(R.id.tvHealthValue);
                tvUnit = (TextView) v.findViewById(R.id.tvHealthUnit);
                tvDateTime = (TextView) v.findViewById(R.id.tvHealthDateTime);
            }
        }

        public MyHealthAdapter(List<HealthWeightObject.HealthWeightObjectItem> healthWeightList) {
            mhealthWeightList = healthWeightList;
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
            holder.ivPic.setImageResource(mhealthWeightList.get(position).pic);
            holder.tvValue.setText(String.valueOf(mhealthWeightList.get(position).value));
            holder.tvUnit.setText(mhealthWeightList.get(position).unit);
            holder.tvDateTime.setText(mhealthWeightList.get(position).datetime);
        }

        @Override
        public int getItemCount() {
            return mhealthWeightList.size();
        }
    }
}
