package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingObjectFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sandy on 2017/8/30.
 */

public class GetShoppingMallKindsecond extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getShoppingMallKindsecond...");
        String url = Constants.SERVER_URL + "getShoppingMallKindsecond.php";
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
            Uri.Builder builder = new Uri.Builder().appendQueryParameter("kindsecond",ShoppingObjectFragment.db_shopping_object_kind);
                    Log.d("55125","getShoppingMallKind");
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
            String[] postkindInf = s.split("@#");
            postkindInf[0] = "請選擇";

            int count = postkindInf.length-1;
            postkindInf[count] = postkindInf[count].trim();
            Set<String> intSet = new HashSet<String>();
            for (String element : postkindInf) {
                intSet.add(element);
            }
            // intSet.size() 為不重複項目的個數
            String nonDuplicateBuildingArray[] = new String[intSet.size()];
            // 將 Set 中的項目取出放到 nonDuplicateBuildingArray 中
            // 這裡也可以利用 iterator 來達成
            Object[] tempArray = intSet.toArray();
            for (int i = 0; i < tempArray.length; i++) {
                nonDuplicateBuildingArray[i] = (String) tempArray[i];
            }
            for (int t = 0 ; t < nonDuplicateBuildingArray.length ; t++) {
                if(nonDuplicateBuildingArray[t] == "請選擇") {
                    nonDuplicateBuildingArray[t] = nonDuplicateBuildingArray[0];
                    nonDuplicateBuildingArray[0] = "請選擇";
                }
            }
            ShoppingObjectFragment.string_shopping_object_kindsecond = nonDuplicateBuildingArray;
        }
    }
}
