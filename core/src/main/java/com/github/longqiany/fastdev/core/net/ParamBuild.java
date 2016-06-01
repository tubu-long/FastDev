package com.github.longqiany.fastdev.core.net;

import com.github.longqiany.fastdev.core.encry.MurmurHash3;
import com.github.longqiany.fastdev.core.utils.Utils;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 走 GetWay 接入层 ，组装入参类
 * Created by zzz on 15/8/14.
 */
public class ParamBuild {

    private static final int BIT_CNT = 16;
    private static final String STUB = "0";
    private static Gson gson = new Gson();
    public static void main(String[] str) {
        String aaa = getSecurity("aaa", 111);
        char[] chars = aaa.toCharArray();
    }


    private static LinkedHashMap map = new LinkedHashMap();

    private void ParamBuild() {
    }

    public static String buildNoParams() {
        HashMap allMap = new HashMap();
        allMap.put("base", map);
        return allMap.toString();
    }

    public static String getSign(Object entity, LinkedHashMap baseMap) {
        String s = gson.toJson(entity);
//        String entityString = Utils.sortMap(entityMap);
        String base = Utils.map2Param(baseMap);
        String param = base + s;
        return getSecurity(param, Req_Stastus.MUM_KEY.getCode());
    }

    public static String getSign(Map map, LinkedHashMap baseMap) {
        String s = gson.toJson(map);
        String base = Utils.map2Param(baseMap);
        String param = base + s;

        return getSecurity(param, Req_Stastus.MUM_KEY.getCode());
    }

    public static String getSign(Map map, int seed, LinkedHashMap baseMap) {
        String s = gson.toJson(map);
        String base = Utils.map2Param(baseMap);
        String param = base + s;
        String security = getSecurity(param, seed);
        return security;
    }


    public static String getSecurity(String key, int seed) {
        if (key == null) {
            throw new IllegalArgumentException("key is not allowed to be null");
        }
        byte[] bytes = null;
        try {
            bytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MurmurHash3.LongPair longPair = new MurmurHash3.LongPair();
        MurmurHash3.murmurhash3_x64_128(bytes, 0, bytes.length, seed, longPair);

        String high = Long.toHexString(longPair.val1);
        String low = Long.toHexString(longPair.val2);

        if (high.length() < BIT_CNT) {
            high = getZero(BIT_CNT - high.length()) + high;
        }
        if (low.length() < BIT_CNT) {
            low = getZero(BIT_CNT - low.length()) + low;
        }

        return high + low;

    }
    private static String getZero(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(STUB);
        }
        return sb.toString();
    }

    /**
     * 只有基础参数
     *
     * @param baseMap 基础参数
     * @return sign返回
     */
    public static String getSign(LinkedHashMap baseMap) {
        String base = Utils.map2Param(baseMap);
        return getSecurity(base, Req_Stastus.MUM_KEY.getCode());
    }


    private Map getParam(Object o) {
        String s = gson.toJson(o);
        Map mo = gson.fromJson(s, Map.class);
        return mo;
    }


}
