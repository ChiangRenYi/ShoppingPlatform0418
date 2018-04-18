package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.object.OpinionObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OpinionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OpinionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpinionFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    private List<OpinionObject> opinionList = new ArrayList<>();

    public OpinionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpinionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpinionFragment newInstance(String param1, String param2) {
        OpinionFragment fragment = new OpinionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opinion, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuTemp = item.getItemId();
        if (menuTemp == R.id.menuAddOpinion) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("opinionList", (Serializable) opinionList);
            OpinionAddFragment fragobj = new OpinionAddFragment();
            fragobj.setArguments(bundle);
            ((MainActivity) getContext()).replaceFragment(OpinionAddFragment.class, fragobj);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        try {
            opinionList = (List<OpinionObject>) getArguments().getSerializable("opinionList");
        } catch (Exception e) {
            e.printStackTrace();
            opinionList.add(new OpinionObject(R.drawable.ic_person, "2017/2/20", "Andy", "教職員工", "大家好"));
            opinionList.add(new OpinionObject(R.drawable.ic_person2, "2017/2/18", "Ricky", "教職員工", "今天天氣真好"));
            opinionList.add(new OpinionObject(R.drawable.ic_person3, "2017/2/14", "Paul", "教職員工", "Lucky~"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_opinion, container, false);
        ((MainActivity) getActivity()).setSubTitle(" > 意見交流");
        v.setOnTouchListener(this);

        recyclerView = (RecyclerView) v.findViewById(R.id.lOpinion);

        MyAdapter myAdapter = new MyAdapter(opinionList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

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
        private List<OpinionObject> mOpinionList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView ivProfilePicture;
            public TextView tvDate, tvPersonName, tvPosition, tvContent;

            public ViewHolder(View v) {
                super(v);
                ivProfilePicture = (ImageView) v.findViewById(R.id.list_avatar);
                tvDate = (TextView) v.findViewById(R.id.list_title);
                tvPersonName = (TextView) v.findViewById(R.id.list_person_name);
                tvPosition = (TextView) v.findViewById(R.id.list_subtitle);
                tvContent = (TextView) v.findViewById(R.id.list_content);
            }
        }

        public MyAdapter(List<OpinionObject> opinionList) {
            mOpinionList = opinionList;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_opinion, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.ivProfilePicture.setImageResource(mOpinionList.get(position).getProfilePicture());
            holder.tvDate.setText(mOpinionList.get(position).getDate());
            holder.tvPersonName.setText(mOpinionList.get(position).getPersonName());
            holder.tvPosition.setText(mOpinionList.get(position).getPosition());
            holder.tvContent.setText(mOpinionList.get(position).getMessage());
        }

        @Override
        public int getItemCount() {
            return opinionList.size();
        }
    }

}
