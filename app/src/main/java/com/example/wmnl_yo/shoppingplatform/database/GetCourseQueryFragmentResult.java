package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseQueryResultFragment;
import com.example.wmnl_yo.shoppingplatform.object.CourseObject;

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

public class GetCourseQueryFragmentResult extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getCourseQueryFragmentResult...");
        String url = Constants.SERVER_URL + "getCourseQueryFragmentResult.php";
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
            //傳值
            Log.d("55125", CourseQueryFragment.cCountry+","+CourseQueryFragment.cCity+","
                    +CourseQueryFragment.cBuilding+CourseQueryFragment.cMonth+","+CourseQueryFragment.cTeacher+","+CourseQueryFragment.cType);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("country", CourseQueryFragment.cCountry.trim())
                    .appendQueryParameter("city",CourseQueryFragment.cCity.trim())
                    .appendQueryParameter("building",CourseQueryFragment.cBuilding.trim())
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
            int j = 0;

            CourseQueryFragment.classall = s;

            String[] courseQuery = s.split("<br>");
            String[] courseQuery_photo = new String[courseQuery.length - 1];
            String[] courseQuery_count = new String[courseQuery.length - 1];
            String[] courseQuery_building = new String[courseQuery.length - 1];
            String[] courseQuery_type = new String[courseQuery.length - 1];
            String[] courseQuery_classname = new String[courseQuery.length - 1];
            String[] courseQuery_teacher = new String[courseQuery.length - 1];
            String[] courseQuery_days = new String[courseQuery.length - 1];
            String[] courseQuery_sdate = new String[courseQuery.length - 1];
            String[] courseQuery_edate = new String[courseQuery.length - 1];
            String[] courseQuery_time = new String[courseQuery.length - 1];
            String[] courseQuery_teacherinfo = new String[courseQuery.length - 1];
            String[] courseQuery_content = new String[courseQuery.length - 1];
            String[] courseQuery_total = new String[courseQuery.length - 1];
            String[] courseQuery_remain = new String[courseQuery.length - 1];
            String[] courseQuery_price = new String[courseQuery.length - 1];

            CourseObject.ITEMS.clear();
            CourseObject dim = new CourseObject();
            for (int i = 0; i < courseQuery.length - 1; i++) {
                String[] courseQueryInf ;
                courseQueryInf = courseQuery[i].split("@#");

                dim.addItem(new CourseObject.CourseObjectItem(
                                courseQueryInf[2],courseQueryInf[1],courseQueryInf[3],courseQueryInf[4],courseQueryInf[5],courseQueryInf[6],
                                courseQueryInf[7],courseQueryInf[8],courseQueryInf[9],courseQueryInf[10],courseQueryInf[11],
                                courseQueryInf[12],courseQueryInf[13],courseQueryInf[14],courseQueryInf[15]));
                courseQuery_photo[j] = courseQueryInf[1];
                courseQuery_count[j] = courseQueryInf[2];
                courseQuery_building[j] = courseQueryInf[3];
                courseQuery_type[j] = courseQueryInf[4];
                courseQuery_classname[j] = courseQueryInf[5];
                courseQuery_teacher[j] = courseQueryInf[6];
                courseQuery_days[j] = courseQueryInf[7];
                courseQuery_sdate[j] = courseQueryInf[8];
                courseQuery_edate[j] = courseQueryInf[9];
                courseQuery_time[j] = courseQueryInf[10];
                courseQuery_teacherinfo[j] = courseQueryInf[11];
                courseQuery_content[j] = courseQueryInf[12];
                courseQuery_total[j] = courseQueryInf[13];
                courseQuery_remain[j] = courseQueryInf[14];
                courseQuery_price[j] = courseQueryInf[15];
                j++;
                Log.d("55125", j + ":"  + courseQueryInf[1]+","+ courseQueryInf[2]+","+courseQueryInf[3]+","+courseQueryInf[4]+","+courseQueryInf[5]+","+courseQueryInf[6]+","+
                        courseQueryInf[7]+","+courseQueryInf[8]+","+courseQueryInf[9]+","+courseQueryInf[10]+","+courseQueryInf[11]+","+
                        courseQueryInf[12]+","+courseQueryInf[13]+","+courseQueryInf[14]+","+courseQueryInf[15]);
            }

            if(CourseQueryResultFragment.mAdapter!=null) {
                CourseQueryResultFragment.mAdapter.notifyDataSetChanged();
            }
        }
    }

}
