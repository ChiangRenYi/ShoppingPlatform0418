package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.fragment.SelectBuildingFragment;
import com.example.wmnl_yo.shoppingplatform.object.SelectBuildingObject;

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

public class GetSelectBuildingFragment extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getSelectBuildingFragment...");
        String url = Constants.SERVER_URL + "getSelectBuildingFragment.php";
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
                    .appendQueryParameter("account",Constants.ACCOUNT.trim());

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
                        SelectBuildingObject.ITEMS.clear();
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
            Log.d("55125",result);
            int j = 0;

            String[] selectBuilding = result.split("<QQ>");
            String[] selectBuilding_name = new String[selectBuilding.length - 1];
            String[] selectBuilding_auid = new String[selectBuilding.length - 1];
            String[] selectBuilding_erid = new String[selectBuilding.length - 1];

            SelectBuildingObject.ITEMS.clear();
            SelectBuildingObject dim = new SelectBuildingObject();

            for (int i = 0; i < selectBuilding.length - 1; i++) {
                String[] selectBuildingInf = new String[5];
                selectBuildingInf = selectBuilding[i].split("@#");
                selectBuilding_name[j] = selectBuildingInf[1];
                selectBuilding_auid[j] = selectBuildingInf[2];
                selectBuilding_erid[j] = selectBuildingInf[3];

                dim.addItem(new SelectBuildingObject.SelectBuildingObjectItem(R.drawable.ic_building,
                        selectBuildingInf[1],selectBuildingInf[2],selectBuildingInf[3]));

                j++;
                Log.d("55125", selectBuildingInf[1]+","+selectBuildingInf[2]+","+selectBuildingInf[3]);
            }
            return result;

        }
    }

    protected void onPostExecute(String s) {

        Log.d("55125","notifyDataSetChanged");
        SelectBuildingFragment.bAdapter.notifyDataSetChanged();
    }
}
