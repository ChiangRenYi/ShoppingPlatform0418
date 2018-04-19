package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordFragment;
import com.example.wmnl_yo.shoppingplatform.object.AbsentStudentRecordObject;

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

public class GetAbsentNoteEntryFragmentResult extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getAbsentNoteEntryFragmentResult...");
        String url = Constants.SERVER_URL + "getAbsentNoteEntryFragmentResult.php";
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
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("account", Constants.ACCOUNT);

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
                        AbsentStudentRecordObject.ITEMS.clear();
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

            String[] absent_student = s.split("<QQ>");
            String[] absent_student_id = new String[absent_student.length - 1];
            String[] absent_student_name = new String[absent_student.length - 1];
            String[] absent_student_date = new String[absent_student.length - 1];
            String[] absent_student_building = new String[absent_student.length - 1];
            String[] absent_student_course = new String[absent_student.length - 1];
            String[] absent_student_stime = new String[absent_student.length - 1];
            String[] absent_student_stime1 = new String[absent_student.length - 1];
            String[] absent_student_etime = new String[absent_student.length - 1];
            String[] absent_student_etime1 = new String[absent_student.length - 1];
            String[] absent_student_type = new String[absent_student.length - 1];
            String[] absent_student_money = new String[absent_student.length - 1];
            String[] absent_student_reason = new String[absent_student.length - 1];
            String[] absent_student_astatus = new String[absent_student.length - 1];

            AbsentStudentRecordObject.ITEMS.clear();
            AbsentStudentRecordObject dim = new AbsentStudentRecordObject();

            for (int i = 0; i < absent_student.length - 1; i++) {
                String[] absent_studentInf = new String[17];
                absent_studentInf = absent_student[i].split("@#|:");

                absent_student_id[j] = absent_studentInf[1];
                absent_student_name[j] = absent_studentInf[2];
                absent_student_date[j] = absent_studentInf[3];
                absent_student_building[j] = absent_studentInf[4];
                absent_student_course[j] = absent_studentInf[5];
                absent_student_stime[j] = absent_studentInf[6];
                absent_student_stime1[j] = absent_studentInf[7];
                absent_student_etime[j] = absent_studentInf[9];
                absent_student_etime1[j] = absent_studentInf[10];
                switch (absent_studentInf[12]){
                    case "0" :
                        absent_student_type[j] = "事假";
                        absent_studentInf[12] = "事假";
                        break;
                    case "1" :
                        absent_student_type[j] = "病假";
                        absent_studentInf[12] = "病假";
                        break;
                    case "2" :
                        absent_student_type[j] = "喪假";
                        absent_studentInf[12] = "喪假";
                        break;
                    case "3" :
                        absent_student_type[j] = "其他";
                        absent_studentInf[12] = "其他";
                        break;
                    default:
                        break;
                }
                switch (absent_studentInf[13]) {
                    case "0":
                        absent_student_money[j] = "退費";
                        absent_studentInf[13] = "退費";
                        break;
                    case "1":
                        absent_student_money[j] = "保留";
                        absent_studentInf[13] = "保留";
                        break;
                    default:
                        break;
                }
                absent_student_reason[j] = absent_studentInf[14];
                switch (absent_studentInf[15]){
                    case "-1" :
                        absent_student_astatus[j] = "不通過";
                        absent_studentInf[15] = "不通過";
                        break;
                    case "4" :
                        absent_student_astatus[j] = "通過";
                        absent_studentInf[15] = "通過";
                        break;
                    default:
                        absent_student_astatus[j] = "審核中";
                        absent_studentInf[15] = "審核中";
                        break;
                }
                dim.addItem(new AbsentStudentRecordObject.AbsentStudentRecordObjectItem(String.valueOf(i + 1),
                        absent_studentInf[1], absent_studentInf[2], absent_studentInf[3], absent_studentInf[4], absent_studentInf[5],
                        absent_studentInf[6], absent_studentInf[7], absent_studentInf[9], absent_studentInf[10], absent_studentInf[12],
                        absent_studentInf[13], absent_studentInf[14], absent_studentInf[15]));
                j++;
                Log.d("55125", j + ":"  + absent_studentInf[1]+","+absent_studentInf[2]+","+absent_studentInf[3]+","+absent_studentInf[4]+","+absent_studentInf[5]+","+
                        absent_studentInf[6]+","+absent_studentInf[7]+","+absent_studentInf[9]+","+absent_studentInf[10]+","+absent_studentInf[12]+","+absent_studentInf[13]+","+absent_studentInf[14]+","+absent_studentInf[15]);
            }
        }
        AbsentNoteRecordFragment.abAdapter.notifyDataSetChanged();
    }

}
