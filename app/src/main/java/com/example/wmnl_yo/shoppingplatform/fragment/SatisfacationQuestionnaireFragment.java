package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetSatisfacationQuestionFragmentResult;
import com.example.wmnl_yo.shoppingplatform.database.SendSatisfacationQuestionPoint;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationQuestionObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SatisfacationQuestionnaireFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SatisfacationQuestionnaireFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SatisfacationQuestionnaireFragment extends Fragment implements View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<SatisfacationQuestionObject.SatisfacationQuestionObjectItem> mQuestionRecordList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static MyAdapter rAdapter;
    public static String questionID="";
    public  static String questionIDReason="";
    public static String totalQuestionPoint="";
    public static String totalQuestionID="";
    public static String suggestion="";
    private RecyclerView mRecyclerView;
    private EditText totalResponse;
    private Button btnConfirm;
    private OnFragmentInteractionListener mListener;
    public static int amountOfQ;
    public static String[] reasonArray;
    ArrayList listSelected = new ArrayList();
    public SatisfacationQuestionnaireFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SatisfacationQuestionnaireFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SatisfacationQuestionnaireFragment newInstance(String param1, String param2) {
        SatisfacationQuestionnaireFragment fragment = new SatisfacationQuestionnaireFragment();
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
        Log.d("andyQuestionnaire","success");

        Bundle bundle = getArguments();

        questionID=bundle.getString("questionNaire");
        Log.d("andygetquestionID",questionID);

        GetSatisfacationQuestionFragmentResult GetSatisfacationQuestionFragmentResult = new GetSatisfacationQuestionFragmentResult();
        GetSatisfacationQuestionFragmentResult.execute();


//        btnConfirm.setOnClickListener(new View.OnClickListener() { //andytemp Onclick
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_satisfacation_questionnaire, container, false);
        ((MainActivity) getActivity()).setSubTitle(" > 問卷");
        v.setOnTouchListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);
        totalResponse=(EditText)v.findViewById(R.id.totalResponse);
        btnConfirm=(Button)v.findViewById(R.id.confirm);
        totalResponse.getBackground().mutate().setColorFilter(getResources().getColor(R.color.cardview_dark_background), PorterDuff.Mode.SRC_ATOP);

        rAdapter = new MyAdapter(SatisfacationQuestionObject.ITEMS);//()內填入需顯示的小孩資訊


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(rAdapter);

        mRecyclerView.addItemDecoration(dividerItemDecoration);

//        btnConfirm.setOnClickListener(new View.OnClickListener() {   //andytemp OnClick
//            @Override
//            public void onClick(View view) {
////                Log.d("andyOnClick","work");
//
//            }
//        });


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


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<SatisfacationQuestionObject.SatisfacationQuestionObjectItem> mQuestionRecordList;



        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout ll;
            public TextView tvQuestion,tvReason;
            public  SatisfacationQuestionObject.SatisfacationQuestionObjectItem   mItem;
//            public RadioButton rbSuperGood,rbGood,rbNormal,rbBad,rbVerybad;
            public Button questionResponse;

            public Button btnSuperGood,btnGood,btnNormal,btnBad,btnVeryBad;

//            public RadioGroup rg;

            public ViewHolder(View v) {
                super(v);
                ll = (LinearLayout) v.findViewById(R.id.ll);

                tvQuestion = (TextView) v.findViewById(R.id.tvQuestion);

                btnSuperGood=(Button)v.findViewById(R.id.btnSuperGood);
                btnGood=(Button)v.findViewById(R.id.btnGood);
                btnNormal=(Button)v.findViewById(R.id.btnNormal);
                btnBad=(Button)v.findViewById(R.id.btnBad);
                btnVeryBad=(Button)v.findViewById(R.id.btnVeryBad);




//                rbSuperGood = (RadioButton) v.findViewById(R.id.rbSuperGood);/////////andytemp for declare radiobutton
//                rbGood = (RadioButton) v.findViewById(R.id.rbGood);
//                rbNormal = (RadioButton) v.findViewById(R.id.rbNormal);
//                rbBad = (RadioButton) v.findViewById(R.id.rbBad);
//                rbVerybad = (RadioButton) v.findViewById(R.id.rbVerybad);

                tvReason=(TextView)v.findViewById(R.id.tvReason);

                questionResponse=(Button) v.findViewById(R.id.questionResponse);


//                rg=(RadioGroup)v.findViewById(R.id.rg); ////////andytemp for declare radioGroup

//                if(rbSuperGood.isChecked())  //andytemp for visible
//                {
//                    tvReason.setVisibility(View.VISIBLE);
//                    questionResponse.setVisibility(View.VISIBLE);
//                }

            }
        }

        public MyAdapter(List<SatisfacationQuestionObject.SatisfacationQuestionObjectItem> satisfacationQuestionList) {
            mQuestionRecordList = satisfacationQuestionList;

        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_recyclerview_satisfacation_question_query_result, parent, false);
            MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);

            return vh;
        }


        Dictionary selected = new Hashtable();
