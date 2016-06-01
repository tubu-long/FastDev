package com.github.longqiany.fastdev.core.internal.di;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by zzz on 16/4/25.
 */
public abstract class UserCase {

    private Subscription subscription = Subscriptions.empty();

    public UserCase(Subscription subscription) {
        AndroidSchedulers.mainThread();
        Schedulers.io();
        this.subscription = subscription;
    }
}
