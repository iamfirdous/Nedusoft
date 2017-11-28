package com.nexusinfo.nedusoft.utils;

import android.app.Application;

/**
 * Created by lukhman on 11/28/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static boolean activityVisible;

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;

    }

    public static void activityPaused() {
        activityVisible = false;

    }
}
