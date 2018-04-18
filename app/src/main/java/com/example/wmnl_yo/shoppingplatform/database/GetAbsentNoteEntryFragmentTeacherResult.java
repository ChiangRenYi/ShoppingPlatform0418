package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteRecordFragmentTeacher;
import com.example.wmnl_yo.shoppingplatform.object.AbsentNoteTeacherObject;

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

public class GetAbsentNoteEntryFragmentTeacherResult extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getAbsentNoteEntryFragmentTeacherResult...");
        String url = Constants.SERVER_URL + "getAbsentNoteEntryFragmentTeacherResult.php";
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
                        AbsentNoteTeacherObject.ITEMS.clear();
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

            String[] absent_teacher = s.split("<QQ>");
            String[] absent_teacher_id = new String[absent_teacher.length - 1];
            String[] absent_teacher_number = new String[absent_teacher.length - 1];
            String[] absent_teacher_building = new String[absent_teacher.length - 1];
            String[] absent_teacher_start = new String[absent_teacher.length - 1];
            String[] absent_teacher_end = new String[absent_teacher.length - 1];
            String[] absent_teacher_type = new String[absent_teacher.length - 1];
            String[] absent_teacher_reason = new String[absent_teacher.length - 1];
            String[] absent_teacher_man = new String[absent_teacher.length - 1];
            String[] absent_teacher_things = new String[absent_teacher.length - 1];
            String[] absent_teacher_astatus = new String[absent_teacher.length - 1];
            String[] absent_teacher_anote = new String[absent_teacher.length - 1];

            AbsentNoteTeacherObject.ITEMS.clear();
            AbsentNoteTeacherObject dim = new AbsentNoteTeacherObject();

            for (int i = 0; i < absent_teacher.length - 1; i++) {
                String[] absent_teacherInf = new String[20];
                absent_teacherInf = absent_teacher[i].split("@#|:");
                absent_teacher_id[j] = absent_teacherInf[1];
                absent_teacher_number[j] = absent_teacherInf[2];
                absent_teacher_building[j] = absent_teacherInf[3];
                absent_teacher_start[j] = absent_teacherInf[4]+absent_teacherInf[5];
                absent_teacher_end[j] = absent_teacherInf[7]+absent_teacherInf[8];
                absent_teacher_reason[j] = absent_teacherInf[11];
                absent_teacher_man[j] = absent_teacherInf[12];
                absent_teacher_things[j] = absent_teacherInf[13];
                absent_teacher_anote[j] = absent_teacherInf[14];
                switch (absent_teacherInf[10]){
                    case "0" :
                        absent_teacher_type[j] = "事假";
                        absent_teacherInf[10] = "事假";
                        break;
                    case "1" :
                        absent_teacher_type[j] = "病假";
                        absent_teacherInf[10] = "病假";
                        break;
                    case "2":
                        absent_teacher_type[j] = "公假";
                        absent_teacherInf[10] = "公假";
                        break;
                    case "3":
                        absent_teacher_type[j] = "婚假";
                        absent_teacherInf[10] = "婚假";
                        break;
                    case "4":
                        absent_teacher_type[j] = "特休假";
                        absent_teacherInf[10] = "特休假";
                        break;
                    case "5":
                        absent_teacher_type[j] = "喪假";
                        absent_teacherInf[10] = "喪假";
                        break;
                    case "6":
                        absent_teacher_type[j] = "產假";
                        absent_teacherInf[10] = "產假";
                        break;
                    case "7":
                        absent_teacher_type[j] = "生理假";
                        absent_teacherInf[10] = "生理假";
                        break;
                    case "8":
                        absent_teacher_type[j] = "其他(請備註再原因)";
                        absent_teacherInf[10] = "其他(請備註再原因)";
                        break;
                    default:
                        break;
                }
                switch (absent_teacherInf[14]){
                    case "0" :
                        absent_teacher_astatus[j] = "審核中";
                        absent_teacherInf[14] = "審核中";
                        break;
                    case "1" :
                        absent_teacher_astatus[j] = "不通過";
                        absent_teacherInf[14] = "不通過";
                        break;
                    case "2" :
                        absent_teacher_astatus[j] = "通過";
                        absent_teacherInf[14] = "通過";
                        break;
                    default:
                        break;
                }
                dim.addItem(new AbsentNoteTeacherObject.AbsentNoteTeacherObjectItem(String.valueOf(i + 1),
                        absent_teacherInf[1], absent_teacherInf[2], absent_teacherInf[3], absent_teacherInf[4]+":"+absent_teacherInf[5], absent_teacherInf[7]+":"+absent_teacherInf[8],
                        absent_teacherInf[10], absent_teacherInf[11], absent_teacherInf[12], absent_teacherInf[13], absent_teacherInf[14], absent_teacherInf[10]));
                j++;
                Log.d("55125", j + ":"  + absent_teacherInf[1]+","+absent_teacherInf[2]+","+absent_teacherInf[3]+","+absent_teacherInf[4]+":"+absent_teacherInf[5]+","+absent_teacherInf[7]+":"+absent_teacherInf[8]+","+
                        absent_teacherInf[10]+","+absent_teacherInf[11]+","+absent_teacherInf[12]+","+absent_teacherInf[13]+","+absent_teacherInf[14]+","+absent_teacherInf[10]);
            }

        }
        AbsentNoteRecordFragmentTeacher.abAdapter.notifyDataSetChanged();
    }

}
