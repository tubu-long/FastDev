package com.github.longqiany.fastdev.core.internal.di.components;

import android.app.Activity;

import com.github.longqiany.fastdev.core.internal.di.PerActivity;
import com.github.longqiany.fastdev.core.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by zzz on 16/5/3.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class ,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}
