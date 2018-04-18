package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryFragment;

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

public class GetCourseQueryFragmentTime extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getCourseQueryFragmentTime...");
        String url = Constants.SERVER_URL + "getCourseQueryFragmentTime.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(35000);
            //傳值
            Log.d("55125", CourseQueryFragment.cCountry+","+CourseQueryFragment.cCity+","
                    +CourseQueryFragment.cBuilding+","+CourseQueryFragment.cClass+","
                    +CourseQueryFragment.cMonth+","+CourseQueryFragment.cTeacher);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("country", CourseQueryFragment.cCountry.trim())
                    .appendQueryParameter("city",CourseQueryFragment.cCity.trim())
                    .appendQueryParameter("building",CourseQueryFragment.cBuilding.trim())
                    .appendQueryParameter("class",CourseQueryFragment.cClass.trim())
                    .appendQueryParameter("month",CourseQueryFragment.cMonth.trim())
                    .appendQueryParameter("teacher",CourseQueryFragment.cTeacher.trim())
                    .appendQueryParameter("type",CourseQueryFragment.cType.trim());

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
            Log.d("55125",s);
            String[] posttimesplitInf1 = s.split("@#|:");
            int time1 = posttimesplitInf1.length;
            posttimesplitInf1[time1-1] = posttimesplitInf1[time1-1].trim();

            String posttimeInf[] = new String[time1/6+1];
            posttimeInf[0] = "不限";
            for(int i = 0 ; i < time1-6 ; i++)
            {
                posttimeInf[i/6+1] = posttimesplitInf1[i+1]+":"+posttimesplitInf1[i+2]+"~"+posttimesplitInf1[i+4]+":"+posttimesplitInf1[i+5];
                Log.d("55125","posttimeInf"+posttimeInf[i/6+1]);
                i = i+5;

            }
            // 利用 Set 的特性，將所有項目放入
            // Set//   中即可移除重複的項目
            //刪除重複的內容
            Set<String> intSet = new HashSet<String>();
            for (String element : posttimeInf) {
                intSet.add(element);
            }
            // intSet.size() 為不重複項目的個數
            String nonDuplicateTimeArray[] = new String[intSet.size()];
            // 將 Set 中的項目取出放到 nonDuplicateTimeArray 中
            // 這裡也可以利用 iterator 來達成
            Object[] tempArray = intSet.toArray();
            for (int i = 0; i < tempArray.length; i++) {
                nonDuplicateTimeArray[i] = (String) tempArray[i];
            }
            for (int t = 0 ; t < nonDuplicateTimeArray.length ; t++){
                if(nonDuplicateTimeArray[t] == "不限")
                {
                    nonDuplicateTimeArray[t] = nonDuplicateTimeArray[0];
                    nonDuplicateTimeArray[0] = "不限";
                }
            }
            CourseQueryFragment.stringTime = nonDuplicateTimeArray;
        }


    }

}
