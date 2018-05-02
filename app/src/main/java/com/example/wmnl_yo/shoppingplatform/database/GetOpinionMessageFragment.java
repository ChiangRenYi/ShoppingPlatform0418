package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.OpinionMessageAddFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.OpinionMessengerFragment;
import com.example.wmnl_yo.shoppingplatform.object.OpinionmessageObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.wmnl_yo.shoppingplatform.fragment.OpinionMessageAddFragment.mopinionmessageList;

/**
 * Created by Sandy on 2017/8/30.
 */

public class GetOpinionMessageFragment extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getOpinionMessageFragment...");
        String url = Constants.SERVER_URL + "getOpinionMessageFragment.php";
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
                    .appendQueryParameter("AU_Id", OpinionMessengerFragment.AUId.trim())
                    .appendQueryParameter("ER_Id", OpinionMessengerFragment.ERId.trim())
                    .appendQueryParameter("OM_Text", OpinionMessengerFragment.OMText.trim())
                    .appendQueryParameter("OM_Date", OpinionMessengerFragment.OMDate.trim())
                    .appendQueryParameter("OM_Time", OpinionMessengerFragment.OMTime.trim());

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
                        OpinionmessageObject.ITEMS.clear();
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
                Log.d("55125",result);
                int j = 0;

                String[] opinionmessage = result.split("<QQ>");
                String[] opinionmessage_name = new String[opinionmessage.length - 1];
                String[] opinionmessage_message = new String[opinionmessage.length - 1];
                String[] opinionmessage_date = new String[opinionmessage.length - 1];
                String[] opinionmessage_time = new String[opinionmessage.length - 1];

                OpinionmessageObject.ITEMS.clear();
                OpinionmessageObject dim = new OpinionmessageObject();

                for (int i = 0; i < opinionmessage.length - 1; i++) {
                    String[] opinionmessageInf = new String[20];
                    opinionmessageInf = opinionmessage[i].split("@#");
                    opinionmessage_name[j] = opinionmessageInf[1];
                    opinionmessage_message[j] = opinionmessageInf[2];
                    opinionmessage_date[j] = opinionmessageInf[3];
                    opinionmessage_time[j] = opinionmessageInf[4];

                    dim.addItem(new OpinionmessageObject.OpinionmessageObjectItem(opinionmessageInf[1]+":",opinionmessageInf[2],opinionmessageInf[3]+"("+opinionmessageInf[4]+")"));

                    j++;
                    Log.d("55125", opinionmessageInf[1]+","+opinionmessageInf[2]+","+opinionmessageInf[3]+"("+opinionmessageInf[4]+")");
                }
            }
            return result;

        }
    }

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125",s);
            int j = 0;

            String[] opinionmessage = s.split("<QQ>");
            String[] opinionmessage_name = new String[opinionmessage.length - 1];
            String[] opinionmessage_message = new String[opinionmessage.length - 1];
            String[] opinionmessage_date = new String[opinionmessage.length - 1];
            String[] opinionmessage_time = new String[opinionmessage.length - 1];

            OpinionmessageObject.ITEMS.clear();
            OpinionmessageObject dim = new OpinionmessageObject();

            for (int i = 0; i < opinionmessage.length - 1; i++) {
                String[] opinionmessageInf = new String[20];
                opinionmessageInf = opinionmessage[i].split("@#");
                opinionmessage_name[j] = opinionmessageInf[1];
                opinionmessage_message[j] = opinionmessageInf[2];
                opinionmessage_date[j] = opinionmessageInf[3];
                opinionmessage_time[j] = opinionmessageInf[4];

                dim.addItem(new OpinionmessageObject.OpinionmessageObjectItem(opinionmessageInf[1]+":",opinionmessageInf[2],opinionmessageInf[3]+"("+opinionmessageInf[4]+")"));

                j++;
                Log.d("55125", opinionmessageInf[1]+","+opinionmessageInf[2]+","+opinionmessageInf[3]+"("+opinionmessageInf[4]+")");
            }
        }else {
            mopinionmessageList.clear();
            OpinionMessageAddFragment.oAdapter.notifyDataSetChanged();
        }
        Log.d("55125","notifyDataSetChanged");
        OpinionMessageAddFragment.oAdapter.notifyDataSetChanged();
    }
}
