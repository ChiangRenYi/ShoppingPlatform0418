package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.EmployInfoFragment;
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

public class UpdateEmployInfo extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "UpdateEmployInfo...");
        String url = Constants.SERVER_URL + "UpdateEmployInfo.php";
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
                    .appendQueryParameter("EMname", EmployInfoFragment.ChangeName)
                    .appendQueryParameter("EMEname", EmployInfoFragment.ChangeEname)
                    .appendQueryParameter("EMCphone", EmployInfoFragment.ChangeContactPhone)
                    .appendQueryParameter("Emphone", EmployInfoFragment.ChangePhone)
                    .appendQueryParameter("EMmail", EmployInfoFragment.ChangeMail)
                    .appendQueryParameter("EMaddress", EmployInfoFragment.ChangeAddress)
                    .appendQueryParameter("EMNaddress", EmployInfoFragment.ChangeNaddress)
                    .appendQueryParameter("EMedu", EmployInfoFragment.ChangeEducation)
                    .appendQueryParameter("EMhome", EmployInfoFragment.Changehome)
                    .appendQueryParameter("EMchildren", EmployInfoFragment.ChangeChildren)
                    .appendQueryParameter("EMsch", EmployInfoFragment.ChangeSchool)
                    .appendQueryParameter("EMdep", EmployInfoFragment.ChangeDepartment)
                    .appendQueryParameter("EMemerpeople", EmployInfoFragment.ChangeEmerPeople)
                    .appendQueryParameter("Ememerphone", EmployInfoFragment.ChangeEmerPhone);


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

            return result;

        }
    }
}
