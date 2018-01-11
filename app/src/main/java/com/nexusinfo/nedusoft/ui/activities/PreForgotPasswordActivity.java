package com.nexusinfo.nedusoft.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

public class PreForgotPasswordActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvError = findViewById(R.id.textView_error_loginActivity);
        tvForgotPassword = findViewById(R.id.textView_forgot_password_login);
        etLoginName = findViewById(R.id.editText_login_name);
        etPassword = findViewById(R.id.editText_password);
        buttonLogin = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressBar_login);

        progressBar.setVisibility(View.INVISIBLE);

        showCustomToast(this, "Enter your login name to proceed.", 1);
        tvForgotPassword.setVisibility(View.INVISIBLE);
        etPassword.setHint("");
        etPassword.setVisibility(View.GONE);
        buttonLogin.setText("Proceed");

        user = (UserModel) getIntent().getSerializableExtra("User");
        databaseConnection = new DatabaseConnection(user.getSchoolDBName());

        showError(InternetConnectivityReceiver.isConnected());

        buttonLogin.setOnClickListener(view -> {

            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.GONE);
                Log.e("Available", "Internet Available....  :) :) :D");

                ForgotLoginTask task = new ForgotLoginTask();
                task.execute();
            }

        });

        etLoginName.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                buttonLogin.performClick();
                return true;
            }
            return false;
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
            tvError.setVisibility(View.GONE);
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showError(isConnected);
    }

    class ForgotLoginTask extends AsyncTask<String, String, UserModel> {

        @Override
        protected void onPreExecute() {
            loginName = etLoginName.getText().toString().trim();

            boolean notEmpty = true;

            if(loginName.equals("")){
                notEmpty = false;
                etLoginName.setError("Enter your login name");
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

                String query = "SELECT " + DatabaseConnection.COL_ROLLNO + ", "
                                         + DatabaseConnection.COL_FATHERMOBILE + ", "
                                         + DatabaseConnection.COL_STUDENTID +
                                " FROM " + DatabaseConnection.VIEW_STUDENT_DETAILS_FOR_REPORT +
                                " WHERE " + DatabaseConnection.COL_ROLLNO + " = '" + loginName + "' AND "
                                          + DatabaseConnection.COL_STATUS + " = 'Regular' AND "
                                          + DatabaseConnection.COL_CMPID + " = '" + user.getCmpId() + "' AND "
                                          + DatabaseConnection.COL_BRCODE + " = '" + user.getBrCode() + "'";
                ResultSet rs = stmt.executeQuery(query);

                boolean wrongCredentials = true;

                while (rs.next()){
                    user.setUserID(rs.getString(DatabaseConnection.COL_ROLLNO));
                    user.setFatherMobile(rs.getString(DatabaseConnection.COL_FATHERMOBILE));
                    user.setStudentID(rs.getInt(DatabaseConnection.COL_STUDENTID));
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
                showCustomToast(PreForgotPasswordActivity.this, "Some error occurred.",1);
                loadFinish();
            }
            if(values[0].equals("WrongCredentials")){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText("Incorrect login name");
                loadFinish();
            }
        }

        @Override
        protected void onPostExecute(UserModel userModel) {

            Intent forgotIntent = new Intent(PreForgotPasswordActivity.this, ForgotPasswordActivity.class);
            forgotIntent.putExtra("KEY", 1);
            forgotIntent.putExtra("UserModel", userModel);
            startActivity(forgotIntent);
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
