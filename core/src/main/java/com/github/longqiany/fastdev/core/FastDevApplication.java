package com.github.longqiany.fastdev.core;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by zzz on 15/11/12.
 */
public class FastDevApplication extends Application {

    private RefWatcher refWatcher;
    private static FastDevApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (FastDevApplication) getApplicationContext();
        refWatcher = LeakCanary.install(this);
    }

    public static FastDevApplication getInstance() {
        return instance;
    }
    public static RefWatcher getRefWatcher() {
        return FastDevApplication.getInstance().refWatcher;
    }

}
