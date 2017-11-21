package com.nexusinfo.nedusoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nexusinfo.nedusoft.ui.activities.LoginActivity;
import com.nexusinfo.nedusoft.ui.activities.SchoolCodeRequestActivity;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
