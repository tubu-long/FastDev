package com.github.longqiany.fastdev.core.net;

import java.util.Vector;

/**
 * Created by zzz on 15/8/18.
 */
public class ResultFactory {

    private ResultFactory() {

    }

    private final static int ROMAX = 20;  //并发加上，正在引用中的对象。


    private static Vector<ResultObject> vro = new Vector<ResultObject>();

    public static ResultObject getro() {
        if ((vro.size()==0 || vro.get(0) == null) && vro.size() < ROMAX) {
            vro.add(new ResultObject());
        } else if (vro.get(0) == null && vro.size() >= ROMAX) {
            throw new IllegalStateException("pool's max size is" + ROMAX);
        }
        return vro.remove(0);
    }

    public static void recyleAll() {
        if (vro.size() > 0) {
            for (ResultObject ro : vro) {
                recyle(ro);
            }
        }
    }

    public static void recyle(ResultObject ro) {
        ro.setCode(0);
        ro.setMessage("");
        ro.setObject(null);
        ro.setSuccess(null);
        ro.setTotal(0);
        vro.add(ro);
    }
}
