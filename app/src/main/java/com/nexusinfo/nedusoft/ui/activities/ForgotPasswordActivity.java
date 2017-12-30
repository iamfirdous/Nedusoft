package com.nexusinfo.nedusoft.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
import com.nexusinfo.nedusoft.MainActivity;
import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.sql.Connection;
import java.sql.Statement;

import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

public class ForgotPasswordActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private Button buttonSendOTP, buttonVerify, buttonSavePassword;
    private EditText etOTP, etNewPassword, etReNewPassword;
    private TextView tvError, tvCountdown;
    private ProgressBar progressBar;

    private DatabaseConnection databaseConnection;
    private UserModel userModel;
    private String loginName, fatherMobile;

    private String OTP, newPassword, reNewPassword;

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

        etOTP.setEnabled(false);
        buttonVerify.setEnabled(false);
        
        etNewPassword.setVisibility(View.GONE);
        etReNewPassword.setVisibility(View.GONE);
        buttonSavePassword.setVisibility(View.INVISIBLE);

        tvError.setVisibility(View.INVISIBLE);
        tvCountdown.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        showCustomToast(this, "Click \"Send OTP\" to receive OTP on Father's mobile", 2, Gravity.TOP | Gravity.LEFT, 90, 480);

        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt("KEY");

        if (i == 1)
            userModel = (UserModel) bundle.getSerializable("UserModel");
        if (i == 2)
            userModel = LocalDatabaseHelper.getInstance(this).getUser();

        databaseConnection = new DatabaseConnection(userModel.getSchoolDBName());

        loginName = userModel.getUserID();
        fatherMobile = userModel.getFatherMobile();

        if(fatherMobile == null || fatherMobile.equals("")){
            showCustomToast(this, "Father mobile number is not available.\nContact school for changing the password", 2);
            finish();
        }

        showError(InternetConnectivityReceiver.isConnected());

        buttonSendOTP.setOnClickListener(view -> {

            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);

                OTP = generateOTP();
                String message = "OTP:%20" + OTP + ".%20If%20you%20did%20not%20try%20to%20change%20your%20password%20of%20your%20Nedusoft%20account,%20please%20ignore%20this%20message.";
                
                SendOTPTask task = new SendOTPTask();
                String url = "http://bulksms.nexusinfo.com/api/sendmsg.php?user=7676276763&pass=123&sender=IPGHSB&phone=" + fatherMobile + "&text=" + message + "&priority=sdnd&stype=normal";
                task.execute(url);
            }
        });
        
        buttonVerify.setOnClickListener(view -> {
            String enteredOTP = etOTP.getText().toString().trim();
            
            progressBar.setVisibility(View.VISIBLE);
            
            if(enteredOTP.equals(OTP)) {
                    progressBar.setVisibility(View.INVISIBLE);
                    buttonSendOTP.setEnabled(false);
                    buttonVerify.setEnabled(false);
                    etOTP.setEnabled(false);
                    tvCountdown.setVisibility(View.INVISIBLE);
                    
                    etNewPassword.setVisibility(View.VISIBLE);
                    etReNewPassword.setVisibility(View.VISIBLE);
                    buttonSavePassword.setVisibility(View.VISIBLE);
            }
            else {
                    progressBar.setVisibility(View.INVISIBLE);
                    etOTP.setError("Incorrect OTP");
            }
        });
        
        buttonSavePassword.setOnClickListener(view -> {
            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);
                
                ChangePasswordTask task = new ChangePasswordTask();
                task.execute();
            }
        });
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

    class SendOTPTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {
            String s = "";
            try {
                OkHttpClient httpclient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(urls[0])
                        .build();
                Response response = httpclient.newCall(request).execute();

                s = response.message();
            }
            catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
            return s;
        }

        @Override
        protected void onPostExecute(String result) {
            buttonSendOTP.setEnabled(false);
            etOTP.setEnabled(true);
            buttonVerify.setEnabled(true);
            
            startTimer();
        }
    }
    
    public void startTimer() {
        tvCountdown.setVisibility(View.VISIBLE);
        
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvCountdown.setText("Re-send OTP in " + millisUntilFinished / 1000 + " secs");
            }

            public void onFinish() {
                tvCountdown.setVisibility(View.INVISIBLE);
                buttonSendOTP.setEnabled(true);
                buttonSendOTP.setText("Re-send\nOTP");
            }
        }.start();
    }

    public String generateOTP() {
        int randomNum = (int)(Math.random()*9000)+1000;
        return "" + randomNum;
    }

    class ChangePasswordTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            newPassword = etNewPassword.getText().toString().trim();
            reNewPassword = etReNewPassword.getText().toString().trim();

            boolean notEmpty = true, passwordMatched = true, passwordLength = true;

            if(newPassword.equals("")){
                notEmpty = false;
                etNewPassword.setError("Enter a new password");
                cancel(true);
            }
            if(reNewPassword.equals("")){
                notEmpty = false;
                etReNewPassword.setError("Re-enter your new password");
                cancel(true);
            }
            if(!newPassword.equals(reNewPassword)){
                passwordMatched = false;
                etReNewPassword.setError("Passwords do not match");
                etReNewPassword.requestFocus();
                cancel(true);
            }
            if(passwordMatched && !newPassword.equals("")) {
                if (newPassword.length() < 8) {
                    passwordLength = false;
                    etNewPassword.setError("Password should have at least 8 characters");
                    etNewPassword.requestFocus();
                    cancel(true);
                }
            }
            if(notEmpty && passwordMatched && passwordLength){
                loadStart();
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                Log.e("RollNo", loginName);

                Connection conn = databaseConnection.getConnection();

                Statement stmtUpdate = conn.createStatement();

                String sql = "UPDATE " + DatabaseConnection.TABLE_MSTUDENT + " SET " + DatabaseConnection.COL_PASSWORD + " = '" + newPassword + "' WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + loginName + "'";
                stmtUpdate.executeUpdate(sql);
            }
            catch (Exception e){
                publishProgress("Exception");
                cancel(true);
                Log.e("Exception", e.toString());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Exception")){
                showCustomToast(ForgotPasswordActivity.this, "Some error occurred while changing the password.\nPlease try again or check your connection.",1);
                loadFinish();
            }
        }

        @Override
        protected void onPostExecute(String value) {
            showCustomToast(ForgotPasswordActivity.this, "Password changed successfully, login to continue",1);
            LocalDatabaseHelper.getInstance(ForgotPasswordActivity.this).deleteData();
            Intent logout = new Intent(ForgotPasswordActivity.this, MainActivity.class);
            startActivity(logout);
            finish();
        }

        private void loadStart(){
            progressBar.setVisibility(View.VISIBLE);
            etNewPassword.setEnabled(false);
            etReNewPassword.setEnabled(false);
            buttonSavePassword.setEnabled(false);
        }

        private void loadFinish(){
            progressBar.setVisibility(View.INVISIBLE);
            etNewPassword.setEnabled(true);
            etReNewPassword.setEnabled(true);
            buttonSavePassword.setEnabled(true);
        }
    }
}
