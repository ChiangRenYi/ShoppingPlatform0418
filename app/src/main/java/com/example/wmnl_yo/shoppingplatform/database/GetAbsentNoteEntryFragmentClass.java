package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.AbsentNoteEntryFragment;

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

public class GetAbsentNoteEntryFragmentClass extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getAbsentNoteEntryFragment_class...");
        String url = Constants.SERVER_URL + "getAbsentNoteEntryFragment_class.php";
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
                    .appendQueryParameter("student_name", AbsentNoteEntryFragment.db_absent_entry_student.trim())
                    .appendQueryParameter("account",Constants.ACCOUNT)
                    .appendQueryParameter("building",AbsentNoteEntryFragment.db_absent_entry_building.trim())
                    .appendQueryParameter("day",AbsentNoteEntryFragment.db_absent_entry_start.trim());                    ;
                    Log.d("55125",AbsentNoteEntryFragment.db_absent_entry_student.trim()
                            +Constants.ACCOUNT+AbsentNoteEntryFragment.db_absent_entry_building
                            +AbsentNoteEntryFragment.db_absent_entry_start.trim());
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
                        AbsentNoteEntryFragment.absent_entry_class_net="nothing";
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
            if( s.equals("nothing")){

            }else{
                String[] postclass = s.split("@#|:");
                int postclassInf = postclass.length;
                postclass[postclassInf-1] = postclass[postclassInf-1].trim();

                String post_class[] = new String[postclassInf/7+1];
                String post_id[] = new String[postclassInf/7+1];
                post_class[0] = "請選擇";
                post_id[0] = "請選擇";
                for(int i = 0 ; i < postclassInf-8 ; i++)
                {
                    post_class[i/8+1] = postclass[i+2]+":"+postclass[i+3]+"~"+postclass[i+5]+":"+postclass[i+6]+" "+postclass[i+8];
                    Log.d("55125","posttimeInf"+post_class[i/8+1]);
                    post_id[i/8+1] =  postclass[i+1] +"-"+ postclass[i+2]+":"+postclass[i+3]+"~"+postclass[i+5]+":"+postclass[i+6]+" "+postclass[i+8];
                    Log.d("55125","posttimeInf"+post_id[i/8+1]);
                    i = i+7;
                }
                AbsentNoteEntryFragment.string_absent_entry_class = post_class;
                AbsentNoteEntryFragment.string_absent_entry_class_new = post_id;
            }
           /*
            AbsentNoteEntryFragment.string_absent_entry_building = nonDuplicateBuildingArray;
            */
        }
    }
}
