package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.object.OrderObject;

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
 * Created by WMNL-Jimmy on 2018/3/9.
 */

public class GetOrderList extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        Log.d("55125", "getOrderList...");
        String url = Constants.SERVER_URL + "getOrderList.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(20000);

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("account", Constants.ACCOUNT);


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
                    Log.d("55123", "get inputStream error");
                } else {
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                        buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        // Stream was empty. No point in parsing.
                        Log.d("55123", "nothing");
                    } else {
                        result = buffer.toString();
                    }
                }
            }

        } catch (Exception e) {

            Log.e("55123", e.toString());
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
                    Log.e("55123", "Error", e);
                }
            }
            return result;

        }
    }

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125", s);
            int j = 0;
            OrderObject.ITEMS.clear();
            OrderObject dim = new OrderObject();

            String[] orderlistQuery = s.split("<br>");
            String T = String.valueOf(orderlistQuery.length - 1);
            Log.e("55125", T);
            String[] orderlistQuery_date = new String[orderlistQuery.length - 1];
            String[] orderlistQuery_num = new String[orderlistQuery.length - 1];
            String[] orderlistQuery_payway = new String[orderlistQuery.length - 1];
            String[] orderlistQuery_state = new String[orderlistQuery.length - 1];
            String[] orderlistQuery_money = new String[orderlistQuery.length - 1];

            String[] orderlistQueryInf = new String[4];
            for (int i = 0; i < orderlistQuery.length - 1; i++) {

                orderlistQueryInf = orderlistQuery[i].split("@#");
                dim.addItem(new OrderObject.OrderObjectItem(
                        orderlistQueryInf[2],
                        "課程",
                        "籃球",
                        orderlistQueryInf[3],
                        orderlistQueryInf[3],
                        orderlistQueryInf[4],
                        orderlistQueryInf[3],
                        "無",
                        10,
                        10,
                        100,
                        orderlistQueryInf[5],
                        orderlistQueryInf[1]));

                orderlistQuery_date[j] = orderlistQueryInf[0];
                orderlistQuery_num[j] = orderlistQueryInf[1];
                orderlistQuery_payway[j] = orderlistQueryInf[2];
                orderlistQuery_state[j] = orderlistQueryInf[3];
                j++;

                Log.d("55125", j + ":" + orderlistQueryInf[1] + "," + orderlistQueryInf[2] + "," + orderlistQueryInf[3] + "," + orderlistQueryInf[4] + "," + orderlistQueryInf[5]);
            }
        }

    }

}
