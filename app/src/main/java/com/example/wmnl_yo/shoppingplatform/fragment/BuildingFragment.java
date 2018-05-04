package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetBuildingDetail;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingFragment extends Fragment implements View.OnTouchListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String t;
    private TextView[] tv;
    private View v;
    private String[] tmp;
    private int choose;
    public  String buildingcountry,buildingcity;
    public static String bcountry,bcity,btype,buildingType,connectbuilding;

    private OnFragmentInteractionListener mListener;

    public BuildingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseQueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingFragment newInstance(String param1, String param2) {
        BuildingFragment fragment = new BuildingFragment();
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
        tv = new TextView[getResources().getStringArray(R.array.courseFindType).length];
        tmp = new String[]{"請選擇", "請選擇", "請選擇"};
       // GetCourseQueryFragmentTeacherClass getmTeacherClassData = new GetCourseQueryFragmentTeacherClass();
      //  getmTeacherClassData.execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_building, null);
            ((MainActivity) getActivity()).setSubTitle(" > 機構查詢");
            v.setOnTouchListener(this);

            ListView lvCourse = (ListView) v.findViewById(R.id.lCourse);

            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.custom_listview_order) {
                @Override
                public int getCount() {
                    return getContext().getResources().getStringArray(R.array.buildingSearch).length;
                }

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    ViewHolder viewHolder = new ViewHolder();
                    if (convertView == null) {
                        Log.e("55123", "" + position);
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview_order, null);
                        viewHolder.tvFindType = (TextView) convertView.findViewById(R.id.textView);
                        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.textView2);
                        tv[position] = viewHolder.tvItem;
                        convertView.setTag(viewHolder);
                    } else {
                        viewHolder = (ViewHolder) convertView.getTag();
                        tv[position] = viewHolder.tvItem;
                    }

                    viewHolder.tvFindType.setText(getContext().getResources().getStringArray(R.array.buildingSearch)[position]);
                    viewHolder.tvItem.setText(tmp[position]);

                    return convertView;
                }
            };
            lvCourse.setAdapter(arrayAdapter);
            lvCourse.setOnItemClickListener(this);

            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_header_view_search, null);
            View footerView = LayoutInflater.from(getContext()).inflate(R.layout.custom_footer_view, null);
            Button btnCourseQuery = (Button) footerView.findViewById(R.id.btnOrderSearch);

            btnCourseQuery.setText("搜尋親子館");
            lvCourse.addHeaderView(headerView);
            lvCourse.addFooterView(footerView);

            btnCourseQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(bcountry==null||bcity==null||btype==null)
                    {
                        Toast.makeText(view.getContext(), "有選項沒選，請確認!!!", Toast.LENGTH_SHORT).show();
                    }else {
                        final Handler handler = new Handler();
                        GetBuildingDetail getBuildingDetail= new GetBuildingDetail();
                        getBuildingDetail.execute();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    switch (connectbuilding)
                                    {
                                        case "nothing" :
                                            Toast.makeText(getContext(), "此地區無親子館!!!", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "check":
                                            ((MainActivity) getContext()).replaceFragment(BuildingDetailFragment.class, null);
                                            break;
                                        default:
                                            break;
                                    }
                                }catch (Exception e){
                                    Toast.makeText(getContext(),"請檢查網路連線訊號",Toast.LENGTH_SHORT).show();
                                    ((MainActivity) getContext()).replaceFragment(BuildingFragment.class, null);
                                }

                            }
                        },600);

                    }
                }
            });
        }
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // i = 0 是 header view
        if (i == 1) {
            numberPicker(getResources().getStringArray(R.array.courseFindTypeCountyCity), i - 1);
            t = String.valueOf(i);
        } else if (i == 2) {
            if (buildingcountry == null) {
                Toast.makeText(view.getContext(), "請選擇親子館縣市!!!", Toast.LENGTH_SHORT).show();
            } else {
                switch (buildingcountry) {
                    case "不限":
                        break;
                    case "基隆市":
                        numberPicker(getResources().getStringArray(R.array.基隆市), i - 1);
                        break;
                    case "臺北市":
                        numberPicker(getResources().getStringArray(R.array.臺北市), i - 1);
                        break;
                    case "新北市":
                        numberPicker(getResources().getStringArray(R.array.新北市), i - 1);
                        break;
                    case "桃園市":
                        numberPicker(getResources().getStringArray(R.array.桃園市), i - 1);
                        break;
                    case "新竹市":
                        numberPicker(getResources().getStringArray(R.array.新竹市), i - 1);
                        break;
                    case "新竹縣":
                        numberPicker(getResources().getStringArray(R.array.新竹縣), i - 1);
                        break;
                    case "苗栗縣":
                        numberPicker(getResources().getStringArray(R.array.苗栗縣), i - 1);
                        break;
                    case "臺中市":
                        numberPicker(getResources().getStringArray(R.array.臺中市), i - 1);
                        break;
                    case "彰化縣":
                        numberPicker(getResources().getStringArray(R.array.彰化縣), i - 1);
                        break;
                    case "南投縣":
                        numberPicker(getResources().getStringArray(R.array.南投縣), i - 1);
                        break;
                    case "雲林縣":
                        numberPicker(getResources().getStringArray(R.array.雲林縣), i - 1);
                        break;
                    case "嘉義市":
                        numberPicker(getResources().getStringArray(R.array.嘉義市), i - 1);
                        break;
                    case "嘉義縣":
                        numberPicker(getResources().getStringArray(R.array.嘉義縣), i - 1);
                        break;
                    case "臺南市":
                        numberPicker(getResources().getStringArray(R.array.臺南市), i - 1);
                        break;
                    case "高雄市":
                        numberPicker(getResources().getStringArray(R.array.高雄市), i - 1);
                        break;
                    case "屏東縣":
                        numberPicker(getResources().getStringArray(R.array.屏東縣), i - 1);
                        break;
                    case "臺東縣":
                        numberPicker(getResources().getStringArray(R.array.臺東縣), i - 1);
                        break;
                    case "花蓮縣":
                        numberPicker(getResources().getStringArray(R.array.花蓮縣), i - 1);
                        break;
                    case "宜蘭縣":
                        numberPicker(getResources().getStringArray(R.array.宜蘭縣), i - 1);
                        break;
                    case "澎湖縣":
                        numberPicker(getResources().getStringArray(R.array.澎湖縣), i - 1);
                        break;
                    case "金門縣":
                        numberPicker(getResources().getStringArray(R.array.金門縣), i - 1);
                        break;
                    case "連江縣":
                        numberPicker(getResources().getStringArray(R.array.連江縣), i - 1);
                        break;
                    default:
                        break;
                }
            }
            t = String.valueOf(i);
        } else if (i == 3) {
            numberPicker(getResources().getStringArray(R.array.buildingSearchType), i - 1);
            t = String.valueOf(i);
        }
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

    class ViewHolder {
        TextView tvFindType, tvItem;
    }

    public void numberPicker(String[] data, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_numberpicker, null);
        final Button btnSuccess = (Button) view.findViewById(R.id.btnSuccess);
        final NumberPickerView numberPickerView = (NumberPickerView) view.findViewById(R.id.picker);
        numberPickerView.setDisplayedValues(data);
        numberPickerView.setMaxValue(data.length - 1);
        numberPickerView.setMinValue(0);

        final AlertDialog ad = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .show();

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();
                tv[position].setText(numberPickerView.getContentByCurrValue());
                tmp[position] = numberPickerView.getContentByCurrValue();

                //縣市
                choose = Integer.valueOf(t).intValue();
                if(choose == 1)
                {
                    buildingcountry = numberPickerView.getContentByCurrValue();
                    bcountry = buildingcountry;
                    Log.e("55125",bcountry);
                }else if(choose == 2) {
                    buildingcity = numberPickerView.getContentByCurrValue();
                    bcity = buildingcity;
                    Log.e("55125",bcity);
                }else if(choose == 3) {
                    buildingType = numberPickerView.getContentByCurrValue();
                    switch (buildingType){
                        case "公立":
                            btype = "0";
                            break;
                        case "私立":
                            btype = "1";
                            break;
                        case "不限":
                            btype = "2";
                            break;
                        default:
                            break;
                    }
                    Log.e("55125",btype);
                }

            }
        });
    }
}
