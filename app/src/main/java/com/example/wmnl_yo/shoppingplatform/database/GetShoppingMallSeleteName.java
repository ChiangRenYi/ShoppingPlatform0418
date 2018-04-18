package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingObjectFragment;
import com.example.wmnl_yo.shoppingplatform.object.ShoppingMallObject;

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

public class GetShoppingMallSeleteName extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getShoppingMallSeleteName...");
        String url = Constants.SERVER_URL + "getShoppingMallSeleteName.php";
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
            Uri.Builder builder = new Uri.Builder().appendQueryParameter("name", ShoppingObjectFragment.db_shopping_object_search_edittext.trim());
            Log.d("55125","ShoppingMallSeleteName");
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
                        ShoppingMallObject.ITEMS.clear();
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

            Log.d("55125",s);

            int j = 0;
            String[] shoppingMallAll = s.split("<br>");
            String[] courseRecord_photo = new String[shoppingMallAll.length - 1];
            String[] shoppingMallAll_id = new String[shoppingMallAll.length - 1];
            String[] shoppingMallAll_kind = new String[shoppingMallAll.length - 1];
            String[] shoppingMallAll_name = new String[shoppingMallAll.length - 1];
            String[] courseRecord_price = new String[shoppingMallAll.length - 1];
            String[] shoppingMallAll_amount = new String[shoppingMallAll.length - 1];
            String[] shoppingMallAll_factory = new String[shoppingMallAll.length - 1];

            ShoppingMallObject.ITEMS.clear();
            ShoppingMallObject dim = new ShoppingMallObject();
            for (int i = 0; i < shoppingMallAll.length - 1; i++) {
                String[] shoppingMallAllInf = new String[20];
                shoppingMallAllInf = shoppingMallAll[i].split("@#|:");
                courseRecord_photo[j] = shoppingMallAllInf[1];
                shoppingMallAll_id[j] = shoppingMallAllInf[2];
                shoppingMallAll_kind[j] = shoppingMallAllInf[3];
                shoppingMallAll_name[j] = shoppingMallAllInf[4];
                courseRecord_price[j] = shoppingMallAllInf[5];
                shoppingMallAll_amount[j] = shoppingMallAllInf[6];
                shoppingMallAll_factory[j] = shoppingMallAllInf[7];

                dim.addItem(new ShoppingMallObject.ShoppingMallObjectItem(
                        shoppingMallAllInf[1],shoppingMallAllInf[2],shoppingMallAllInf[3],shoppingMallAllInf[4],shoppingMallAllInf[5],shoppingMallAllInf[6],
                        shoppingMallAllInf[7],shoppingMallAllInf[8]));

                j++;
                Log.d("55125", j + ":"  + shoppingMallAllInf[1]+","+shoppingMallAllInf[2]+","+shoppingMallAllInf[3]+","+shoppingMallAllInf[4]+","+shoppingMallAllInf[5]+","+shoppingMallAllInf[6]+","+
                        shoppingMallAllInf[7]+","+shoppingMallAllInf[8]);
            }
        }

    }
}
