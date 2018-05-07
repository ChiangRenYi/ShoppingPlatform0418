package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingFinialFragmentAtm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingCarATMFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingCarATMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCarATMFragment extends Fragment implements View.OnTouchListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView sp_money_text,sp_account,sp_data,sp_ATM_person_text,sp_ATM_number;
    public static String sp_ATM_money_text,sp_ATM_account,sp_ATM_data;
    private OnFragmentInteractionListener mListener;
    private Button shopping_ATM_check;
    ProgressDialog progressDoalog;
    public static String sp_atm_number_data = "",get_page_way = "0";
    public ShoppingCarATMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCarATMFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCarATMFragment newInstance(String param1, String param2) {
        ShoppingCarATMFragment fragment = new ShoppingCarATMFragment();
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

        View v = inflater.inflate(R.layout.fragment_shopping_car_atm, container, false);
        v.setOnTouchListener(this);


        shopping_ATM_check = (Button) v.findViewById(R.id.shopping_ATM_check);
        sp_money_text = (TextView) v.findViewById(R.id.sp_ATM_money_text);
        sp_account = (TextView) v.findViewById(R.id.sp_ATM_account);
        sp_data = (TextView) v.findViewById(R.id.sp_ATM_data);
        sp_ATM_person_text = (TextView) v.findViewById(R.id.sp_ATM_person_text);
        sp_ATM_number = (TextView) v.findViewById(R.id.sp_ATM_number);

        GetShoppingFinialFragmentAtm getShoppingFinialFragmentAtm = new GetShoppingFinialFragmentAtm();
        getShoppingFinialFragmentAtm.execute();
        Toast.makeText(getActivity(),"請稍後...",Toast.LENGTH_SHORT);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                sp_money_text.setText(sp_ATM_money_text);
                sp_account.setText(sp_ATM_account.substring(sp_ATM_account.length()-13,sp_ATM_account.length()));
                sp_data.setText(sp_ATM_data);
                sp_ATM_person_text.setText(Constants.ACCOUNT);
                sp_ATM_number.setText(String.format("%06d", Long.parseLong(sp_atm_number_data)));

            }
        },500);


        shopping_ATM_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (get_page_way){
                    case "0":
                        progressDoalog = new ProgressDialog(getActivity());
                        progressDoalog.setMessage("載入中，請稍後...");
                        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDoalog.setCancelable(false);
                        progressDoalog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                    progressDoalog.dismiss();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ((MainActivity) getContext()).replaceFragment_for_ShoppingCar(ShoppingListFragment.class, null);
                            }
                        },3000);

                        break;

                    case "1":
                        getActivity().onBackPressed();

                        break;
                    default:
                        break;
                }
            }
        });

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
}
