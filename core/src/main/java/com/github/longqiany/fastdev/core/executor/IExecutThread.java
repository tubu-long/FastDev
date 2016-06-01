package com.github.longqiany.fastdev.core.executor;

import rx.Scheduler;

/**
 * Created by zzz on 16/4/26.
 */
public interface IExecutThread  {

    Scheduler getUIScheduler();
}