//        Dictionary questionIndex = new Hashtable();
        Dictionary questionReason = new Hashtable();
        public void reset(final ViewHolder holder){
            holder.btnSuperGood.setBackgroundColor(Color.WHITE);
//            holder.btnSuperGood.setBackgroundResource(R.drawable.btn_background);//andytemp drawable
            holder.btnSuperGood.setTextColor(Color.BLACK);
            holder.btnGood.setBackgroundColor(Color.WHITE);
            holder.btnGood.setTextColor(Color.BLACK);
            holder.btnNormal.setBackgroundColor(Color.WHITE);
            holder.btnNormal.setTextColor(Color.BLACK);
            holder.btnBad.setBackgroundColor(Color.WHITE);
            holder.btnBad.setTextColor(Color.BLACK);
            holder.btnVeryBad.setBackgroundColor(Color.WHITE);
            holder.btnVeryBad.setTextColor(Color.BLACK);
            holder.tvReason.setVisibility(View.GONE);
            holder.questionResponse.setVisibility(View.GONE);
            holder.questionResponse.setText("請點擊填寫");
//            holder.questionResponse.setText("");


        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            holder.mItem = mQuestionRecordList.get(position);
            Log.d("andyOnBindView", mQuestionRecordList.get(position).rQuestion);
            Log.d("QuestionID", mQuestionRecordList.get(position).rQuestionID);
            holder.tvQuestion.setText(mQuestionRecordList.get(position).rQuestion);

            Log.d("ajosdfjoposition:", String.valueOf(position));


            Log.d("ajosdfjo:", String.valueOf(selected.get(position)));
            Log.d("ajosdfjoposition:", String.valueOf(selected.elements()));

//            mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//            Log.d("andyreasonOutput",holder.questionResponse.getText().toString());

            /////////////在畫面滑動時 知道是每一行是哪一個按鍵被按的狀態，再把被按得鍵重新reset在控制為正常的應該要顯示的狀態
//            holder.btnReasonConfirm.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mQuestionRecordList.get(position).rQuestionReason=holder.questionResponse.getText().toString();
//                    Log.d("iiiiiii",String.valueOf(position));
//                    Log.d("iiiiiii",holder.questionResponse.getText().toString());
//                }
//            });
            if(selected.get(position)==null)
            {
                holder.btnSuperGood.setBackgroundColor(Color.WHITE);
                holder.btnSuperGood.setTextColor(Color.BLACK);
                holder.btnGood.setBackgroundColor(Color.WHITE);
                holder.btnGood.setTextColor(Color.BLACK);
                holder.btnNormal.setBackgroundColor(Color.WHITE);
                holder.btnNormal.setTextColor(Color.BLACK);
                holder.btnBad.setBackgroundColor(Color.WHITE);
                holder.btnBad.setTextColor(Color.BLACK);
                holder.btnVeryBad.setBackgroundColor(Color.WHITE);
                holder.btnVeryBad.setTextColor(Color.BLACK);
                holder.tvReason.setVisibility(View.GONE);
                holder.questionResponse.setVisibility(View.GONE);
                holder.questionResponse.setText("請點擊填寫");

//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);
                Log.d("ajosdfjoposition:", "我有跑空哦");
            }
            if(selected.get(position)== "5") {
                reset(holder);

//                holder.btnSuperGood.setBackgroundColor(Color.parseColor("#51cdf0"));
                holder.btnSuperGood.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                holder.btnSuperGood.setTextColor(Color.WHITE);
                holder.tvReason.setVisibility(View.GONE);
                holder.questionResponse.setVisibility(View.GONE);
                holder.questionResponse.setText("請點擊填寫");

                Log.d("ajosdfjoposition:", "我有跑五哦");
//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);


//                        String questionIndex="Q"+i;
//                        try {
//                            jsonObject.put(questionIndex,mQuestionRecordList.get(i).rQuestionReason);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.d("JsonFormat",jsonObject.toString());








            }
            if(selected.get(position)== "4")
            {
                reset(holder);

//                holder.btnGood.setBackgroundColor(Color.parseColor("#51cdf0"));
                holder.btnGood.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                holder.btnGood.setTextColor(Color.WHITE);
                holder.tvReason.setVisibility(View.GONE);
                holder.questionResponse.setVisibility(View.GONE);
                holder.questionResponse.setText("請點擊填寫");

//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);


            }

            if(selected.get(position)== "3")
            {
                reset(holder);

//                holder.btnNormal.setBackgroundColor(Color.parseColor("#51cdf0"));
                holder.btnNormal.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                holder.btnNormal.setTextColor(Color.WHITE);
                holder.questionResponse.setText("請點擊填寫");

//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);


            }
            if(selected.get(position)== "2")
            {
                reset(holder);

//                holder.btnBad.setBackgroundColor(Color.parseColor("#51cdf0"));
                holder.btnBad.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                holder.btnBad.setTextColor(Color.WHITE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.questionResponse.setVisibility(View.VISIBLE);
                holder.questionResponse.setText(String.valueOf(questionReason.get(position)));
//                Log.d("andynull",String.valueOf(position));
//
//                holder.questionResponse.setText(reasonArray[position]); //andyTODO
//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);


            }
            if(selected.get(position)== "1")
            {
                reset(holder);

//                holder.btnVeryBad.setBackgroundColor(Color.parseColor("#51cdf0"));
                holder.btnVeryBad.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                holder.btnVeryBad.setTextColor(Color.WHITE);
                holder.tvReason.setVisibility(View.VISIBLE);
                holder.questionResponse.setVisibility(View.VISIBLE);
                holder.questionResponse.setText(String.valueOf(questionReason.get(position)));

//                Log.d("andynull",String.valueOf(position));
//                holder.questionResponse.setText(reasonArray[position]); //andyTODO

//                mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
//                holder.questionResponse.setText(mQuestionRecordList.get(position).rQuestionReason);



            }
//            if(questionIndex.get(position)==String.valueOf(position))
//            {
//                Log.d("reason",String.valueOf(position));
//                if(selected.get(position)=="2"||selected.get(position)=="1")
//                {
//                    holder.questionResponse.setText(reasonArray[position]);
//
//                }
//            }

            holder.btnSuperGood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reset(holder);
                    selected.put(position,"5");
//                    questionIndex.put(position,String.valueOf(position)); //andytemp
//                    holder.btnSuperGood.setBackgroundColor(Color.parseColor("#51cdf0"));
                    holder.btnSuperGood.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                    holder.btnSuperGood.setTextColor(Color.WHITE);
                    holder.tvReason.setVisibility(View.GONE);
                    holder.questionResponse.setVisibility(View.GONE);
                    mQuestionRecordList.get(position).rQuestionPoint="5";
                    Log.d("andyQuestionPointButton",mQuestionRecordList.get(position).rQuestionPoint);

                }
            });
            holder.btnGood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reset(holder);
                    selected.put(position,"4");
