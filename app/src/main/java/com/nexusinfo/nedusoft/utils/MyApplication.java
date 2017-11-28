package com.nexusinfo.nedusoft.utils;

import android.app.Application;

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
