package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetAnnouncement;
import com.example.wmnl_yo.shoppingplatform.object.AnnouncementObject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CategoryFragment extends Fragment implements View.OnTouchListener{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    //  public static MyMemberRecyclerViewAdapter adapter;

    private View view;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(int columnCount) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
//        AnnouncementObject.ITEMS.clear();
//        AnnouncementObject dim = new AnnouncementObject();
//        dim.addItem(new AnnouncementObject.AnnouncementObjectItem("2017-10.25","中秋節快樂","666"));
//        dim.addItem(new AnnouncementObject.AnnouncementObjectItem("444","(停課公告)黏土DIY課程因教師臨時有事......","666"));

        GetAnnouncement getAnnouncement = new GetAnnouncement();
        getAnnouncement.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_announcement, container, false);
        v.setOnTouchListener(this);

        recyclerView = (RecyclerView) v.findViewById(R.id.an_recyclerview);


        myAdapter = new MyAdapter(AnnouncementObject.ITEMS);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //分隔線
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myAdapter);
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        }, 500);
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
        private List<AnnouncementObject.AnnouncementObjectItem> AnnouncementObjectList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvTitle;

            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.an_ll);
                tvTitle = (TextView) v.findViewById(R.id.announcement);

            }
        }

        public MyAdapter(List<AnnouncementObject.AnnouncementObjectItem> announcementObjectList) {

            AnnouncementObjectList = announcementObjectList;

        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_announcement, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }



        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Toast.makeText(view.getContext(),"aaaa"+position,Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("announcementDetail", AnnouncementObjectList.get(position));
                    AnnouncementDetailFragment fragobj = new AnnouncementDetailFragment();
                    fragobj.setArguments(bundle);
                    ((MainActivity) getContext()).replaceFragment_for_Announcement(AnnouncementDetailFragment.class, fragobj);
                }
            });
            holder.tvTitle.setText(AnnouncementObjectList.get(position).anTitle);

        }



        @Override
        public int getItemCount() {
            return AnnouncementObjectList.size();
        }
    }
}

