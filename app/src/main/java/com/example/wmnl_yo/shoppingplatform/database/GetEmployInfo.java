package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.MemberServiceFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WMNL-Jimmy on 2017/12/7.
 */

public class GetEmployInfo extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "GetEmployInfo...");
        String url = Constants.SERVER_URL + "GetEmployInfo.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //傳值
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("PIaccount", MemberServiceFragment.PIaccount);

            String query = builder.build().getEncodedQuery();

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {/*success*/
                InputStream inputStream = connection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                Log.d("55125", "200");
                if (inputStream == null) {
                    // Nothing to do.
                    Log.d("55125", "get inputStream error");
                } else {
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                        buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        // Stream was empty. No point in parsing.
                        Log.d("55125", "nothing");
                    } else {
                        result = buffer.toString();

                    }
                }
            }
        } catch (Exception e) {
            Log.e("55125", e.toString());
            return null;
        } finally {
                /*close urlConnection*/
            if (connection != null) {
                connection.disconnect();
            }
                /*close inputStreamReader*/
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("55125", "Error", e);
                }
            }
            Log.e("55555",result);
            Log.e("55886","顯示資料庫回傳結果");
            String a = result.trim();
            Log.e("GetEmployInfo-55555~",a);
            Log.e("55886","再次顯示回傳結果");
            return a;

        }
    }

    protected void onPostExecute(String s) {
        String[] result = s.split("@#");
        Log.d("55555-GetEmployInfo",result[1]+result[2]+result[3]+result[4]+result[5]+result[6]+result[7]+result[8]+result[9]+result[10]+result[11]+result[12]+result[13]+result[14]+result[15]+result[16]+result[17]+result[18]+result[19]+result[20]+result[21]+result[22]);

        if(result[9].equals("0")){
            result[9] = "男";
        }else{
            result[9] = "女";
        }
//        switch (result[3])
//        {
//            case "1":
//                result[3]="公";
//                break;
//            case "2":
//                result[3]="工";
//                break;
//            case "3":
//                result[3]="教";
//                break;
//            case "4":
//                result[3]="軍/警";
//                break;
//            case "5":
//                result[3]="家管";
//                break;
//            case "6":
//                result[3]="商";
//                break;
//        }
//        switch (result[4])
//        {
//            case "1":
//                result[4]="台灣";
//                break;
//            case "2":
//                result[4]="日本";
//                break;
//            case "3":
//                result[4]="美國";
//                break;
//            case "4":
//                result[4]="中國";
//                break;
//            case "5":
//                result[4]="香港";
//                break;
//        }

        MemberServiceFragment.EMNO = result[1];
        MemberServiceFragment.EMjob = result[2];
        MemberServiceFragment.EMRdate = result[3];
        MemberServiceFragment.EMlicense = result[4];
        MemberServiceFragment.EMname = result[5];
        MemberServiceFragment.EMEname = result[6];
        MemberServiceFragment.EMbirthday = result[7];
        MemberServiceFragment.EMID = result[8];
        MemberServiceFragment.EMgender = result[9];
        MemberServiceFragment.EMcontact_phone = result[10];
        MemberServiceFragment.EMphone = result[11];
        MemberServiceFragment.EMnationality = result[12];
        MemberServiceFragment.EMmail = result[13];
        MemberServiceFragment.EMaddress = result[14];
        MemberServiceFragment.EMNaddress = result[15];
        MemberServiceFragment.EMeducation = result[16];
        MemberServiceFragment.EMhome = result[17];
        MemberServiceFragment.EMchildren = result[18];
        MemberServiceFragment.EMschool = result[19];
        MemberServiceFragment.EMdepartment = result[20];
        MemberServiceFragment.EM_Emer_people = result[21];
        MemberServiceFragment.EM_Emer_phone = result[22];

    }
}
