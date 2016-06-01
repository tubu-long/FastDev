package com.github.longqiany.fastdev.core.rxjava;

import com.github.longqiany.fastdev.core.net.HttpClient;
import com.github.longqiany.fastdev.core.net.GsonParser;
import com.github.longqiany.fastdev.core.net.ResultObject;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zzz on 11/16/15.
 */
public class RxUtils {

    static HttpClient client = new HttpClient();


    /**
     * 入参为 map 的请求。
     * @param path
     * @param map
     * @return
     */
    public static Observable<String> postMap(final String path, final Map map,final LinkedHashMap baseMap) {
        return Observable.just(path)
                .map(s -> {
                    try {
                        return client.postMap(path, map,baseMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    /**
     * 入参为 EntityBean 形式的请求
     * @param path
     * @param o
     * @return
     */
    public static Observable<String> postEntity(final String path, final Object o,final LinkedHashMap baseMap) {
        return Observable.just(path)
                .map(s -> {
                    try {
                        return client.postEntity(path, o, baseMap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }


    /**
     * 对应 JsonParser.parseObject
     * @param clz
     * @return
     */
    public static Func1<String , ResultObject> getObject(final Class<?> clz){
        return s -> GsonParser.parse2Entity(s, clz);
    }

    /**
     * 对应 JsonParser.parseObject
     * @param key
     * @return
     */
    public static Func1<String , ResultObject> getObject(final String key){
        return s -> GsonParser.parseObject(s, key);
    }

    /**
     * 对应 JsonParser.parseObject
     * @return
     */
    public static Func1<String , ResultObject> getMap(){
        return s -> GsonParser.parse2Map(s);
    }

    /**
     * 对应 JsonParser.parseList
     * @param key
     * @param t
     * @return
     */
    public static <T>Func1<String , ResultObject> getList(final String key,final T t){
        return s -> GsonParser.parse2List(s, key, t);
    }

    /**
     * 对应 JsonParser.parseObject
     * @return
     */
    public static Func1<String , ResultObject> getNoClz(){
        return s -> GsonParser.parseNoClz(s);
    }


    /**
     * 单界面多接口调用情况，复用 Subscriber 的情况。
     * @param flag
     * @return
     */
    public static Func1<ResultObject , ResultObject> setFlag(final String flag){
        return ro -> {
            ro.setFlag(flag);
            return ro;
        };
    }


    public static Observable.Transformer <ResultObject,ResultObject> mainAsync() {
        return obs -> obs
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
