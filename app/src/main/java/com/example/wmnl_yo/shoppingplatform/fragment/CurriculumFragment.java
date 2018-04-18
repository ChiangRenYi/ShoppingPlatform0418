package com.example.wmnl_yo.shoppingplatform.fragment;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WMNL-YO on 2017/2/21.
 */

public class CurriculumFragment extends Fragment {

    private View view;
    private GridView gridView;
    private TextView tv;

    static final String[] numbers = new String[] {
            "數學", "美語", "語文", "美語", "數學",
            "語文", "音樂", "數學", "音樂", "美語",
            "音樂", "語文", "數學", "數學", "美語",
            "音樂", "美語", "音樂", "美語", "語文",
            "數學", "數學", "美語", "音樂", "數學"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null){
            view = inflater.inflate(R.layout.fragment_curriculum, container, false);
        }
//        gridView = (GridView)view.findViewById(R.id.layout_curriculum_subject);
//        final List<String> plantsList = new ArrayList<String>(Arrays.asList(numbers));
//        gridView.setColumnWidth(300);
//        gridView.setAdapter(new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_list_item_1, plantsList) {
//                                public View getView(int position, View convertView, ViewGroup parent) {
//                                    View view = super.getView(position, convertView, parent);
//
//                                    TextView tv = (TextView) view;
//
//                                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//                                            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
//                                    );
//                                    tv.setLayoutParams(lp);
//
//                                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();
//                                    params.height = 70;
//                                    tv.setLayoutParams(params);
//                                    tv.setGravity(Gravity.CENTER);
//
////                                    tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
////                                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
//
////                                    tv.setText(plantsList.get(position));
//
//                                    if(tv.getText().equals("音樂")) {
//                                        tv.setBackgroundColor(Color.parseColor("#FF82DE81"));
//                                    }
//                                    else if(tv.getText().equals("語文")) {
//                                        tv.setBackgroundColor(Color.parseColor("#FFffcc00"));
//                                    }
//                                    else if(tv.getText().equals("美語")) {
//                                        tv.setBackgroundColor(Color.parseColor("#FF66ccff"));
//                                    }
//                                    else if (tv.getText().equals("數學")) {
//                                        tv.setBackgroundColor(Color.parseColor("#FFffcccc"));
//                                    }
//                                    else{}
//
//
//
//                                    // Display TextView text in center position
//                                    return tv;
//                                }
//                            });
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(),"Selected Item : " + selectedItem,Toast.LENGTH_SHORT).show();
//            }
//        });



//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, numbers);
//        for(String a:numbers){
//            if(a.equals("A11兒兒")){
//                ((TextView) view).setBackgroundColor(Color.BLUE);
//            }
//        }
//
//        gridView.setAdapter(adapter);

//        List<Map<String, Object>> items = new ArrayList<>();
//        for (int i = 0; i < numbers.length; i++) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("text", numbers[i]);
//            items.add(item);
//        }
//
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
//                items, R.layout.curriculum_content_layout, new String[]{"text"},
//                new int[]{R.id.curriculum_subject_item});
//
//        gridView.setNumColumns(5);
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(getActivity(),
//                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    public static int getPixelsFromDPs(Activity activity, int dps){
        Resources r = activity.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }

}