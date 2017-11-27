package com.nexusinfo.nedusoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nexusinfo.nedusoft.ui.activities.SchoolCodeRequestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
        startActivity(intent);
        finish();

    }
}
