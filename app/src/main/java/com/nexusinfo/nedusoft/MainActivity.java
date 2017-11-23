package com.nexusinfo.nedusoft;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
//        startActivity(intent);
//        finish();

        GetSchoolName schoolName = new GetSchoolName();
        schoolName.execute("");

        Toast.makeText(this, z, Toast.LENGTH_LONG).show();

    }

    String z = "nothing here";

    public class GetSchoolName extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Connection conn = ConnectionClass.getConnection();

                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + ConnectionClass.NEDUSOFT_TABLE);

                String schoolName = "Nothing here";
                while(resultSet.next()){
                    schoolName = resultSet.getString("SchoolName");
                }

                z = schoolName;

            } catch (ClassNotFoundException e) {
                Log.e("ERROR", e.toString());
            } catch (IllegalAccessException e) {
                Log.e("ERROR", e.toString());
            } catch (InstantiationException e) {
                Log.e("ERROR", e.toString());
            } catch (SQLException e) {
                Log.e("ERROR", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
