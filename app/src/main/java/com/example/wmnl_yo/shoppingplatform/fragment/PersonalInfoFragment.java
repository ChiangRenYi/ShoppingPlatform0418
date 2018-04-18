package com.example.wmnl_yo.shoppingplatform.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.CircleTransform;
import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.UpdatePersonalInfo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by WMNL-YO on 2017/2/22.
 */

public class PersonalInfoFragment extends Fragment {

    private String PIaccount, PIname, PIgender, PIcareer, PInationality, PIID, PIbirthday, PImail, PIaddress, PIcontact_phone, PIphone;
    public static String ChangeName, ChangeMail, ChangeAddress, ChangeContactPhone, ChangePhone;
    private TextView tvGender, tvCareer, tvNationality, tvID, tvBirthday;
    private EditText edName, edMail, edAddress, edContactPhone, edPhone;
    private View view;
    private ImageView imgProfilePic;

    private Button btnok;
    private String imagepath = null;
    private SharedPreferences preferences;
    private RelativeLayout layoutSetKinship;
    private String ProfilePicUrl = "";
    private Bitmap head;
    private ProgressDialog pd;
    private static String path = Environment.getExternalStorageDirectory().getPath()+"/QinZiHead/";
    private String Change = "andytest";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            preferences = getActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);
            preferences.edit().putString("PREFERENCE_PROFILE_PIC_URL", Constants.SERVER_URL + MainActivity.account + ".jpg").commit();
            PIaccount = preferences.getString("PREFERENCE_ACCOUNT", "");
            PIname = preferences.getString("PREFERENCE_NAME", "");
            PIgender = preferences.getString("PREFERENCE_GENDER", "");
            PInationality = preferences.getString("PREFERENCE_NATIONALITY", "");
            PIID = preferences.getString("PREFERENCE_ID", "");
            PIbirthday = preferences.getString("PREFERENCE_BIRTHDAY", "");
            PImail = preferences.getString("PREFERENCE_MAIL", "");
            PIaddress = preferences.getString("PREFERENCE_ADDRESS", "");
            PIcontact_phone = preferences.getString("PREFERENCE_CONTACT_PHONE", "");
            PIphone = preferences.getString("PREFERENCE_PHONE", "");
            PIcareer = preferences.getString("PREFERENCE_CAREER", "");

            Log.e("55555PI555", PIname + PIgender);


//            GetPersonalInfo getPersonalInfo = new GetPersonalInfo();
//            getPersonalInfo.execute();

            //Log.e("PI55555",PIname);


            view = inflater.inflate(R.layout.fragment_personal_info, container, false);
            btnok = (Button) view.findViewById(R.id.Button_PI_ok);
            imgProfilePic = (ImageView) view.findViewById(R.id.ImageView_profile_pic);
            layoutSetKinship = (RelativeLayout) view.findViewById(R.id.layout_set_kinship);

            edName = (EditText) view.findViewById(R.id.editText_PI_username);
            tvGender = (TextView) view.findViewById(R.id.textView_PI_gender_show);
            tvNationality = (TextView) view.findViewById(R.id.textView_PI_nationality_show);
            tvID = (TextView) view.findViewById(R.id.textView_PI_ID_show);
            tvBirthday = (TextView) view.findViewById(R.id.textView_PI_birthday_show);
            tvCareer = (TextView) view.findViewById(R.id.textView_PI_career_show);
            edMail = (EditText) view.findViewById(R.id.editText_PI_mail);
            edAddress = (EditText) view.findViewById(R.id.editText_PI_address);
            edContactPhone = (EditText) view.findViewById(R.id.editText_PI_contact_phone);
            edPhone = (EditText) view.findViewById(R.id.editText_PI_phone);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    edName.setText(PIname);
                    tvGender.setText(PIgender);
                    tvCareer.setText(PIcareer);
                    tvNationality.setText(PInationality);
                    tvID.setText((PIID));
                    tvBirthday.setText(PIbirthday);
                    edMail.setText(PImail);
                    edAddress.setText(PIaddress);
                    edContactPhone.setText(PIcontact_phone);
                    edPhone.setText(PIphone);
                }
            }, 1500);


            imgProfilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    galleryIntent();
                }
            });

            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeName = edName.getText().toString();
                    ChangeMail = edMail.getText().toString();
                    ChangeAddress = edAddress.getText().toString();
                    ChangeContactPhone = edContactPhone.getText().toString();
                    ChangePhone = edPhone.getText().toString();

                    Log.d(Change, ChangeName);
                    Log.d(Change, ChangeMail);
                    Log.d(Change, ChangeAddress);
                    Log.d(Change, ChangeContactPhone);
                    Log.d(Change, ChangePhone);

                    UpdatePersonalInfo updatePersonalInfo = new UpdatePersonalInfo();
                    updatePersonalInfo.execute();

                    Toast.makeText(getActivity(), "更新成功", Toast.LENGTH_SHORT).show();


                }
            });

            ProfilePicUrl = preferences.getString("PREFERENCE_PROFILE_PIC_URL", "");

            if (!ProfilePicUrl.equals("")) {
                Picasso.with(getActivity()).load(ProfilePicUrl).transform(new CircleTransform()).into(imgProfilePic);
            } else {
                Toast.makeText(getActivity(), "請選擇大頭貼", Toast.LENGTH_LONG).show();
            }

            layoutSetKinship.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    fragmentClass = KinshipManageFragment.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    fragment.setTargetFragment(getTargetFragment(), 0);
                    getFragmentManager()
                            .beginTransaction()
                            .add(R.id.flContent, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });


        }

        Log.d("55123-PersonalInfFrg", "onCreateView");
        Log.d("55123-Personal", PIaccount);

        return view;

    }

    private void galleryIntent() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select File"), 0);

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        Uri selectedImageUri = null;

//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == 0) {
//
//                selectedImageUri = data.getData();
//                imagepath = GetPhotoPath.getPath(getContext(), selectedImageUri);
//                Log.d("55123-imagepath", imagepath);
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        UploadFile mFileUpload = new UploadFile();
//                        mFileUpload.setOnFileUploadListener(new UploadFile.OnFileUploadListener() {
//                            @Override
//                            public void onFileUploadSuccess(final String msg) {
//                                Log.d("55123-FileUpload", "onFileUploadSuccess");
//                                (getActivity()).runOnUiThread(new Runnable() {
//                                    public void run() {
//                                        Picasso.with(getActivity()).load(ProfilePicUrl).transform(new CircleTransform()).into(imgProfilePic);
//                                    }
//                                });
//
//                            }
//
//                            @Override
//                            public void onFileUploadFail(final String msg) {
//                                Log.d("55123-FileUpload", msg);
//                            }
//                        });
//                        mFileUpload.doFileUpload(imagepath, preferences.getString("PREFERENCE_ACCOUNT", MainActivity.account));
//                    }
//                }).start();
//
//            }
//        }
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;

            case 2:
                if (data != null) {
//                     Bundle extras = data.getExtras();
                    head = data.getParcelableExtra("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(toRoundBitmap(head));//保存在SD卡中

                        imgProfilePic.setImageBitmap(toRoundBitmap(head));//用ImageView显示出来

                    }
                    break;


                }
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建以此File对象为名（path）的文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                if(b != null){
                    b.flush();
                    b.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        //进入系统裁剪图片的界面
        startActivityForResult(intent, 2);
    }

    public Bitmap toRoundBitmap(Bitmap bitmap) {
        //圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r = 0;
        //取最短边做边长
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        //构建一个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);
        //返回已经绘画好的backgroundBmp
        return backgroundBmp;
    }

}

