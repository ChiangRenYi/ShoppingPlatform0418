package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.BuildingDetailFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.BuildingFragment;
import com.example.wmnl_yo.shoppingplatform.object.BuildingObject;

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

public class GetBuildingDetail extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getBuildingDetail...");
        String url = Constants.SERVER_URL + "getBuildingDetail.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //傳值
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("country", BuildingFragment.bcountry.trim())
                    .appendQueryParameter("city", BuildingFragment.bcity.trim())
                    .appendQueryParameter("type", BuildingFragment.btype.trim());
Log.d("55125",BuildingFragment.bcountry+BuildingFragment.bcity+BuildingFragment.buildingType);
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
                Log.d("55125", "200");
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
                        BuildingObject.ITEMS.clear();
                        BuildingFragment.connectbuilding = "nothing";
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
            return result;

        }
    }

    protected void onPostExecute(String s) {
        if (s != null) {
            Log.d("55125", s);
            BuildingFragment.connectbuilding = "check";
            int j = 0;
            BuildingObject.ITEMS.clear();
            BuildingObject dim = new BuildingObject();

            String[] building = s.split("<br>");
            String[] buildingName = new String[building.length - 1];
            String[] buildingPhone = new String[building.length - 1];
            String[] buildingTime = new String[building.length - 1];
            String[] buildingAddress = new String[building.length - 1];

            for (int i = 0; i < building.length - 1; i++) {
                String[] buildingInf = new String[4];
                buildingInf = building[i].split("@#");
                dim.addItem(new BuildingObject.BuildingObjectItem(String.valueOf(i + 1), buildingInf[1],buildingInf[2],buildingInf[3],buildingInf[4]));

                buildingName[j] = buildingInf[1];
                buildingPhone[j] = buildingInf[2];
                buildingTime[j] = buildingInf[3];
                buildingAddress[j] = buildingInf[4];
                j++;
                Log.d("55125", j + ":"  + buildingInf[1] + buildingInf[2] + buildingInf[3] + buildingInf[4]);
            }

            if(BuildingDetailFragment.buildadapter!=null) {
                BuildingDetailFragment.buildadapter.notifyDataSetChanged();
            }
        }
    }
}
