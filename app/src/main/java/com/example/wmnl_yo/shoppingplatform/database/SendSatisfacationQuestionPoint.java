package com.example.wmnl_yo.shoppingplatform.database;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfacationQuestionnaireFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.SatisfactionSurveyFragment;
import com.example.wmnl_yo.shoppingplatform.object.SatisfacationQuestionObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WMNL on 2018/3/13.
 */

public class SendSatisfacationQuestionPoint extends AsyncTask<String, Void, String> {
//    private List<SatisfacationQuestionObject.SatisfacationQuestionObjectItem> mQuestionRecordList;

    public static String result;

    @Override
    protected String doInBackground(String... params) {
        Log.d("55125", "sendSatisfacationQuestionPoint.php...");
        String url = Constants.SERVER_URL + "sendSatisfacationQuestionPoint.php";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        result = null;
        try {
            URL tkuUrl = new URL(url);     ////andytemp original
            connection = (HttpURLConnection) tkuUrl.openConnection();

            connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            //傳值
            Log.d("GGandy","GGandy");
            Log.d("andytest", Constants.ACCOUNT);
            //////////////jsonformat
            JSONObject jObj =new JSONObject();
            String accountID =  Constants.ACCOUNT;
            String studentID =  SatisfactionSurveyFragment.childID.trim();
            String questionID = SatisfacationQuestionnaireFragment.totalQuestionID.trim();
            String  scorse = SatisfacationQuestionnaireFragment.totalQuestionPoint.trim();
            String satisfactionID = SatisfacationQuestionnaireFragment.questionID.trim();
            String sugestion = SatisfacationQuestionnaireFragment.suggestion.trim();
            String amountOfQ = String.valueOf(SatisfacationQuestionnaireFragment.amountOfQ);
            jObj.put("accountID",accountID);
            jObj.put("studentID",studentID);
            jObj.put("questionID",questionID);
            jObj.put("scorse",scorse);
            jObj.put("satisfactionID",satisfactionID);
            jObj.put("sugestion",sugestion);
            jObj.put("amountOfQ",amountOfQ);
            String question ="";
            Log.d("andyJson",accountID);
            Log.d("andytestAfterJson", Constants.ACCOUNT);
            Log.d("amount",String.valueOf(SatisfacationQuestionnaireFragment.amountOfQ));
            for(int i=0;i<Integer.valueOf(amountOfQ);i++)
            {
                Log.d("list:", String.valueOf(SatisfacationQuestionnaireFragment.reasonArray[i]));

            }
//            Log.d("list:", String.valueOf(SatisfacationQuestionnaireFragment.reasonArray[0]));
            for(int i = 0; i< SatisfacationQuestionnaireFragment.amountOfQ; i++ )
            {
                question="";
                question="Q"+String.valueOf(i);
                Log.d("quwstion",question);
                if(SatisfacationQuestionnaireFragment.reasonArray[i] != null)
                {
                    Log.d("andylist:", String.valueOf(SatisfacationQuestionnaireFragment.reasonArray[i]));
                    jObj.put(question, SatisfacationQuestionnaireFragment.reasonArray[i]);  //andytemp
                }

                else
                    jObj.put(question,"");
//                Log.d("andyQuestion",question);
//                Log.d("andyReasonArray",SatisfacationQuestionnaireFragment.reasonArray[i]);
////                SatisfacationQuestionnaireFragment.mQuestionRecordList.size();
////                jObj.put(question,SatisfacationQuestionnaireFragment.mQuestionRecordList.get(i).rQuestionReason);  //andytemp
            }



            Log.d("andychildID", SatisfacationQuestionnaireFragment.totalQuestionID.trim());
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("php://input", jObj.toString());

            Log.d("jsonObject",jObj.toString());

            Log.d("andyInput", SatisfacationQuestionnaireFragment.totalQuestionID.trim());
            Log.d("Sendandytotalpoint", SatisfacationQuestionnaireFragment.totalQuestionPoint.trim());
            Log.d("andyInput", Constants.ACCOUNT);
            Log.d("andyInput", SatisfactionSurveyFragment.childID.trim());
            Log.d("andyInput", SatisfacationQuestionnaireFragment.questionID.trim());
            Log.d("andyInput", SatisfacationQuestionnaireFragment.suggestion.trim());
            Log.d("andyGG","GG");
            String query = builder.build().getEncodedQuery();

            OutputStream os = connection.getOutputStream();
            DataOutputStream writer = new DataOutputStream(os);
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
            String jsonString = jObj.toString();
            writer.writeBytes(jsonString);
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
                        SatisfacationQuestionObject.ITEMS.clear();

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

            Log.d("result",result);
            /////////////////////////////////////////////
            return result;

        }
    }
}
