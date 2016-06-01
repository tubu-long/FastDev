package com.github.longqiany.fastdev.core.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.longqiany.fastdev.core.net.ResultObject;
import com.github.longqiany.fastdev.core.rxjava.RxUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by zzz on 16/3/23.
 */
public class Async2Sync extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Map m = new HashMap<>();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        RxUtils.postMap("ssss",m,linkedHashMap)
                .map(RxUtils.getMap())
//                .map()
                .compose(RxUtils.mainAsync())
                .subscribe(new Subscriber<ResultObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultObject resultObject) {

                    }
                });
    }
}
