package com.nexusinfo.nedusoft.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nexusinfo.nedusoft.R;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        Button button = findViewById(R.id.button_send_sms);
//
//        button.setOnClickListener(view -> {
//            HttpAsyncTask task = new HttpAsyncTask();
//            task.execute("http://bulksms.nexusinfo.com/api/sendmsg.php?user=7676276763&pass=123&sender=NDUSFT&phone=8122682821&text=Hello,%20How%20are%20you?&priority=sdnd&stype=normal");
//        });
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return httpRequestResponse(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }

    //For HttpAsync Functions: sending requests and receiving responses
    public static String httpRequestResponse(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            OkHttpClient httpclient = new OkHttpClient();

            // make GET request to the given URL
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = httpclient.newCall(request).execute();

//            // receive response as inputStream
//            inputStream = response.getEntity().getContent();
//
//            // convert InputStream to string
//            if(inputStream != null)
//                result = convertInputStreamToString(inputStream);
//            else
//                result = "InputStream did not work";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }
}
