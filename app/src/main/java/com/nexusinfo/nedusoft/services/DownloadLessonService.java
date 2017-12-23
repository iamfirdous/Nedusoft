package com.nexusinfo.nedusoft.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadLessonService extends Service {
    public DownloadLessonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
