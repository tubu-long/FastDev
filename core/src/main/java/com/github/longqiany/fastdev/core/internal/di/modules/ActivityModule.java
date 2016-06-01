package com.github.longqiany.fastdev.core.internal.di.modules;

import android.app.Activity;

import com.github.longqiany.fastdev.core.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zzz on 16/4/26.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity
    Activity activity() {
        return activity;
    }
}
