package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthManageFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.HealthTemperatureFragment;
import com.example.wmnl_yo.shoppingplatform.object.CourseRecordObject;
import com.example.wmnl_yo.shoppingplatform.object.HealthTemperatureObject;

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

public class GetHealthTemperatureFragment extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getHealthTemperatureFragment...");
        String url = Constants.SERVER_URL + "getHealthTemperatureFragment.php";
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
                    .appendQueryParameter("account",Constants.ACCOUNT.trim())
                    .appendQueryParameter("SD_Id", HealthManageFragment.SDId.trim());

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
                        HealthTemperatureObject.ITEMS.clear();
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

            String[] healthTemperature = result.split("<QQ>");
            String[] healthTemperature_value = new String[healthTemperature.length - 1];
            String[] healthTemperature_date = new String[healthTemperature.length - 1];
            String[] healthTemperature_time = new String[healthTemperature.length - 1];

            HealthTemperatureObject.ITEMS.clear();
            HealthTemperatureObject dim = new HealthTemperatureObject();

            for (int i = 0; i < healthTemperature.length - 1; i++) {
                String[] healthTemperatureInf = new String[5];
                healthTemperatureInf = healthTemperature[i].split("@#");
                healthTemperature_value[j] = healthTemperatureInf[1];
                healthTemperature_date[j] = healthTemperatureInf[2];
                healthTemperature_time[j] = healthTemperatureInf[3];

                dim.addItem(new HealthTemperatureObject.HealthTemperatureObjectItem(R.drawable.ic_temperature,
                        healthTemperatureInf[1],"°C",healthTemperatureInf[2]+"("+healthTemperatureInf[3]+")"));

                j++;
                Log.d("55125", healthTemperatureInf[1]+"°C"+","+healthTemperatureInf[2]+"("+healthTemperatureInf[3]+")");
            }
            return result;

        }
    }

    protected void onPostExecute(String s) {

        Log.d("55125","notifyDataSetChanged");
        HealthTemperatureFragment.tAdapter.notifyDataSetChanged();
    }
}
