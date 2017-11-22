package com.nexusinfo.nedusoft.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nexusinfo.nedusoft.MainActivity;
import com.nexusinfo.nedusoft.R;

public class SchoolCodeRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_code_request);

        Button button = findViewById(R.id.button_school_code_submit);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(SchoolCodeRequestActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
