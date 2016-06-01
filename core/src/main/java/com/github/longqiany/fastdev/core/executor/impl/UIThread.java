package com.github.longqiany.fastdev.core.executor.impl;

import com.github.longqiany.fastdev.core.executor.IExecutThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zzz on 16/4/26.
 */
@Singleton
public class UIThread implements IExecutThread {

    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getUIScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
