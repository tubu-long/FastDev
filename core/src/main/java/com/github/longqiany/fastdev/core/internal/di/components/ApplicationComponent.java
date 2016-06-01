package com.github.longqiany.fastdev.core.internal.di.components;

import android.content.Context;

import com.github.longqiany.fastdev.core.base.BaseActivity;
import com.github.longqiany.fastdev.core.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zzz on 16/4/25.
 */
@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //扩展到子Module
    Context getContext();

}
