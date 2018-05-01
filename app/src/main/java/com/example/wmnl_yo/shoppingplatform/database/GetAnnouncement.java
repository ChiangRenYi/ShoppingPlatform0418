package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.object.AnnouncementObject;

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
 * Created by WMNL-Jimmy on 2017/11/1.
 */

public class GetAnnouncement extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getAnnouncement...");
        String url = Constants.SERVER_URL + "getAnnouncement.php";
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

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("state","requestAnnouncement");

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

        }catch (Exception e){

            Log.e("55123", e.toString());
            return null;

        }finally {
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
        if(s != null){
            Log.d("55125",s);
            int j = 0;
            AnnouncementObject.ITEMS.clear();
            AnnouncementObject dim = new AnnouncementObject();

            String[] announcementQuery = s.split("<br>");
            String T = String.valueOf(announcementQuery.length-1);
            Log.e("55125",T);
            String[] announcementQuery_title = new String[announcementQuery.length - 1];
            String[] announcementQuery_time = new String[announcementQuery.length - 1];
            String[] announcementQuery_content = new String[announcementQuery.length - 1];

            String[] announcementQueryInf = new String[4];
//            String[] timeSpare = new String[2];
            for (int i = 0; i < announcementQuery.length -1; i++){

                announcementQueryInf = announcementQuery[i].split("@#");
//                timeSpare = announcementQueryInf[2].split(".");
//                Log.d("55125",timeSpare[0]+"/"+timeSpare[1]);
                dim.addItem(new AnnouncementObject.AnnouncementObjectItem(
                        announcementQueryInf[2],
                        announcementQueryInf[1],
                        announcementQueryInf[3]));

                announcementQuery_time[j] =   announcementQueryInf[2];
                announcementQuery_title[j] = announcementQueryInf[1];
                announcementQuery_content[j] = announcementQueryInf[3];
                j++;

                Log.d("55125", j + ":"  + announcementQueryInf[1]+","+announcementQueryInf[2]+","+announcementQueryInf[3]);
            }
        }

    }

}
