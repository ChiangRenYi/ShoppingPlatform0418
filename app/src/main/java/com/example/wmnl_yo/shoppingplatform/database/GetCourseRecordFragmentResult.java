package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.CourseRecordFragment;
import com.example.wmnl_yo.shoppingplatform.object.CourseRecordObject;

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

public class GetCourseRecordFragmentResult extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getCourseRecordFragmentResult...");
        String url = Constants.SERVER_URL + "getCourseRecordFragment.php";
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
                        CourseRecordObject.ITEMS.clear();
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

            String[] courseRecord = s.split("<QQ>");
            String[] courseRecord_picture = new String[courseRecord.length - 1];
            String[] courseRecord_csid = new String[courseRecord.length - 1];
            String[] courseRecord_erename = new String[courseRecord.length - 1];
            String[] courseRecord_cscname = new String[courseRecord.length - 1];
            String[] courseRecord_emname = new String[courseRecord.length - 1];
            String[] courseRecord_occsdate = new String[courseRecord.length - 1];
            String[] courseRecord_rorweek = new String[courseRecord.length - 1];
            String[] courseRecord_occstime = new String[courseRecord.length - 1];
            String[] courseRecord_occstime1 = new String[courseRecord.length - 1];
            String[] courseRecord_occetime = new String[courseRecord.length - 1];
            String[] courseRecord_occetime1 = new String[courseRecord.length - 1];
            String[] courseRecord_emabiography = new String[courseRecord.length - 1];
            String[] courseRecord_csccontent = new String[courseRecord.length - 1];
            String[] courseRecord_ocanumber = new String[courseRecord.length - 1];
            String[] courseRecord_oclnumber = new String[courseRecord.length - 1];
            String[] courseRecord_ocprice =  new String[courseRecord.length - 1];
            String[] courseRecord_ocpayment =  new String[courseRecord.length - 1];
            CourseRecordObject.ITEMS.clear();
            CourseRecordObject dim = new CourseRecordObject();
            for (int i = 0; i < courseRecord.length - 1; i++) {
                String[] courseRecordInf = new String[20];
                courseRecordInf = courseRecord[i].split("@#|:");
                courseRecord_picture[j] = courseRecordInf[1];
                courseRecord_csid[j] = courseRecordInf[2];
                courseRecord_erename[j] = courseRecordInf[3];
                courseRecord_cscname[j] = courseRecordInf[4];
                courseRecord_emname[j] = courseRecordInf[5];
                courseRecord_occsdate[j] = courseRecordInf[6];
                courseRecord_rorweek[j] = courseRecordInf[7];
                courseRecord_occstime[j] = courseRecordInf[8];
                courseRecord_occstime1[j] = courseRecordInf[9];
                courseRecord_occetime[j] = courseRecordInf[11];
                courseRecord_occetime1[j] = courseRecordInf[12];
                courseRecord_emabiography[j] = courseRecordInf[14];
                courseRecord_csccontent[j] = courseRecordInf[15];
                courseRecord_ocanumber[j] = courseRecordInf[16];
                courseRecord_oclnumber[j] = courseRecordInf[17];
                courseRecord_ocprice[j] = courseRecordInf[18];
                courseRecord_ocpayment[j] = courseRecordInf[19];

                switch (courseRecordInf[19]){
                    case "0" :
                        courseRecord_ocpayment[j] = "未結帳";
                        courseRecordInf[19] = "未結帳";
                        break;
                    case "1" :
                        courseRecord_ocpayment[j] = "已結帳";
                        courseRecordInf[19] = "已結帳";
                        break;
                    default:
                        break;
                }


                dim.addItem(new CourseRecordObject.CourseRecordObjectItem(String.valueOf(i + 1),
                        courseRecordInf[1],courseRecordInf[2],courseRecordInf[3],courseRecordInf[4],courseRecordInf[5],courseRecordInf[6],
                        courseRecordInf[7],courseRecordInf[8],courseRecordInf[9],courseRecordInf[11],courseRecordInf[12],
                        courseRecordInf[14],courseRecordInf[15],courseRecordInf[16],courseRecordInf[17],courseRecordInf[18],courseRecordInf[19]));

                j++;
                Log.d("55125", j + ":"  + courseRecordInf[1]+","+courseRecordInf[2]+","+courseRecordInf[3]+","+courseRecordInf[4]+","+courseRecordInf[5]+","+courseRecordInf[6]+","+
                        courseRecordInf[7]+","+courseRecordInf[8]+","+courseRecordInf[9]+","+courseRecordInf[11]+","+courseRecordInf[12]+","+
                        courseRecordInf[14]+","+courseRecordInf[15]+","+courseRecordInf[16]+","+courseRecordInf[17]+","+courseRecordInf[18]+","+courseRecordInf[19]);
            }

        }
        Log.d("55125","notifyDataSetChanged");
        CourseRecordFragment.rAdapter.notifyDataSetChanged();
    }
}
