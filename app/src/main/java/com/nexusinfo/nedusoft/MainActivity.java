package com.nexusinfo.nedusoft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusinfo.nedusoft.utils.InternetConnectivityReceiver;
import com.nexusinfo.nedusoft.utils.MyApplication;

public class MainActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener{

    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvError = findViewById(R.id.textView_error_mainActivity);
        tvError.setVisibility(View.INVISIBLE);

        showError(InternetConnectivityReceiver.isConnected());
//        Intent intent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
//        startActivity(intent);
//        finish();

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
            Toast.makeText(this, "We indeed have Internet connection!!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showError(isConnected);
    }
}
