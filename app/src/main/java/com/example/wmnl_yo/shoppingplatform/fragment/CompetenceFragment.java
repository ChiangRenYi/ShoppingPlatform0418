package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetCompetenceState;
import com.example.wmnl_yo.shoppingplatform.database.Update_Competence;
import com.example.wmnl_yo.shoppingplatform.object.CompetenceObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2018/5/5.
 */

public class CompetenceFragment extends Fragment implements View.OnTouchListener{
    private RecyclerView mRecyclerView;
    private Button submit;
    public static MyAdapter competenceAdapter;
    public static String SendCom ="" ;
    public static String[] Building;
    public static String[] comBuilding;
    ProgressDialog progressDoalog;
    private List<CompetenceObject.CompetenceObjectItem> mCompetenceObjectList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//
//        CompetenceObject.ITEMS.clear();
//        CompetenceObject dim = new CompetenceObject();
//
//        dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                "三峽光明公共托育中心",
//                true
//        ));
//        dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                "三峽中園公共托育中心",
//                true
//        ));
//        dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                "樹林三多公共托育中心",
//                false
//        ));
//        dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                "林口麗林公共托育中心",
//                false
//        ));
//        dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                "樹林武林公共托育中心",
//                true
//        ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_competence, container, false);
        v.setOnTouchListener(this);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.com_recyclerview);
        submit = (Button) v.findViewById(R.id.com_submit);
        competenceAdapter = new MyAdapter(CompetenceObject.ITEMS);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int a = 0; a< mCompetenceObjectList.size(); a++){

                    if(mCompetenceObjectList.get(a).competenceState == true){
                        SendCom = SendCom.concat("@#"+mCompetenceObjectList.get(a).competenceName);
                    }
                }

                Log.d("55123-1",mCompetenceObjectList.size()+"");
//                Log.d("55123-2",mCompetenceObjectList.get(0).competenceState.toString());
//                Log.d("55123-3",mCompetenceObjectList.get(1).competenceState.toString());
//                Log.d("55123-4",mCompetenceObjectList.get(2).competenceState.toString());
//                Log.d("55123-5",mCompetenceObjectList.get(3).competenceState.toString());
//                Log.d("55123-6",mCompetenceObjectList.get(4).competenceState.toString());
                Log.d("55123-10",SendCom);

                progressDoalog = new ProgressDialog(getActivity());
                progressDoalog.setMessage("權限修改中，請稍後...");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.setCancelable(false);
                progressDoalog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Update_Competence update_competence = new Update_Competence();
                            update_competence.execute();

                            Thread.sleep(5000);
                            progressDoalog.dismiss();


                            SendCom = "";
                            ((MainActivity)getContext()).replaceFragment( MemberServiceFragment.class, null);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(competenceAdapter);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        return v;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView ll;
            public CheckBox checkBox;
            public TextView tvName;
            public CompetenceObject.CompetenceObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                ll = (RecyclerView) v.findViewById(R.id.com_recyclerview);
                checkBox = (CheckBox) v.findViewById(R.id.com_checkBox);
                tvName = (TextView) v.findViewById(R.id.com_name);
            }

        }
    public MyAdapter(List<CompetenceObject.CompetenceObjectItem> CompetenceObjectList) {
        mCompetenceObjectList = CompetenceObjectList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_recycleview_competence, parent, false);

        ViewHolder vh = new ViewHolder(v);

        for(int i = 1; i < comBuilding.length ; i++){
            for(int n = 1; n < Building.length ; n++){
                if(Arrays.asList(Building[n]).contains(comBuilding[i].trim())){
                    mCompetenceObjectList.get(n-1).competenceState = true;
                }
            }
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(mCompetenceObjectList.get(position).competenceName.trim());
        holder.checkBox.setChecked(mCompetenceObjectList.get(position).competenceState);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mCompetenceObjectList.get(position).competenceState.toString())
                {
                    case "true":
                        //mCompetenceObjectList.get(position).competenceState = false;
                        Toast.makeText(getActivity(),"欲刪除親子館權限，請洽行政!",Toast.LENGTH_SHORT).show();
                        holder.checkBox.setChecked(true);
                        break;

                    case "false" :
                        mCompetenceObjectList.get(position).competenceState = true;
                        break;

                    default:
                        break;
                }
            }
        });
        Log.d("55123",mCompetenceObjectList.get(position).competenceName + "權限:" + mCompetenceObjectList.get(position).competenceState);

    }

    @Override
    public int getItemCount() {
        return mCompetenceObjectList.size();
    }
}
}