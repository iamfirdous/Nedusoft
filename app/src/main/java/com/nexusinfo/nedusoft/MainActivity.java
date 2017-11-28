package com.nexusinfo.nedusoft;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.utils.MyApplication;

public class MainActivity extends AppCompatActivity {

    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvError = findViewById(R.id.textView_error_mainActivity);
        tvError.setVisibility(View.INVISIBLE);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();

        boolean isConnected = activeInfo != null && activeInfo.isConnected();

        if(isConnected){
            showError(true);
        }
        else {
            showError(true);
        }
//        Intent intent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
//        startActivity(intent);
//        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

    public void showError(boolean isConnected) {

        if (!isConnected) {
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.errorMessageForInternet);
        }
        else {
            tvError.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "We indeed have Internet connection!!", Toast.LENGTH_LONG).show();
        }

    }
}
