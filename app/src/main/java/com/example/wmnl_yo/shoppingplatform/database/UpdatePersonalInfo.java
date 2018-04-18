package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.MemberServiceFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.PersonalInfoFragment;

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

public class UpdatePersonalInfo extends AsyncTask<String,Void,String>{
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "UpdatePersonalInfo...");
        String url = Constants.SERVER_URL + "UpdatePersonalInfo.php";
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
                    .appendQueryParameter("PIaccount", MemberServiceFragment.PIaccount)
                    .appendQueryParameter("PIname", PersonalInfoFragment.ChangeName)
                    .appendQueryParameter("PImail", PersonalInfoFragment.ChangeMail)
                    .appendQueryParameter("PIaddress", PersonalInfoFragment.ChangeAddress)
                    .appendQueryParameter("PIcontactphone", PersonalInfoFragment.ChangeContactPhone)
                    .appendQueryParameter("PIphone", PersonalInfoFragment.ChangePhone);

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
}
