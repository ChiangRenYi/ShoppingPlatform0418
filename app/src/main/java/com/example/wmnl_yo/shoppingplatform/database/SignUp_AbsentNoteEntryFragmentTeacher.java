package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteEntryFragmentTeacher;

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
 * Created by Sandy on 2017/10/19.
 */

public class SignUp_AbsentNoteEntryFragmentTeacher extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "signUp_AbsentNoteEntryFragmentTeacher...");
        String url = Constants.SERVER_URL + "signUp_AbsentNoteEntryFragmentTeacher.php";
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
                    .appendQueryParameter("absentEntryTeacher_id", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_id.trim())
                    .appendQueryParameter("absentEntryTeacher_building", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_building.trim())
                    .appendQueryParameter("absentEntryTeacher_Sdate", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_start+" "+AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_starttime+":00.000000")
                    .appendQueryParameter("absentEntryTeacher_Edate", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_end+" "+AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_endtime+":00.000000")
                    .appendQueryParameter("absentEntryTeacher_kind", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_kind.trim())
                    .appendQueryParameter("absentEntryTeacher_reason", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_reason.trim())
                    .appendQueryParameter("absentEntryTeacher_man", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_man.trim())
                    .appendQueryParameter("absentEntryTeacher_things", AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_things.trim());
            Log.d("55125",AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_id
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_building
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_start+AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_starttime+":00"
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_end+AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_endtime+":00"
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_kind
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_reason
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_man
                    +AbsentNoteEntryFragmentTeacher.db_absent_entry_teacher_things);
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
            AbsentNoteEntryFragmentTeacher.absent_teacher_leave = result.trim();
            return result;

        }
    }

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125", s);


        }
    }
}
