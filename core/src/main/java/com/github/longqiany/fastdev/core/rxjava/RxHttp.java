package com.github.longqiany.fastdev.core.rxjava;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by zzz on 11/16/15.
 */
public class RxHttp {

    private static final OkHttpClient client = new OkHttpClient();

    public RxHttp(/*BaseInfos mInfos*/) {
        super();
        client.setConnectTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(20, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
    }



}
