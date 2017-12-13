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
import android.widget.Toast;

import com.nexusinfo.nedusoft.LocalDBHelper;
import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity  implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private TextView tvError, tvForgotPassword;
    private EditText etLoginName, etPassword;
    private Button buttonLogin;
    private ProgressBar progressBar;

    private UserModel user;
    private DatabaseConnection databaseConnection;

    private String loginName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvError = findViewById(R.id.textView_error_loginActivity);
        tvForgotPassword = findViewById(R.id.textView_forgot_password);
        etLoginName = findViewById(R.id.editText_login_name);
        etPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressBar_login);

        progressBar.setVisibility(View.INVISIBLE);

        user = (UserModel) getIntent().getSerializableExtra("User");
        databaseConnection = new DatabaseConnection(user.getSchoolDBName());

        showError(InternetConnectivityReceiver.isConnected());

        buttonLogin.setOnClickListener(view -> {

            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);
                Log.e("Available", "Internet Available....  :) :) :D");

                LoginTask task = new LoginTask();
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

    class LoginTask extends AsyncTask<String, String, UserModel>{

        @Override
        protected void onPreExecute() {
            loginName = etLoginName.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            boolean notEmpty = true;

            if(loginName.equals("")){
                notEmpty = false;
                etLoginName.setError("Enter your login name");
                cancel(true);
            }
            if(password.equals("")){
                notEmpty = false;
                etPassword.setError("Enter your password");
                cancel(true);
            }
            if(notEmpty){
                loadStart();
            }
        }

        @Override
        protected UserModel doInBackground(String... strings) {

            try{
                Connection conn = databaseConnection.getConnection();
                Statement stmt = conn.createStatement();

                String query = "SELECT * FROM " + DatabaseConnection.TABLE_MSTUDENT + " WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + loginName + "' AND " + DatabaseConnection.COL_PASSWORD + " = '" + password + "' AND " + DatabaseConnection.COL_STATUS + " = 'Regular'";
                ResultSet rs = stmt.executeQuery(query);

                boolean wrongCredentials = true;

                while (rs.next()){
                    user.setUserID(rs.getString(DatabaseConnection.COL_ROLLNO));
                    wrongCredentials = false;
                }

                if(wrongCredentials){
                    publishProgress("WrongCredentials");
                    cancel(true);
                }

            }
            catch (Exception e){
                publishProgress("Exception");
                cancel(true);
                Log.e("Exception", e.toString());
            }

            return user;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(values[0].equals("Exception")){
                Toast.makeText(LoginActivity.this, "Some error occurred.", Toast.LENGTH_LONG).show();
                loadFinish();
            }
            if(values[0].equals("WrongCredentials")){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForWrongCredentials);
                loadFinish();
            }
        }

        @Override
        protected void onPostExecute(UserModel userModel) {
            if (LocalDBHelper.getInstance(LoginActivity.this).addData(user)) {
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                Intent studentDetailsIntent = new Intent(LoginActivity.this, StudentDetailsActivity.class);
                startActivity(studentDetailsIntent);
                finish();
            }
            else {
                Log.e("LocalDBProblem", "Data not added");
            }
        }

        private void loadStart(){
            progressBar.setVisibility(View.VISIBLE);
            etLoginName.setEnabled(false);
            etPassword.setEnabled(false);
            buttonLogin.setEnabled(false);
            tvForgotPassword.setEnabled(false);
        }

        private void loadFinish(){
            progressBar.setVisibility(View.INVISIBLE);
            etLoginName.setEnabled(true);
            etPassword.setEnabled(true);
            buttonLogin.setEnabled(true);
            tvForgotPassword.setEnabled(true);
        }
    }
}
