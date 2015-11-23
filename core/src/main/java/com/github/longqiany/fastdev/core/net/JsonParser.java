package com.github.longqiany.fastdev.core.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.longqiany.fastdev.core.FastDevApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by zzz on 11/16/15.
 */
public class JsonParser {
    private final static int SUCCESS = 100000;
    private static Gson gson;
    public static void main(String[] args) {

    }
    static {
        gson = new Gson();
    }

    public static ResultObject parse2Entity(String response, Class<?> clz) {
        if (response == null) {
            //TODO
            return getROwithNull();
        } else {
            Map map = gson.fromJson(response, Map.class);
            Integer code = ((Double) map.get("code")).intValue();
            String message = (String) map.get("message");
            checkToken(map);
            if (code != SUCCESS) {
                return getErrorRO(code, message);
            } else {
                ResultObject resultObject = ResultFactory.getro();
                Map<String, Object> data = (Map<String, Object>) map.get("data");
                String value = data.toString();
                resultObject.setMessage(message);
                Object o = gson.fromJson(value, clz);
                resultObject.setSuccess(true);
                resultObject.setObject(o);
                resultObject.setCode(code);
                return resultObject;
            }
        }
    }

    public static ResultObject parseNoClz(String response) {
        if (response == null) {
            //TODO
            return getROwithNull();
        } else {
            Map map = gson.fromJson(response, Map.class);
            Integer code = ((Double) map.get("code")).intValue();
            String message = (String) map.get("message");
            checkToken(map);
            if (code != SUCCESS) {
                return getErrorRO(code, message);
            } else {
                ResultObject resultObject = ResultFactory.getro();
                resultObject.setMessage(message);
                resultObject.setSuccess(true);
                resultObject.setCode(code);
                return resultObject;
            }
        }
    }

    /**
     * 用于解析 data 里面只有一个 key value 的情况。 不用定义 Bean 就可以解析数据。
     * @param response
     * @param key
     * @return
     */
    public static ResultObject parseObject(String response, String key) {
        if (response == null) {
            return getROwithNull();
        }
        Map map = gson.fromJson(response, Map.class);
        Integer code = ((Double) map.get("code")).intValue();
        String message = (String) map.get("message");
        checkToken(map);

        if (code != SUCCESS) {
            return getErrorRO(code, message);
        } else {
            ResultObject resultObject = ResultFactory.getro();
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            String value = data.toString();
            resultObject.setMessage(message);

            if (data.containsKey(key)) {
                resultObject.setObject(data.get(key));
            }
            resultObject.setSuccess(true);
            resultObject.setCode(code);
            return resultObject;
        }
    }


    /**
     * 将结果直接处理成 map 返回，适用于没有定义 Bean 的情况。直接拿着 map 操作
     * @param response
     * @return
     */
    public static ResultObject parse2Map(String response) {
        if (response == null) {
            return getROwithNull();
        }
        Map map = gson.fromJson(response, Map.class);
        if (map == null)
            return getROwithNull();
        Integer code = ((Double) map.get("code")).intValue();
        String message = (String) map.get("message");
        checkToken(map);
        if (code != SUCCESS) {
            return getErrorRO(code, message);
        } else {
            ResultObject resultObject = ResultFactory.getro();
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            resultObject.setObject(data);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
            return resultObject;
        }
    }


    /**
     * 用于解析data 中为 jsonArray 的情况，
     * @param response
     * @param key   其中参数 key 为 jsonArray 的 key。
     * @param t     需要转成的对象实体，可以直接 new T();
     * @param <T>   返回的对象 T 泛型
     * @return      ResultObject
     */
    public static <T> ResultObject parse2List(String response ,String key ,final T t) {
        if (response == null) {
            return getROwithNull();
        }
        Map map = gson.fromJson(response, Map.class);
        if (map == null)
            return getROwithNull();
        Integer code = ((Double) map.get("code")).intValue();
        String message = (String) map.get("message");
        checkToken(map);
        if (code != SUCCESS) {
            return getErrorRO(code, message);
        } else {
            ResultObject resultObject = ResultFactory.getro();
            Map data = (Map) map.get("data");
            List value = (List) data.get(key);
            String s = gson.toJson(value);
            List<T> list = gson.fromJson(s, new TypeToken<T>() {
            }.getType());
            resultObject.setObject(list);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
            return resultObject;
        }
    }


    /**
     * data 中 jsonArray 中没有 key 的情况，直接放的 jsonArray
     * @param response
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResultObject parse2ListNokey(String response ,final T t) {
        if (response == null) {
            return getROwithNull();
        }
        Map map = gson.fromJson(response, Map.class);
        if (map == null)
            return getROwithNull();
        Integer code = ((Double) map.get("code")).intValue();
        String message = (String) map.get("message");
        checkToken(map);
        if (code != SUCCESS) {
            return getErrorRO(code, message);
        } else {
            ResultObject resultObject = ResultFactory.getro();
            List value = (List) map.get("data");
            String s = gson.toJson(value);
            List<T> list = gson.fromJson(s, new TypeToken<T>() {
            }.getType());
            resultObject.setObject(list);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
            return resultObject;
        }
    }

    public static <T> ResultObject parse2ListwithNo(String response, String key, String No, final T t) {
        if (response == null) {
            return getROwithNull();
        }
        Map map = gson.fromJson(response, Map.class);
        if (map == null)
            return getROwithNull();
        Integer code = ((Double) map.get("code")).intValue();
        String message = (String) map.get("message");
        checkToken(map);
        if (code != SUCCESS) {
            return getErrorRO(code, message);
        } else {
            ResultObject resultObject = ResultFactory.getro();
            Map data = (Map) map.get("data");
            Integer size = ((Double) data.get(No)).intValue();
            List value = (List) data.get(key);
            String s = gson.toJson(value);
            List<T> list = gson.fromJson(s, new TypeToken<T>() {
            }.getType());
            resultObject.setTotal(size);
            resultObject.setObject(list);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
            return resultObject;
        }
    }






    private static ResultObject getErrorRO(int code, String msg) {
        ResultObject resultObject = ResultFactory.getro();
        resultObject.setCode(code);
        resultObject.setSuccess(false);
        resultObject.setMessage(msg);
        return resultObject;
    }
    private static ResultObject getROwithNull() {
        ResultObject resultObject = ResultFactory.getro();
        resultObject.setCode(-1);
        resultObject.setSuccess(false);
        resultObject.setMessage("网络错误");
        return resultObject;
    }

    private static void checkToken(Map map) {
        if (map.containsKey("token")) {
            String token = (String) map.get("token");
            save(token);
        }
    }

    /**
     * 存 token 到本地
     */
    private static void save(String token) {
        if (!"".equals(token)) {
            SharedPreferences sp = FastDevApplication.getInstance().getSharedPreferences("", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("token", token);
            edit.commit();
        }
    }

}
