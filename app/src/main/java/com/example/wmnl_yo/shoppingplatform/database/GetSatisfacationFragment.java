package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfactionSurveyFragment;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationSurveyObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.wmnl_yo.shoppingplatform.fragment.SatisfactionSurveyFragment.mSurveyRecordList;

/**
 * Created by WMNL on 2018/2/23.
 */

public class GetSatisfacationFragment extends AsyncTask<String, Void, String> {
    public static String[] satisfacation_teacher;
    public static String[] satisfacation_course;
    public static String[] satisfacation_questionnaire;
    public static String[] satisfacation_questionID;
    public static String[] satisfacation_coursePlace;

    public static String[] satisfacation;
    public static String result;
    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "getSatisfacationQuestionnaire...");
        String url = Constants.SERVER_URL + "getSatisfacationQuestionnaire.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        result = null;
        try {
            URL tkuUrl = new URL(url);
            connection = (HttpURLConnection) tkuUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            //傳值

            Log.d("andychildID", SatisfactionSurveyFragment.childID.trim());
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("childID", SatisfactionSurveyFragment.childID.trim());
            Log.d("andytemp", SatisfactionSurveyFragment.childID.trim());
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
                        SatisfacationSurveyObject.ITEMS.clear();

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

//            SatisfactionSurveyFragment.questionNaire = result.trim();
//            Log.d("andydatabase", SatisfactionSurveyFragment.questionNaire);//andytemp for look total output
//            Log.d("andydatabase",result);


            //////////////////////////////////////////
//                if(result!=null) {
//                    Log.d("andyresult", result);
//                    int j = 0;
//
//                    satisfacation = result.split("<QQ>");
//                    satisfacation_teacher = new String[satisfacation.length - 1];
//                    satisfacation_course = new String[satisfacation.length - 1];
//                    satisfacation_questionnaire = new String[satisfacation.length - 1];
//                    SatisfacationSurveyObject.ITEMS.clear();
//                    SatisfacationSurveyObject dim = new SatisfacationSurveyObject();
//                    for (int i = 0; i < satisfacation.length - 1; i++) {
//                        String[] satisfacationInf = new String[3];
//                        satisfacationInf = satisfacation[i].split("@#");
//                        satisfacation_teacher[j] = satisfacationInf[1];
////                Log.d("andydatabase",satisfacation[0]);//andytemp
//
//                        Log.d("andydatabase", satisfacation_teacher[j]);
//                        satisfacation_course[j] = satisfacationInf[2];
//                        Log.d("andydatabase", satisfacation_course[j]);
//
//                        satisfacation_questionnaire[j] = satisfacationInf[3];
//                        Log.d("andydatabase", satisfacation_questionnaire[j]);
//
//
//                        dim.addItem(new SatisfacationSurveyObject.SatisfacationSurveyObjectItem(
//                                satisfacationInf[1], satisfacationInf[2], satisfacationInf[3]));
//
//
//                        j++;
//                        Log.d("55125", j + ":" + satisfacationInf[1] + "," + satisfacationInf[2] + "," + satisfacationInf[3]);
//                    }
//
//
//                }

            /////////////////////////////////////////////
            return result;

        }
    }

    protected void onPostExecute(String s) {


        if (s != null) {
            Log.d("55125",s);
            int j = 0;

            satisfacation = s.split("<QQ>");
            satisfacation_teacher = new String[satisfacation.length-1];
            satisfacation_course = new String[satisfacation.length-1];
            satisfacation_questionnaire = new String[satisfacation.length-1];
            satisfacation_questionID = new String[satisfacation.length-1];
            satisfacation_coursePlace = new String[satisfacation.length-1];

            SatisfacationSurveyObject.ITEMS.clear();
            SatisfacationSurveyObject dim = new SatisfacationSurveyObject();
            for (int i = 0; i < satisfacation.length - 1; i++) {
                String[] satisfacationInf = new String[5];
                satisfacationInf = satisfacation[i].split("@#");
                satisfacation_teacher[j] = satisfacationInf[1];
//                Log.d("andydatabase",satisfacation[0]);//andytemp

                Log.d("andydatabase",satisfacation_teacher[j]);
                satisfacation_course[j] = satisfacationInf[2];
                Log.d("andydatabase",satisfacation_course[j]);

                satisfacation_questionnaire[j] = satisfacationInf[3];
                Log.d("andydatabase",satisfacation_questionnaire[j]);
                satisfacation_questionID[j] = satisfacationInf[4];
                Log.d("andydatabaseQuestionID",satisfacation_questionID[j]);

                satisfacation_coursePlace[j] = satisfacationInf[5];
                Log.d("andydatabaseQuestionID",satisfacation_coursePlace[j]);

                dim.addItem(new SatisfacationSurveyObject.SatisfacationSurveyObjectItem(
                        satisfacationInf[1],satisfacationInf[2],satisfacationInf[3],satisfacationInf[4],satisfacationInf[5]));





                j++;
                Log.d("55125", j + ":"  + satisfacationInf[1]+","+satisfacationInf[2]+","+satisfacationInf[3]+","+satisfacationInf[4]+","+satisfacationInf[5]);
            }

        }
        if(s==null)
        {
            Log.d("andyOnpost","null");
//            internetState=0;
            mSurveyRecordList.clear();
            SatisfactionSurveyFragment.rAdapter.notifyDataSetChanged();
//            Toast.makeText(SatisfacationChildFragment.,"網路不穩，請重新嘗試",Toast.LENGTH_LONG);
            Log.d("andyNull","success");
        }
        Log.d("55125","notifyDataSetChanged");
        SatisfactionSurveyFragment.rAdapter.notifyDataSetChanged();
    }
}
