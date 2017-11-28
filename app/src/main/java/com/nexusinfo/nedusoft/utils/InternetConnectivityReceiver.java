package com.nexusinfo.nedusoft.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nexusinfo.nedusoft.MainActivity;

/**
 * Created by lukhman on 11/28/2017.
 */

public class InternetConnectivityReceiver extends BroadcastReceiver {



    public InternetConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(MyApplication.isActivityVisible()){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = manager.getActiveNetworkInfo();

            boolean isConnected = activeInfo != null && activeInfo.isConnected();

            if(isConnected){
                new MainActivity().showError(true);
            }
            else {
                new MainActivity().showError(false);
            }

        }


//        if(listener != null){
//            listener.onNetworkConnectionChanged(isConnected);
//        }
    }
















}
