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

public class ChangePasswordActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private TextView tvError, tvForgotPassword;
    private EditText etCurrentPassword, etNewPassword, etReNewPassword;
    private Button buttonSave;
    private ProgressBar progressBar;

    private UserModel user;
    private DatabaseConnection databaseConnection;

    private String currentPassword, newPassword, reNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        tvError = findViewById(R.id.textView_error_change_password_activity);
        tvForgotPassword = findViewById(R.id.textView_forgot_password_change_password);
        etCurrentPassword = findViewById(R.id.editText_current_password);
        etNewPassword = findViewById(R.id.editText_new_password);
        etReNewPassword = findViewById(R.id.editText_re_new_password);
        buttonSave = findViewById(R.id.button_save_changed_password);
        progressBar = findViewById(R.id.progressBar_change_password);

        progressBar.setVisibility(View.GONE);

        user = LocalDBHelper.getInstance(this).getUser();
        databaseConnection = new DatabaseConnection(user.getSchoolDBName());

        showError(InternetConnectivityReceiver.isConnected());

        buttonSave.setOnClickListener(view -> {

            if(!InternetConnectivityReceiver.isConnected()){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageForInternet);
            }
            else {
                tvError.setVisibility(View.INVISIBLE);
                Log.e("Available", "Internet Available....  :) :) :D");

                ChangePasswordTask task = new ChangePasswordTask();
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

    class ChangePasswordTask extends AsyncTask<String, String, UserModel> {

        @Override
        protected void onPreExecute() {
            currentPassword = etCurrentPassword.getText().toString().trim();
            newPassword = etNewPassword.getText().toString().trim();
            reNewPassword = etReNewPassword.getText().toString().trim();

            boolean notEmpty = true, passwordMatched = true;

            if(currentPassword.equals("")){
                notEmpty = false;
                etCurrentPassword.setError("Enter your current password");
                cancel(true);
            }
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
                cancel(true);
            }
            if(notEmpty && passwordMatched){
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
            etCurrentPassword.setEnabled(false);
            etNewPassword.setEnabled(false);
            etReNewPassword.setEnabled(false);
            buttonSave.setEnabled(false);
            tvForgotPassword.setEnabled(false);
        }

        private void loadFinish(){
            progressBar.setVisibility(View.INVISIBLE);
            etCurrentPassword.setEnabled(true);
            etNewPassword.setEnabled(true);
            etReNewPassword.setEnabled(false);
            buttonSave.setEnabled(true);
            tvForgotPassword.setEnabled(true);
        }
    }
}
