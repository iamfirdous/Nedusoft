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

import com.nexusinfo.nedusoft.LocalDatabaseHelper;
import com.nexusinfo.nedusoft.MainActivity;
import com.nexusinfo.nedusoft.MyApplication;
import com.nexusinfo.nedusoft.R;
import com.nexusinfo.nedusoft.connection.DatabaseConnection;
import com.nexusinfo.nedusoft.models.UserModel;
import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.nexusinfo.nedusoft.utils.Util.showCustomToast;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvError = findViewById(R.id.textView_error_change_password_activity);
        tvForgotPassword = findViewById(R.id.textView_forgot_password_change_password);
        etCurrentPassword = findViewById(R.id.editText_current_password_change);
        etNewPassword = findViewById(R.id.editText_new_password_change);
        etReNewPassword = findViewById(R.id.editText_re_new_password_change);
        buttonSave = findViewById(R.id.button_save_changed_password_change);
        progressBar = findViewById(R.id.progressBar_change_password);

        progressBar.setVisibility(View.GONE);

        user = LocalDatabaseHelper.getInstance(this).getUser();
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

        etReNewPassword.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                buttonSave.performClick();
                return true;
            }
            return false;
        });

        tvForgotPassword.setOnClickListener(view -> {
            Intent forgotIntent = new Intent(ChangePasswordActivity.this, ForgotPasswordActivity.class);
            forgotIntent.putExtra("KEY", 2);
            startActivity(forgotIntent);
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

    class ChangePasswordTask extends AsyncTask<String, String, UserModel> {

        @Override
        protected void onPreExecute() {
            currentPassword = etCurrentPassword.getText().toString().trim();
            newPassword = etNewPassword.getText().toString().trim();
            reNewPassword = etReNewPassword.getText().toString().trim();

            boolean notEmpty = true, passwordMatched = true, passwordLength = true;

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
        protected UserModel doInBackground(String... strings) {

            try{
                String loginName = user.getUserID();
                int studentID = user.getStudentID();
                Log.e("RollNo", loginName);
                Log.e("CurrentPassword", currentPassword);

                Connection conn = databaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                Statement stmtUpdate = conn.createStatement();

                String query = "SELECT * FROM " + DatabaseConnection.TABLE_MSTUDENT + " WHERE " + DatabaseConnection.COL_STUDENTID + " = " + studentID + " AND " + DatabaseConnection.COL_PASSWORD + " = '" + currentPassword + "'";
                Log.e("Query", query);
                ResultSet rs = stmt.executeQuery(query);

                boolean wrongCredentials = true;

                while (rs.next()){
                    wrongCredentials = false;
                }

                if(wrongCredentials){
                    publishProgress("WrongCredentials");
                    cancel(true);
                }
                else {
                    String sql = "UPDATE " + DatabaseConnection.TABLE_MSTUDENT + " SET " + DatabaseConnection.COL_PASSWORD + " = '" + newPassword + "' WHERE " + DatabaseConnection.COL_STUDENTID + " = " + studentID;
                    stmtUpdate.executeUpdate(sql);
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
                showCustomToast(ChangePasswordActivity.this, "Some error occurred while changing the password.\nPlease try again or check your connection.",1);
                loadFinish();
            }
            if(values[0].equals("WrongCredentials")){
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(R.string.errorMessageIncorrectCurrentPassword);
                loadFinish();
            }
        }

        @Override
        protected void onPostExecute(UserModel userModel) {
            showCustomToast(ChangePasswordActivity.this, "Password changed successfully, you need to login after password change",1);
            LocalDatabaseHelper.getInstance(ChangePasswordActivity.this).deleteData();
            Intent logout = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);
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
            etReNewPassword.setEnabled(true);
            buttonSave.setEnabled(true);
            tvForgotPassword.setEnabled(true);
        }
    }
}
