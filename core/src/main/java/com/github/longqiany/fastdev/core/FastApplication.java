package com.github.longqiany.fastdev.core;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by zzz on 15/11/12.
 */
public class FastApplication extends Application {

    private RefWatcher refWatcher;
    private static FastApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = (FastApplication) getApplicationContext();
        refWatcher = LeakCanary.install(this);
    }

    public static FastApplication getInstance() {
        return instance;
    }
    public static RefWatcher getRefWatcher() {
        return FastApplication.getInstance().refWatcher;
    }

}
