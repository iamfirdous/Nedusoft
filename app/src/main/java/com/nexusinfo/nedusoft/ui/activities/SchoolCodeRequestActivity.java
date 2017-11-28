package com.nexusinfo.nedusoft.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nexusinfo.nedusoft.connection.SchoolCodeConnection;
import com.nexusinfo.nedusoft.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchoolCodeRequestActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_code_request);

        progressBar = findViewById(R.id.progressBar_school_code);
        progressBar.setVisibility(View.GONE);
        editText = findViewById(R.id.editText_school_code);
        button = findViewById(R.id.button_school_code_submit);

        button.setOnClickListener(view -> {
//            Intent intent = new Intent(SchoolCodeRequestActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();

            SampleTask task = new SampleTask();
            task.execute();
        });
    }

    class SampleTask extends AsyncTask<String, String, String>{

        String out;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            button.setEnabled(false);
            editText.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                List<String> list = new ArrayList<>();
                Connection conn = SchoolCodeConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + SchoolCodeConnection.NEDUSOFT_TABLE);
                if(rs != null){
                    while (rs.next()){
                        list.add(rs.getString(SchoolCodeConnection.DATABASE_NAME));
                    }
                    publishProgress(list.get(0), list.get(1));
                }
                else {
                    Log.e("Error", "rs is null");
                }
            }
            catch (Exception e){
                Log.e("Exception", e.toString());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(SchoolCodeRequestActivity.this, "Database for 0001: " + values[0] + "\nDatabase for 0002: " + values[1], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            button.setEnabled(true);
            editText.setEnabled(true);
        }
    }
}
