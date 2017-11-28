package com.nexusinfo.nedusoft.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.SchoolCodeConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SchoolCodeRequestActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private ProgressBar progressBar;
    private Button buttonSubmit;
    private EditText etSchoolCode;
    private TextView tvError;

    private String schoolCode, dbName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_code_request);

        progressBar = findViewById(R.id.progressBar_school_code);
        progressBar.setVisibility(View.GONE);
        etSchoolCode = findViewById(R.id.editText_school_code);
        buttonSubmit = findViewById(R.id.button_school_code_submit);
        tvError = findViewById(R.id.textView_error_schoolCodeActivity);

        tvError.setVisibility(View.INVISIBLE);

        showError(InternetConnectivityReceiver.isConnected());

        buttonSubmit.setOnClickListener(view -> {
            boolean connAvailable = InternetConnectivityReceiver.isConnected();

            if(!connAvailable){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);
                Log.e("Available", "Internet Available....  :) :) :D");

                CheckSchoolCodeTask task = new CheckSchoolCodeTask();
                task.execute("");
            }

        });
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

    class CheckSchoolCodeTask extends AsyncTask<String, String, UserModel>{

        boolean code = false;

        @Override
        protected void onPreExecute() {

            schoolCode = etSchoolCode.getText().toString().trim();

            if(schoolCode.equals("")){
                etSchoolCode.setError("Enter the school code to continue");
                cancel(true);
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                buttonSubmit.setEnabled(false);
                etSchoolCode.setEnabled(false);
            }
        }

        @Override
        protected UserModel doInBackground(String... strings) {
            UserModel user = null;
            try{
                if(isCancelled())
                    return null;
                user = new UserModel();
                Connection conn = SchoolCodeConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + SchoolCodeConnection.NEDUSOFT_TABLE + " WHERE " + SchoolCodeConnection.UNIQUE_ID + " = " + Integer.parseInt(schoolCode));
                if(rs != null){
                    int count = 0;
                    while (rs.next()){
                        schoolCode = rs.getString(SchoolCodeConnection.UNIQUE_ID);
                        dbName = rs.getString(SchoolCodeConnection.DATABASE_NAME);
                        count++;
                    }

                    user.setSchoolCode(schoolCode);
                    user.setSchoolDBName(dbName);

                    if(count < 1){
                        code = false;
                        Log.e("Error", "No data for given code");
                    }
                    else {
                        code = true;
                    }
                }
                else {
                    Log.e("Error", "rs is null");
                }
            }
            catch (Exception e){
                Log.e("Exception", e.toString());
            }
            return user;
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(UserModel user) {

            if(code){
                Intent loginIntent = new Intent(SchoolCodeRequestActivity.this, LoginActivity.class);
                loginIntent.putExtra("User", user);
                startActivity(loginIntent);
            }
            else {
                etSchoolCode.setError("School code is incorrect");
            }

            progressBar.setVisibility(View.GONE);
            buttonSubmit.setEnabled(true);
            etSchoolCode.setEnabled(true);
        }
    }
}