//                    questionIndex.put(position,String.valueOf(position)); //andytemp


//                    holder.btnGood.setBackgroundColor(Color.parseColor("#51cdf0"));
                    holder.btnGood.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                    holder.btnGood.setTextColor(Color.WHITE);
                    holder.tvReason.setVisibility(View.GONE);
                    holder.questionResponse.setVisibility(View.GONE);
                    mQuestionRecordList.get(position).rQuestionPoint="4";
                    Log.d("andyQuestionPointButton",mQuestionRecordList.get(position).rQuestionPoint);

                }
            });
            holder.btnNormal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected.put(position,"3");
//                    questionIndex.put(position,String.valueOf(position)); //andytemp

                    reset(holder);
//                    holder.btnNormal.setBackgroundColor(Color.parseColor("#51cdf0"));
                    holder.btnNormal.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                    holder.btnNormal.setTextColor(Color.WHITE);
                    holder.tvReason.setVisibility(View.GONE);
                    holder.questionResponse.setVisibility(View.GONE);
                    mQuestionRecordList.get(position).rQuestionPoint="3";
                    Log.d("andyQuestionPointButton",mQuestionRecordList.get(position).rQuestionPoint);
                }
            });
            holder.btnBad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected.put(position,"2");
//                    questionIndex.put(position,String.valueOf(position)); //andytemp

                    reset(holder);
