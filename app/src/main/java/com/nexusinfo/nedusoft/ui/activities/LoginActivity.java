package com.nexusinfo.nedusoft.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nexusinfo.nedusoft.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, StudentDetailsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
