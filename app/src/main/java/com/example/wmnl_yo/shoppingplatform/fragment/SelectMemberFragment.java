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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetSelectMemberFragment;
import com.example.wmnl_yo.shoppingplatform.object.SelectMemberObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectMemberFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectMemberFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv;
    public static MySelectAdapter bAdapter;
    public static List<SelectMemberObject.SelectMemberObjectItem> mselectmemberList;
//    private List<SelectMemberObject.SelectMemberObjectItem> selectmemberList = new ArrayList<SelectMemberObject.SelectMemberObjectItem>();
    private OnFragmentInteractionListener mListener;
    public static String bBuilding;

    public SelectMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectMemberFragment newInstance(String param1, String param2) {
        SelectMemberFragment fragment = new SelectMemberFragment();
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
        View v = inflater.inflate(R.layout.fragment_health_select, container, false);
        ((MainActivity) getActivity()).setSubTitle(" > 互動訊息");
        v.setOnTouchListener(this);

        rv = (RecyclerView) v.findViewById(R.id.rv);
        bAdapter = new MySelectAdapter(SelectMemberObject.ITEMS);

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
            GetSelectMemberFragment getSelectMemberFragment = new GetSelectMemberFragment();
            getSelectMemberFragment.execute();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bAdapter.notifyDataSetChanged();
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(bAdapter);
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

    public class MySelectAdapter extends RecyclerView.Adapter<MySelectAdapter.ViewHolder> {
//        private List<SelectMemberObject.SelectMemberObjectItem> mselectmemberList;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout rv;
            public ImageView ivPic;
            public TextView tvName;
            public SelectMemberObject.SelectMemberObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                rv = (RelativeLayout) v.findViewById(R.id.rv1);
                ivPic = (ImageView) v.findViewById(R.id.ivStudent);
                tvName = (TextView) v.findViewById(R.id.tvStudentName);
            }
        }

        public MySelectAdapter(List<SelectMemberObject.SelectMemberObjectItem> selectmemberList) {
            mselectmemberList = selectmemberList;
        }

        @Override
        public MySelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_listview_select, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info=connManager.getActiveNetworkInfo();

                    if (info == null || !info.isConnected())
                    {
                        Toast.makeText(getActivity(),"請檢查網路",Toast.LENGTH_LONG).show();
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("AUId",mselectmemberList.get(position).AU_id);
                        bundle.putSerializable("ERId",mselectmemberList.get(position).ER_id);
                        OpinionMessengerFragmentTeacher fragobj = new OpinionMessengerFragmentTeacher();
                        fragobj.setArguments(bundle);
//                    Log.d("55125", String.valueOf(bundle));

                        ((MainActivity) getContext()).replaceFragment(OpinionMessengerFragmentTeacher.class, fragobj);//null fragobj
                    }
                }
            });
            holder.mItem = mselectmemberList.get(position);
            holder.ivPic.setImageResource(mselectmemberList.get(position).pic);
            holder.tvName.setText(mselectmemberList.get(position).name);
        }

        @Override
        public int getItemCount() {
            return mselectmemberList.size();
        }
    }
}