//                    holder.btnBad.setBackgroundColor(Color.parseColor("#51cdf0"));
                    holder.btnBad.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                    holder.btnBad.setTextColor(Color.WHITE);

                    holder.tvReason.setVisibility(View.VISIBLE);
                    holder.questionResponse.setVisibility(View.VISIBLE);
                    mQuestionRecordList.get(position).rQuestionPoint="2";
                    Log.d("andyQuestionPointButton",mQuestionRecordList.get(position).rQuestionPoint);

//                    mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();  //andytemp for bad reason test
//                    Log.d("rQuestionReason",holder.questionResponse.getText().toString());
                }
            });
            holder.btnVeryBad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selected.put(position,"1");
//                    questionIndex.put(position,String.valueOf(position)); //andytemp

                    reset(holder);
//                    holder.btnVeryBad.setBackgroundColor(Color.parseColor("#51cdf0"));
                    holder.btnVeryBad.setBackgroundResource(R.drawable.btn_background);//andytemp drawable

                    holder.btnVeryBad.setTextColor(Color.WHITE);
                    holder.tvReason.setVisibility(View.VISIBLE);
                    holder.questionResponse.setVisibility(View.VISIBLE);
                    mQuestionRecordList.get(position).rQuestionPoint="1";
                    Log.d("andyQuestionPointButton",mQuestionRecordList.get(position).rQuestionPoint);




//                    mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString(); //andytemp for VeryBad reason test
//                    Log.d("rQuestionReason",holder.questionResponse.getText().toString());
                }
            });

            holder.questionResponse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
//                    alertDialog.setTitle("Alert");
//                    alertDialog.setMessage("Alert message to be shown");
//
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    alertDialog.show();

                    final View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_reason,null);
                    final EditText reason;
                    reason = (EditText) mView.findViewById(R.id.edtReason);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext())
                            .setTitle("原因")
                            .setView(mView)

                            .setPositiveButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(reason.getText().length()==0)
                                    {
                                        mQuestionRecordList.get(position).rQuestionReason ="";
                                        Log.d("andyposition",mQuestionRecordList.get(position).rQuestionReason);
                                        Log.d("andyposition",String.valueOf(position));
                                        Log.d("GGGG","empty");
                                    }
                                    else
                                    {
                                        mQuestionRecordList.get(position).rQuestionReason=reason.getText().toString();
                                        Log.d("andyposition",mQuestionRecordList.get(position).rQuestionReason);
                                        Log.d("andyposition",String.valueOf(position));

                                        Log.d("GGGG",reason.getText().toString());
                                        holder.questionResponse.setText(reason.getText().toString());
                                        questionReason.put(position,reason.getText().toString());
                                    }
                                    Log.d("andyDialog",reason.getText().toString());



                                }
                            });
                    alertDialog.show();


//                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    };


                }
            });
//            Log.d("ajosdfjo:", String.valueOf(selected.get(position)));      //andytemp
//            Log.d("ajosdfjoposition:", String.valueOf(selected.elements()));

//            if(selected.get(position)==null)
//            {
//                holder.rbSuperGood.setChecked(false);
//                holder.rbGood.setChecked(false);
//                holder.rbNormal.setChecked(false);
//                holder.rbBad.setChecked(false);
//                holder.rbVerybad.setChecked(false);
//                Log.d("ajosdfjoposition:", "我有跑空哦");
//            }
//            if(selected.get(position)== "5") {
//                holder.rbSuperGood.setChecked(true);
//                Log.d("ajosdfjoposition:", "我有跑五哦");
//            }
//            if(selected.get(position)== "4")
//                holder.rbGood.setChecked(true);
//            if(selected.get(position)== "3")
//                holder.rbNormal.setChecked(true);
//            if(selected.get(position)== "2")
//                holder.rbBad.setChecked(true);
//            if(selected.get(position)== "1")
//                holder.rbVerybad.setChecked(true);
//            holder.rbSuperGood.setChecked(true);




            /////////////use setOnCheckedChangeListener to check the status for the Radiobutton in the RadioGroup

