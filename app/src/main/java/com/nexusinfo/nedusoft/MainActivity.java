package com.nexusinfo.nedusoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nexusinfo.nedusoft.ui.activities.SchoolCodeRequestActivity;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(new LocalDBHelper(this).isDataExist()){
            Intent studentDetailsIntent = new Intent(MainActivity.this, StudentDetailsActivity.class);
            startActivity(studentDetailsIntent);
            finish();
        }
        else {
            Intent schoolCodeIntent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
            startActivity(schoolCodeIntent);
            finish();
        }
    }
}
