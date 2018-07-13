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
                        CourseRecordFragment.checkCourseRecord = "nothing";
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
            Log.d("55125-1",s);
            if(s.trim().equals("nothing")){
                CourseRecordFragment.checkCourseRecord = "nothing";
            }else {
                int j = 0;
                //String[] net = s.split("nothing");
                String[] courseRecord = s.split("<br>");
                CourseRecordFragment.checkCourseRecord = courseRecord[0].trim();
                String[] courseRecord_photo = new String[courseRecord.length - 1];
                String[] courseRecord_count = new String[courseRecord.length - 1];
                String[] courseRecord_building = new String[courseRecord.length - 1];
                String[] courseRecord_type = new String[courseRecord.length - 1];
                String[] courseRecord_classname = new String[courseRecord.length - 1];
                String[] courseRecord_teacher = new String[courseRecord.length - 1];
                String[] courseRecord_days = new String[courseRecord.length - 1];
                String[] courseRecord_sdate = new String[courseRecord.length - 1];
                String[] courseRecord_edate = new String[courseRecord.length - 1];
                String[] courseRecord_time = new String[courseRecord.length - 1];
                String[] courseRecord_teacherinfo = new String[courseRecord.length - 1];
                String[] courseRecord_content = new String[courseRecord.length - 1];
                String[] courseRecord_total = new String[courseRecord.length - 1];
                String[] courseRecord_remain = new String[courseRecord.length - 1];
                String[] courseRecord_price = new String[courseRecord.length - 1];
                String[] courseRecord_ocpayment = new String[courseRecord.length - 1];
                String[] courseRecord_odid = new String[courseRecord.length - 1];
                String[] courseRecord_studentid = new String[courseRecord.length - 1];
                String[] courseRecord_studentname = new String[courseRecord.length - 1];
                CourseRecordObject.ITEMS.clear();
                CourseRecordObject dim = new CourseRecordObject();

                for (int i = 0 ; i < courseRecord.length - 1; i++) {
                    String[] courseRecordInf;
                    Log.d("55125-2",String.valueOf(courseRecord.length-1));
                    Log.d("55125-2",courseRecord[i]);
                    courseRecordInf = courseRecord[i].split("@#");
                    courseRecord_photo[j] = courseRecordInf[1];
                    courseRecord_count[j] = courseRecordInf[2];
                    courseRecord_building[j] = courseRecordInf[3];
                    courseRecord_type[j] = courseRecordInf[4];
                    courseRecord_classname[j] = courseRecordInf[5];
                    courseRecord_teacher[j] = courseRecordInf[6];
                    courseRecord_days[j] = courseRecordInf[7];
                    courseRecord_sdate[j] = courseRecordInf[8];
                    courseRecord_edate[j] = courseRecordInf[9];

                    courseRecordInf[10] = "123" + courseRecordInf[10];
                    String[] timeQuery = courseRecordInf[10].split("@&");
                    String s1 = "";
                    for (int q = 0; q <= timeQuery.length - 1; q++) {
                        if (q % 4 == 3) {
                            s1 = s1 + timeQuery[q] + "~";
                        } else if (q % 4 == 2) {
                            s1 = s1 + "(" + timeQuery[q] + ")";
                        } else if (q % 4 == 1) {
                            s1 = s1 + timeQuery[q];
                        } else if (q % 4 == 0 && q != timeQuery.length && q != 0) {
                            s1 = s1 + timeQuery[q] + "\n";
                        } else if (q % 4 == 0 && q == timeQuery.length - 1) {
                            s1 = s1 + timeQuery[q];
                        }
                        Log.d("55125", q + ":" + s1);
                    }
                    courseRecordInf[10] = s1;
                    courseRecord_time[j] = courseRecordInf[10];

                    courseRecord_teacherinfo[j] = courseRecordInf[11];
                    courseRecord_content[j] = courseRecordInf[12];
                    courseRecord_total[j] = courseRecordInf[13];
                    courseRecord_remain[j] = courseRecordInf[14];
                    courseRecord_price[j] = courseRecordInf[15];
                    courseRecord_ocpayment[j] = courseRecordInf[16];
                    courseRecord_odid[j] = courseRecordInf[17];
                    courseRecord_studentid[j] = courseRecordInf[18];
                    courseRecord_studentname[j] = courseRecordInf[19];
                    switch (courseRecordInf[16]) {
                        case "0":
                            courseRecord_ocpayment[j] = "正取，請盡速繳費";
                            courseRecordInf[16] = "正取，請盡速繳費";
                            break;
                        case "1":
                            courseRecord_ocpayment[j] = "備取";
                            courseRecordInf[16] = "備取";
                            break;
                        case "3":
                            courseRecord_ocpayment[j] = "已加入購物車，請至購物車結帳";
                            courseRecordInf[16] = "已加入購物車，請至購物車結帳";
                            break;
                        default:
                            break;
                    }


                    dim.addItem(new CourseRecordObject.CourseRecordObjectItem(courseRecordInf[2], courseRecordInf[1], courseRecordInf[3], courseRecordInf[4], courseRecordInf[5], courseRecordInf[6],
                            courseRecordInf[7], courseRecordInf[8], courseRecordInf[9], courseRecordInf[10], courseRecordInf[11],
                            courseRecordInf[12], courseRecordInf[13], courseRecordInf[14], courseRecordInf[15], courseRecordInf[16], courseRecordInf[17], courseRecordInf[18], courseRecordInf[19]));

                    j++;
                    Log.d("55125", j + ":" + courseRecordInf[1] + "," + courseRecordInf[2] + "," + courseRecordInf[3] + "," + courseRecordInf[4] + "," + courseRecordInf[5] + "," + courseRecordInf[6] + "," +
                            courseRecordInf[7] + "," + courseRecordInf[8] + "," + courseRecordInf[9] + "," + courseRecordInf[10] + "," + courseRecordInf[11] + "," +
                            courseRecordInf[12] + "," + courseRecordInf[13] + "," + courseRecordInf[14] + "," + courseRecordInf[15] + "," + courseRecordInf[16] + "," + courseRecordInf[17] + "," + courseRecordInf[18] + "," + courseRecordInf[19]);
                }
            }
        }

        Log.d("55125","notifyDataSetChanged");
    }
}