//            holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {   //////////andytemp for radioGroup and radioButton
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//
////                    holder.rbSuperGood.setChecked(false);
////                    holder.rbGood.setChecked(false);
////                    holder.rbNormal.setChecked(false);
////                    holder.rbBad.setChecked(false);
////                    holder.rbVerybad.setChecked(false);
////                    if(!(listSelected.contains(position)))
////                         listSelected.add(position);
//
//                    ////////////////////////////////////////////////andytemp bug TODO
//                    if(holder.rbSuperGood.isChecked())
//                    {
//
////                        selected.put(position,"5");
////                        Log.d("ajosdfjo----:", String.valueOf(selected.get(position)));
//                        holder.tvReason.setVisibility(View.GONE);
//                        holder.questionResponse.setVisibility(View.GONE);
//                        mQuestionRecordList.get(position).rQuestionPoint="5";
//
//                        Log.d("andyQuestionPoint",mQuestionRecordList.get(position).rQuestionPoint);
//
//
//                    }
//                    else if(holder.rbGood.isChecked())
//                    {
////                        selected.put(position,"4");
//                        holder.tvReason.setVisibility(View.GONE);
//                        holder.questionResponse.setVisibility(View.GONE);
//                        mQuestionRecordList.get(position).rQuestionPoint="4";
//                        Log.d("andyQuestionPoint",mQuestionRecordList.get(position).rQuestionPoint);
//
//                    }
//                    else if(holder.rbNormal.isChecked())
//                    {
////                        selected.put(position,"3");
//                        holder.tvReason.setVisibility(View.GONE);
//                        holder.questionResponse.setVisibility(View.GONE);
//                        mQuestionRecordList.get(position).rQuestionPoint="3";
//                        Log.d("andyQuestionPoint",mQuestionRecordList.get(position).rQuestionPoint);
//
//                    }
//                    else if(holder.rbBad.isChecked())
//                    {
////                        selected.put(position,"2");
//                        holder.tvReason.setVisibility(View.VISIBLE);
//                        holder.questionResponse.setVisibility(View.VISIBLE);
//                        mQuestionRecordList.get(position).rQuestionPoint="2";
//                        Log.d("andyQuestionPoint",mQuestionRecordList.get(position).rQuestionPoint);
////                        mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
////                        Log.d("rQuestionReason",holder.questionResponse.getText().toString());
//                    }
//                    else if(holder.rbVerybad.isChecked())
//                    {
////                        selected.put(position,"1");
//                        holder.tvReason.setVisibility(View.VISIBLE);
//                        holder.questionResponse.setVisibility(View.VISIBLE);
//                        mQuestionRecordList.get(position).rQuestionPoint="1";
//                        Log.d("andyQuestionPoint",mQuestionRecordList.get(position).rQuestionPoint);
////                        mQuestionRecordList.get(position).rQuestionReason = holder.questionResponse.getText().toString();
////                        Log.d("rQuestionReason",holder.questionResponse.getText().toString());
//
//                    }
//////////////////////////////////////////////////////////////////////
//                }
//            });



            btnConfirm.setOnClickListener(new View.OnClickListener() {
            //andytemp OnClick
            @Override
            public void onClick(View view) {
//                Log.d("andyOnClick","work");
                ////////////////////////////////////////////////////deal with QuestionID and QuestionPoint by format like"1,2,3,4,5,6,7,8,9,10"
                amountOfQ =mQuestionRecordList.size();
                totalQuestionID="";
                totalQuestionPoint="";
                for(int i=0;i<amountOfQ;i++) {
                    Log.d("andyOnClick", mQuestionRecordList.get(i).rQuestionPoint);
                    if (totalQuestionPoint == ""){
                        totalQuestionPoint = mQuestionRecordList.get(i).rQuestionPoint;
                    }
                    else {
                        totalQuestionPoint = totalQuestionPoint + "," + mQuestionRecordList.get(i).rQuestionPoint ;
                    }
                }
                Log.d("andytotalPoint",totalQuestionPoint);
                for(int i=0;i<amountOfQ;i++)
                {
                    Log.d("andyQuestionID", mQuestionRecordList.get(i).rQuestionID);

                    if(totalQuestionID=="")
                    {
                        totalQuestionID=mQuestionRecordList.get(i).rQuestionID;
                    }
                    else
                    {
                        totalQuestionID=totalQuestionID+","+mQuestionRecordList.get(i).rQuestionID;
                    }

                }
                Log.d("andyQuestionID",totalQuestionID);
                suggestion = totalResponse.getText().toString();
                Log.d("suggestion",totalResponse.getText().toString());
                ///////////////////////////////////////
//                JSONObject jsonObject=new JSONObject();


//                for(int i=0;i<=position;i++) {
//                    if (mQuestionRecordList.get(i).rQuestionPoint == "1" || mQuestionRecordList.get(i).rQuestionPoint =="2"){
//                        mQuestionRecordList.get(i).rQuestionReason = holder.questionResponse.getText().toString();
//                        Log.d("rQuestionReason",mQuestionRecordList.get(i).rQuestionReason);
//
//
////                        String questionIndex="Q"+i;
////                        try {
////                            jsonObject.put(questionIndex,mQuestionRecordList.get(i).rQuestionReason);
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                        Log.d("JsonFormat",jsonObject.toString());
//
//                    }
//
//                }
//                for(int i=0;i<amountOfQ;i++)  //amdytemp for look every reason
//                {
//                    Log.d("andyreason",mQuestionRecordList.get(i).rQuestionReason);
//
//                }
                /////////andytemp null array ,fail

                reasonArray = new String[amountOfQ];

                for(int i =0;i<amountOfQ;i++)
                {
                    reasonArray[i]="";
                    if(mQuestionRecordList.get(i).rQuestionReason==null)
                    {
                        reasonArray[i]="";
                        Log.d("andyReason","null");
                    }
                    else
                    {
                        reasonArray[i]=mQuestionRecordList.get(i).rQuestionReason;
                        Log.d("andyReason",reasonArray[i]);

                    }
                }

                ////////////////andytemp for check point
                int reasonflag=1;
                for(int i = 0;i<amountOfQ;i++)
                {
                    if(mQuestionRecordList.get(i).rQuestionPoint=="5" || mQuestionRecordList.get(i).rQuestionPoint=="4" ||mQuestionRecordList.get(i).rQuestionPoint=="3" ||mQuestionRecordList.get(i).rQuestionPoint=="2" ||mQuestionRecordList.get(i).rQuestionPoint=="1" )
                    {

                    }
                    else
                    {
                        reasonflag=0;
                    }
                }
                if(reasonflag==1)
                {
                    SendSatisfacationQuestionPoint sendSatisfacationQuestionPoint=new SendSatisfacationQuestionPoint();
                    sendSatisfacationQuestionPoint.execute();
                    Bundle bundle = new Bundle();
                    bundle.putString("childID", SatisfactionSurveyFragment.childID.trim());
//                    Log.d("andysendchildID",childList.get(position));    //get the position for child name
                    SatisfactionSurveyFragment fragobj = new SatisfactionSurveyFragment();
                    fragobj.setArguments(bundle);
                    SatisfacationQuestionnaireFragment satisfacationQuestionnaireFragment =new SatisfacationQuestionnaireFragment();
                    FragmentManager fm = getActivity()
                            .getSupportFragmentManager();
                    fm.popBackStack();

//                    fm.putFragment(bundle,SatisfactionSurveyFragment.childID.trim(),fragobj);  //bughere

//                    ((MainActivity) getContext()).replaceFragment(SatisfactionSurveyFragment.class, fragobj);//andytemp replacefragment old way

//                    getActivity().getFragmentManager().beginTransaction().remove(view.this).commit(); //andytemp

                    Toast.makeText(getActivity(),"問卷提交完成",Toast.LENGTH_LONG).show();
                }
                else if(reasonflag==0)
                {
                    Toast.makeText(getActivity(),"問卷尚未填寫完畢",Toast.LENGTH_LONG).show();
                }



            }
        });





/////////////////////andytemp for linearout button  (not necessarily)
//            holder.ll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("childID", mQuestionRecordList.get(position).rChild_ID);
//                    SatisfactionSurveyFragment fragobj = new SatisfactionSurveyFragment();
//                    fragobj.setArguments(bundle);
//                    ((MainActivity) getContext()).replaceFragment(SatisfactionSurveyFragment.class, fragobj);
//                }
//            });




        }

        @Override
        public int getItemCount() {
            return mQuestionRecordList.size();
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
}
