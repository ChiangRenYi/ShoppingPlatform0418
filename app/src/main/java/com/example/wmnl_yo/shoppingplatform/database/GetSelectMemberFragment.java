package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.fragment.SelectMemberFragment;
import com.example.wmnl_yo.shoppingplatform.object.SelectMemberObject;

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
 * Created by Sandy on 2017/8/30.
 */

public class GetSelectMemberFragment extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getSelectMemberFragment...");
        String url = Constants.SERVER_URL + "getSelectMemberFragment.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            //傳值
            Uri.Builder builder = new Uri.Builder()
                    //傳值到php
                    .appendQueryParameter("account", Constants.ACCOUNT.trim());

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
                        SelectMemberObject.ITEMS.clear();
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
//            Log.d("55125",result);
//            int j = 0;
//
//            String[] selectMember = result.split("<QQ>");
//            String[] selectMember_name = new String[selectMember.length - 1];
//            String[] selectMember_auid = new String[selectMember.length - 1];
//            String[] selectMember_erid = new String[selectMember.length - 1];
//
//            SelectMemberObject.ITEMS.clear();
//            SelectMemberObject dim = new SelectMemberObject();
//
//            for (int i = 0; i < selectMember.length - 1; i++) {
//                String[] selectMemberInf = new String[5];
//                selectMemberInf = selectMember[i].split("@#");
//                selectMember_name[j] = selectMemberInf[1];
//                selectMember_auid[j] = selectMemberInf[2];
//                selectMember_erid[j] = selectMemberInf[3];
//
//                dim.addItem(new SelectMemberObject.SelectMemberObjectItem(R.drawable.ic_person,
//                        selectMemberInf[1],selectMemberInf[2],selectMemberInf[3]));
//
//                j++;
//                Log.d("55125", selectMemberInf[1]+","+selectMemberInf[2]+","+selectMemberInf[3]);
//            }
            return result;

        }
    }

    protected void onPostExecute(String s) {

        if (s != null){
            Log.d("55125",s);
            int j = 0;

            String[] selectMember = s.split("<QQ>");
            String[] selectMember_name = new String[selectMember.length - 1];
            String[] selectMember_auid = new String[selectMember.length - 1];
            String[] selectMember_erid = new String[selectMember.length - 1];

            SelectMemberObject.ITEMS.clear();
            SelectMemberObject dim = new SelectMemberObject();

            for (int i = 0; i < selectMember.length - 1; i++) {
                String[] selectMemberInf = new String[5];
                selectMemberInf = selectMember[i].split("@#");
                selectMember_name[j] = selectMemberInf[1];
                selectMember_auid[j] = selectMemberInf[2];
                selectMember_erid[j] = selectMemberInf[3];

                dim.addItem(new SelectMemberObject.SelectMemberObjectItem(R.drawable.ic_person,
                        selectMemberInf[1],selectMemberInf[2],selectMemberInf[3]));

                j++;
                Log.d("55125", selectMemberInf[1]+","+selectMemberInf[2]+","+selectMemberInf[3]);
            }
        }

        Log.d("55125","notifyDataSetChanged");
        SelectMemberFragment.bAdapter.notifyDataSetChanged();
    }
}
