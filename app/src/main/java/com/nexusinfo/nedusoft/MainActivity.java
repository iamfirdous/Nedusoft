package com.nexusinfo.nedusoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;
import com.nexusinfo.nedusoft.ui.activities.SchoolCodeRequestActivity;
import com.nexusinfo.nedusoft.ui.activities.StudentDetailsActivity;

public class MainActivity extends AppCompatActivity implements InternetConnectivityReceiver.InternetConnectivityReceiverListener {

    private CardView cardView;
    private ImageView ivRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.cardView_no_internet_connection);
        ivRefresh = findViewById(R.id.imageView_refresh_main);

        if(LocalDatabaseHelper.getInstance(this).isDataExist()){

            checkForInternet(InternetConnectivityReceiver.isConnected());

            ivRefresh.setOnClickListener(view -> {
                checkForInternet(InternetConnectivityReceiver.isConnected());
            });
        }
        else {
            Intent schoolCodeIntent = new Intent(MainActivity.this, SchoolCodeRequestActivity.class);
            startActivity(schoolCodeIntent);
            finish();
        }
    }

    public void checkForInternet(boolean isConnected) {
        if(isConnected){
            cardView.setVisibility(View.GONE);

            Intent studentDetailsIntent = new   Intent(MainActivity.this, StudentDetailsActivity.class);
            startActivity(studentDetailsIntent);
            finish();
        }
        else {
            cardView.setVisibility(View.VISIBLE);
            ivRefresh.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        checkForInternet(isConnected);
    }
}
