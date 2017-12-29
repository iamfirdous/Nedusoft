package com.nexusinfo.nedusoft.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ForgotPasswordActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private Button buttonSendOTP, buttonVerify, buttonSavePassword;
    private EditText etOTP, etNewPassword, etReNewPassword;
    private TextView tvError, tvCountdown;
    private ProgressBar progressBar;

    private UserModel user;
    private DatabaseConnection databaseConnection;

    private String currentPassword, newPassword, reNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        buttonSendOTP = findViewById(R.id.button_send_otp_forgot);
        buttonVerify = findViewById(R.id.button_verify_otp_forgot);
        buttonSavePassword = findViewById(R.id.button_save_changed_password_forgot);
        etOTP = findViewById(R.id.editText_otp_forgot);
        etNewPassword = findViewById(R.id.editText_new_password_forgot);
        etReNewPassword = findViewById(R.id.editText_re_new_password_forgot);
        tvError = findViewById(R.id.textView_error_forgot_password_activity);
        tvCountdown = findViewById(R.id.textView_countdown_forgot);
        progressBar = findViewById(R.id.progressBar_forgot_password);

        disableAllControls();

        user = LocalDatabaseHelper.getInstance(this).getUser();
        databaseConnection = new DatabaseConnection(user.getSchoolDBName());

        showError(InternetConnectivityReceiver.isConnected());

        buttonSendOTP.setOnClickListener(view -> {

            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);

//                ChangePasswordActivity.ChangePasswordTask task = new ChangePasswordActivity.ChangePasswordTask();
//                task.execute("");
            }

        });


//        Button button = findViewById(R.id.button_send_sms);
//
//        button.setOnClickListener(view -> {
//            HttpAsyncTask task = new HttpAsyncTask();
//            task.execute("http://bulksms.nexusinfo.com/api/sendmsg.php?user=7676276763&pass=123&sender=NDUSFT&phone=8122682821&text=Hello,%20How%20are%20you?&priority=sdnd&stype=normal");
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    private void showError(boolean isConnected) {

        if (!isConnected) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.errorMessageForInternet);
        }
        else {
            tvError.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showError(isConnected);
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

    public void disableAllControls() {
        buttonVerify.setEnabled(false);
        buttonSavePassword.setEnabled(false);

    }
}
