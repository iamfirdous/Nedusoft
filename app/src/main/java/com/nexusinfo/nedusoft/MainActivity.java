package com.nexusinfo.nedusoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;
import com.nexusinfo.nedusoft.ui.activities.SchoolCodeRequestActivity;

public class MainActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener{

    private TextView tvError, tvLoading;
    private ImageView ivRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvError = findViewById(R.id.textView_error_mainActivity);
        tvLoading = findViewById(R.id.textView_loading);
        ivRefresh = findViewById(R.id.imageView_refresh_mainActivity);

        ivRefresh.setVisibility(View.GONE);
        tvError.setVisibility(View.INVISIBLE);

        ivRefresh.setOnClickListener(view -> {
            ivRefresh.setVisibility(View.GONE);
            tvLoading.setVisibility(View.VISIBLE);
            showError(InternetConnectivityReceiver.isConnected());
        });

        showError(InternetConnectivityReceiver.isConnected());

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    private void showError(boolean isConnected) {

        if (!isConnected) {
            tvLoading.setVisibility(View.GONE);
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(R.string.errorMessageForInternet);
            ivRefresh.setVisibility(View.VISIBLE);
        }
        else {
            Intent schoolCodeIntent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
            startActivity(schoolCodeIntent);
            finish();
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showError(isConnected);
    }
}
