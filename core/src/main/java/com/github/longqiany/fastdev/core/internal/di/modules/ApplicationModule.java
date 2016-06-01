package com.github.longqiany.fastdev.core.internal.di.modules;

import android.content.Context;

import com.github.longqiany.fastdev.core.FastApplication;
import com.github.longqiany.fastdev.core.executor.IExecutThread;
import com.github.longqiany.fastdev.core.executor.IWorker;
import com.github.longqiany.fastdev.core.executor.impl.UIThread;
import com.github.longqiany.fastdev.core.executor.impl.WorkThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zzz on 16/4/25.
 */
@Module
public class ApplicationModule {
    private FastApplication application;

    public ApplicationModule(FastApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return application;
    }


    @Provides @Singleton
    IExecutThread provideIExecutThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    IWorker provideWorkerThread(WorkThread workThread) {
        return workThread;
    }



}
