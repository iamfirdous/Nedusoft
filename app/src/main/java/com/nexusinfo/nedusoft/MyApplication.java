package com.nexusinfo.nedusoft;

import android.app.Application;
import android.os.SystemClock;

import com.nexusinfo.nedusoft.receivers.InternetConnectivityReceiver;

/**
 * Created by lukhman on 11/28/2017.
 */

public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;
    }

    public static synchronized MyApplication getInstance() {
        return mApplication;
    }

    public void setConnectivityListener(InternetConnectivityReceiver.InternetConnectivityReceiverListener listener) {
        InternetConnectivityReceiver.listener = listener;
    }
}
