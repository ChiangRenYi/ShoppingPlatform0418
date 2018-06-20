package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingCarATMFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingFinialFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingPaymentFragment;

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
 * Created by Sandy on 2017/10/19.
 */

public class Update_orderlist extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "insert_bto_and_update_orderlist...");
        String url = Constants.SERVER_URL + "insert_bto_and_update_orderlist.php";
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
                    .appendQueryParameter("bod_id", ShoppingPaymentFragment.bigorderlistnumber)
                    .appendQueryParameter("account", Constants.ACCOUNT)
                    .appendQueryParameter("bod_allobject_id", ShoppingPaymentFragment.shoppingcar_goodscount)
                    .appendQueryParameter("bod_allobject_number", ShoppingPaymentFragment.shoppingcar_goodsnumber)
                    .appendQueryParameter("bod_allobject_price", ShoppingPaymentFragment.shoppingcar_goodsprice);
            Log.d("55125", ShoppingPaymentFragment.bigorderlistnumber+","+Constants.ACCOUNT+","+ShoppingPaymentFragment.shoppingcar_goodscount+","+ShoppingPaymentFragment.shoppingcar_goodsnumber+","+ShoppingPaymentFragment.shoppingcar_goodsprice);
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

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125", s);
            ShoppingPaymentFragment.pay = s.trim();
            ShoppingFinialFragment.sp_code_number_data = ShoppingPaymentFragment.bigorderlistnumber;
            ShoppingCarATMFragment.sp_atm_number_data = ShoppingPaymentFragment.bigorderlistnumber;
        }
    }
}
