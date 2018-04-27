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
 * Created by WMNL-Jimmy on 2017/9/25.
 */

public class GetPersonalInfo extends AsyncTask<String,Void,String>{
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "GetPersonalInfo...");
        String url = Constants.SERVER_URL + "GetPersonalInfo.php";
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
            Log.e("55555~",a);
            Log.e("55886","再次顯示回傳結果");
            return a;

        }
    }

    protected void onPostExecute(String s) {
        String career = "";
        String country= "";
        String[] result = s.split("@#");
        Log.d("55555",result[1]+result[2]+result[3]+result[4]+result[5]+result[6]+result[7]+result[8]+result[9]+result[10]);

        if(result[2].equals("0")){
            result[2] = "男";
        }else{
            result[2] = "女";
        }

        switch (result[3]){

            case "0":
                career = "公";
                break;

            case "1":
                career = "工";
                break;

            case "2":
                career = "教";
                break;

            case "3":
                career = "軍/警";
                break;

            case "4":
                career = "家管";
                break;

            case "5":
                career = "商";
                break;


            default:
                break;
        }

        switch (result[4]){
            case "1":
                country = "台灣";
                break;

            case "2":
                country = "日本";
                break;

            case "3":
                country = "美國";
                break;

            case "4":
                country = "中國";
                break;

            case "5":
                country = "香港";
                break;

            default:
                break;
        }
        MemberServiceFragment.PIname = result[1];
        MemberServiceFragment.PIgender = result[2];
        MemberServiceFragment.PIcareer = career;
        MemberServiceFragment.PInationality = country;
        MemberServiceFragment.PIID = result[5];
        MemberServiceFragment.PIbirthday = result[6];
        MemberServiceFragment.PImail = result[7];
        MemberServiceFragment.PIaddress = result[8];
        MemberServiceFragment.PIcontact_phone = result[9];
        MemberServiceFragment.PIphone = result[10];

        Log.e("DB55555", MemberServiceFragment.PIgender);
    }

}
