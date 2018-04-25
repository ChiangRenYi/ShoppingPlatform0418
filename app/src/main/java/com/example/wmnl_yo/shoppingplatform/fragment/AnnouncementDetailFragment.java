package com.example.wmnl_yo.shoppingplatform.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.object.AnnouncementObject;

/**
 * Created by WMNL-Jimmy on 2017/11/14.
 */

public class AnnouncementDetailFragment extends Fragment implements View.OnTouchListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CourseQueryDetailFragment.OnFragmentInteractionListener mListener;

    private AnnouncementObject.AnnouncementObjectItem announcementObject;

    private ListView lvAnnouncement;

    private ArrayAdapter arrayAdapter;

    private String[] tmp;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public AnnouncementDetailFragment(){

    }

    public static AnnouncementDetailFragment newInstance(String param1, String param2) {
        AnnouncementDetailFragment fragment = new AnnouncementDetailFragment();
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
        announcementObject = (AnnouncementObject.AnnouncementObjectItem) getArguments().getSerializable("announcementDetail");

        tmp = new String[getContext().getResources().getStringArray(R.array.announcementDetail).length];

        tmp[0] = announcementObject.anTitle;
        tmp[1] = announcementObject.anTime;
        tmp[2] = announcementObject.anContent;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_announcement_detail, container, false);
        v.setOnTouchListener(this);

        lvAnnouncement = (ListView) v.findViewById(R.id.lAnnouncement);

        arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_listview_announcement_detail) {
            @Override
            public int getCount() {
                return getContext().getResources().getStringArray(R.array.announcementDetail).length;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                ViewHolder viewHolder = new ViewHolder();
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview_announcement_detail, null);
                    viewHolder.tvAnnouncementDetail = (TextView) convertView.findViewById(R.id.tvAnnouncementDetail);
                    viewHolder.tvAnnouncementDetailContent = (TextView) convertView.findViewById(R.id.tvAnnouncementDetailContent);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (AnnouncementDetailFragment.ViewHolder) convertView.getTag();
                }

                viewHolder.tvAnnouncementDetail.setText(getContext().getResources().getStringArray(R.array.announcementDetail)[position]);
//                if (position == 2) {
//                    viewHolder.tvAnnouncementDetailContent.setText(tmp[position]);
//                    viewHolder.tvAnnouncementDetailContent.setGravity(Gravity.LEFT);
//                } else {
//                    viewHolder.tvAnnouncementDetailContent.setText(tmp[position]);
//                }

                viewHolder.tvAnnouncementDetailContent.setText(tmp[position]);
                return convertView;
            }
        };

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_announcement_footer_view, null);
        Button btnBack = (Button) footerView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).replaceFragment_for_Announcement(CategoryFragment.class,null);
            }
        });
        lvAnnouncement.setAdapter(arrayAdapter);
        lvAnnouncement.addFooterView(footerView);
        return v;
    }

    class ViewHolder {
        TextView tvAnnouncementDetail, tvAnnouncementDetailContent;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
