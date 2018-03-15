package com.appspy;

import android.app.Application;
import android.content.Intent;

import com.appspy.Apps.WindowChangeDetectingService;

/**
 * Created by guolu on 2018-03-10.
 */

public class SpyApplication extends Application {

    public static String TopActivityName = null;
    public static String TopActivityPackageName = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent serviceIntent = new Intent(this, WindowChangeDetectingService.class);
        startService(serviceIntent);
    }
}
