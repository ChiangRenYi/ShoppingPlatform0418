package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingFinialFragmentCode;
import com.example.wmnl_yo.shoppingplatform.object.ShoppingCarObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingFinialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingFinialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingFinialFragment extends Fragment implements View.OnTouchListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageView sp_finial_code1, sp_finial_code2, sp_finial_code3;
    private TextView sp_finial_person_text, sp_finial_money_text, sp_finial_code_text1, sp_finial_code_text2, sp_finial_code_text3,sp_code_number,sp_code_date;
    public static String __text1,__text2,__text3,sp_date,sp_money;
    private Button shopping_CODE_check;
    ProgressDialog progressDoalog;
    public static String sp_code_number_data = "",get_page_way = "0";

    public ShoppingFinialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingFinialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingFinialFragment newInstance(String param1, String param2) {
        ShoppingFinialFragment fragment = new ShoppingFinialFragment();
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
        View v = inflater.inflate(R.layout.fragment_shopping_finial, container, false);
        v.setOnTouchListener(this);
        sp_finial_code1 = (ImageView) v.findViewById(R.id.sp_finial_code1);
        sp_finial_code2 = (ImageView) v.findViewById(R.id.sp_finial_code2);
        sp_finial_code3 = (ImageView) v.findViewById(R.id.sp_finial_code3);
        sp_finial_person_text = (TextView) v.findViewById(R.id.sp_finial_person_text);
        sp_finial_money_text = (TextView) v.findViewById(R.id.sp_finial_money_text);
        sp_finial_code_text1 = (TextView) v.findViewById(R.id.sp_finial_code_text1);
        sp_finial_code_text2 = (TextView) v.findViewById(R.id.sp_finial_code_text2);
        sp_finial_code_text3 = (TextView) v.findViewById(R.id.sp_finial_code_text3);
        shopping_CODE_check = (Button) v.findViewById(R.id.shopping_CODE_check);
        sp_code_number = (TextView)v.findViewById(R.id.sp_code_number);
        sp_code_date = (TextView)v.findViewById(R.id.sp_code_date);


        GetShoppingFinialFragmentCode getShoppingFinialFragmentCode = new GetShoppingFinialFragmentCode();
        getShoppingFinialFragmentCode.execute();
        Toast.makeText(getActivity(),"請稍後...",Toast.LENGTH_SHORT);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                sp_finial_person_text.setText(Constants.ACCOUNT.trim());
                sp_finial_money_text.setText(sp_money);
                sp_code_number.setText(String.format("%06d", Long.parseLong(sp_code_number_data)));
                sp_code_date.setText(sp_date);
                sp_finial_code_text1.setText(__text1);
                sp_finial_code_text2.setText(__text2);
                sp_finial_code_text3.setText(__text3);

                try {
                    sp_finial_code1.setImageBitmap(encodeAsBitmap(__text1, BarcodeFormat.CODE_39, 900, 200));
                    sp_finial_code2.setImageBitmap(encodeAsBitmap(__text2, BarcodeFormat.CODE_39, 900, 200));
                    sp_finial_code3.setImageBitmap(encodeAsBitmap(__text3, BarcodeFormat.CODE_39, 900, 200));
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        },500);


        shopping_CODE_check.setOnClickListener(new View.OnClickListener() {
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
                                ShoppingCarObject.ITEMS.clear();
                                ShoppingListFragment.shoppingAdapter.notifyDataSetChanged();
                                ((MainActivity) getContext()).replaceFragment(MainFragmentStudent.class, null);

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

    public static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int desiredWidth, int desiredHeight) throws WriterException {
        if (contents.length() == 0) return null;
        final int WHITE = 0xFFFFFFFF;
        final int BLACK = 0xFF000000;
        HashMap<EncodeHintType, String> hints = null;
        String encoding = null;
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                encoding = "UTF-8";
                break;
            }
        }
        if (encoding != null) {
            hints = new HashMap<>(2);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contents, format, desiredWidth, desiredHeight, hints);
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
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
