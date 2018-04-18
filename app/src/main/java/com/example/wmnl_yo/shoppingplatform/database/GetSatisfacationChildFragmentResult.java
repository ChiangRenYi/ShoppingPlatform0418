package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfacationChildFragment;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationChildObject;

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
 * Created by WMNL on 2018/2/26.
 */

public class GetSatisfacationChildFragmentResult extends AsyncTask<String, Void, String> {
    public static String[] satifacation_childName;
    public static String[] satisfacation_childID;
    public static String[] satisfacation;
    public static String child;
    protected String doInBackground(String... params) {
        Log.d("55125", "getSatisfacationChild.php");
        String url = Constants.SERVER_URL + "getSatisfacationChild.php";
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
//            SatisfacationChildFragment.child = result.trim();
//            Log.d("","56465");
//            Log.d("123",SatisfacationChildFragment.child);
//            Log.d("","56465");
            ////////////////////////////////////////


            Log.d("55125",result);
            int j = 0;

            satisfacation = result.split("<QQ>");
            satifacation_childName = new String[satisfacation.length-1];
            satisfacation_childID = new String[satisfacation.length-1];
            SatisfacationChildObject.ITEMS.clear();
            SatisfacationChildObject dim = new SatisfacationChildObject();
            for (int i = 0; i < satisfacation.length - 1; i++) {
                String[] satisfacationInf = new String[2];
                satisfacationInf = satisfacation[i].split("@#");
                satifacation_childName[j] = satisfacationInf[1];
//                Log.d("andydatabase",satisfacation[0]);//andytemp

                Log.d("andydatabase",satifacation_childName[j]);
                satisfacation_childID[j] = satisfacationInf[2];
                Log.d("andydatabase",satisfacation_childID[j]);




                dim.addItem(new SatisfacationChildObject.SatisfacationChildObjectItem(
                        satisfacationInf[1],satisfacationInf[2]));





                j++;
                Log.d("55125", j + ":"  + satisfacationInf[1]+","+satisfacationInf[2]);
            }




            ////////////////////////////////////////
            child=result;
            return result;

        }
    }

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125", s);


        }
        Log.d("55125","notifyDataSetChanged");
        SatisfacationChildFragment.rAdapter.notifyDataSetChanged();
    }
}
