package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingListFragment;
import com.example.wmnl_yo.shoppingplatform.object.ShoppingCarObject;

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

public class GetShoppingListGoods extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getShoppinglistobjectgoods...");
        String url = Constants.SERVER_URL + "getShoppinglistobjectgoods.php";
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
                    .appendQueryParameter("account",Constants.ACCOUNT);
            Log.d("55125","ShoppingMallSeleteKind");
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
                        ShoppingCarObject.ITEMS.clear();
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
            String[] shoppingObjectAll = s.split("<br>");
            String[] shoppingObjectAll_id = new String[shoppingObjectAll.length - 1];
            String[] shoppingObjectAll_kind = new String[shoppingObjectAll.length - 1];
            String[] shoppingObjectAll_name = new String[shoppingObjectAll.length - 1];
            String[] shoppingObjectAll_number = new String[shoppingObjectAll.length - 1];
            String[] shoppingObjectAll_price = new String[shoppingObjectAll.length - 1];
            String[] shoppingObjectAll_warehouse = new String[shoppingObjectAll.length - 1];

            ShoppingCarObject.ITEMS.clear();
            ShoppingCarObject dim = new ShoppingCarObject();
            for (int i = 0; i < shoppingObjectAll.length - 1; i++) {
                String[] shoppingMallAllInf = new String[20];
                shoppingMallAllInf = shoppingObjectAll[i].split("@#|:");
                shoppingObjectAll_id[j] = shoppingMallAllInf[1];
                shoppingObjectAll_kind[j] = shoppingMallAllInf[2];
                shoppingObjectAll_name[j] = shoppingMallAllInf[3];
                shoppingObjectAll_number[j] = shoppingMallAllInf[4];
                shoppingObjectAll_price[j] = shoppingMallAllInf[5];
                shoppingObjectAll_warehouse[j] = shoppingMallAllInf[6];


                dim.addItem(new ShoppingCarObject.ShoppingCarObjectItem(
                        shoppingMallAllInf[1],shoppingMallAllInf[2],shoppingMallAllInf[3],shoppingMallAllInf[4],shoppingMallAllInf[5],shoppingMallAllInf[6]));

                j++;
                Log.d("55125", j + ":"  + shoppingMallAllInf[1]+","+shoppingMallAllInf[2]+","+shoppingMallAllInf[3]+","+shoppingMallAllInf[4]+","+shoppingMallAllInf[5]+","+shoppingMallAllInf[6]);
            }
            ShoppingListFragment.goodsnumber = j;
        }

    }
}
