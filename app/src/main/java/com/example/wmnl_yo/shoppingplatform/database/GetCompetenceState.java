package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.CompetenceFragment;
import com.example.wmnl_yo.shoppingplatform.object.CompetenceObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2018/5/30.
 */

public class GetCompetenceState extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        Log.d("55125", "getCompetence...");
        String url = Constants.SERVER_URL + "getCompetence.php";
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

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("account", Constants.ACCOUNT);


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
            Log.d("55125", s);
            int j = 0;
            CompetenceObject.ITEMS.clear();
            CompetenceObject dim = new CompetenceObject();

//            List<CompetenceObject.CompetenceObjectItem> mList;

            String[] competenceSplit = s.split("<br>");
            String[] competenceBuilding = competenceSplit[0].split("@#");
            String[] competenceRegeist = competenceSplit[1].split("@#");

            CompetenceFragment.Building = competenceBuilding;
            CompetenceFragment.comBuilding = competenceRegeist;
            String T = String.valueOf(competenceBuilding.length - 1);
            Log.e("55125", T);

            for(int k = 1; k <competenceBuilding.length ; k++){
                dim.addItem(new CompetenceObject.CompetenceObjectItem(
                        competenceBuilding[k],
                        false
                ));

            }
//            for(int i = 1; i < competenceRegeist.length ; i++){
//
//            }
//
            for(int i =1; i < competenceRegeist.length ; i++){
                for(int n = 1; n < competenceBuilding.length ; n++){
                    if(Arrays.asList(competenceBuilding[n]).contains(competenceRegeist[i].trim())){

                    }
                }
            }

//
//            for (int i = 1; i < competenceBuilding.length  ; i++) {
//                Log.e("55123-1",competenceRegeist[i]);
//                if(Arrays.asList(competenceBuilding).contains(competenceRegeist[i].trim())){
//                    Log.e("55123",competenceRegeist[i]);
//                    dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                            competenceBuilding[i],
//                            true
//                    ));
//                }else{
//                    Log.e("55123-2",competenceRegeist[i]);
//                    dim.addItem(new CompetenceObject.CompetenceObjectItem(
//                            competenceBuilding[i],
//                            false
//                    ));
//                }
//                j++;
//
//                Log.d("55125", j + ":" + competenceBuilding[i]);
//            }
        }

    }
}
