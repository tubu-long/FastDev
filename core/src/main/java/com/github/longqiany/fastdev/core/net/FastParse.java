package com.github.longqiany.fastdev.core.net;
/*



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by zzz on 14-12-31.
 *//*

public class FastParse {

    public static ResultObject parseObject(String response, Class<?> clz) {

        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            String value = data.toString();
            resultObject.setMessage(message);
            Object o = JSON.parseObject(value, clz);
            resultObject.setSuccess(true);
            resultObject.setObject(o);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    public static ResultObject parseObject(String response, String key) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
//          String value = data.get(key).toString();
            String value = data.toString();
            resultObject.setMessage(message);
            JSONObject o = JSON.parseObject(value);
            if (o.containsKey(key)) {
                resultObject.setObject(o.get(key));
            }
            resultObject.setSuccess(true);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }

    public static ResultObject parseMap(String response, String key) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            resultObject.setObject(data);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }

    public static ResultObject parseList(String response, String key, Class<?> clz) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        checkToken(code);
        if (code == 0) {
            String value = data.get(key).toString();
//            List<T> list = (List<T>) JSON.parseObject(value, new TypeReference<List<T>>() {});
            List<?> list = JSON.parseArray(value, clz);
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(list);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    public static ResultObject parseList2(String response, String key, Class<?> clz) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        checkToken(code);
        if (code == 0) {

//            String value = data.get(key).toString();
//            List<?> list = JSON.parseArray(value, clz);
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(data);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    public static ResultObject parseList4SearchHouse(String response, String key, Class<?> clz) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        Map<String, Object> data = (Map<String, Object>) map.get("data");
        checkToken(code);
        if (code == 0) {
//            String value = data.get(key).toString();
            Map<String, Object> map2 = JSON.parseObject(data.toString());
            Integer houseCount = (Integer) map2.get("houseCount");
//            Map<String, Object> data2 = (Map<String, Object>) map2.get("houseList");
            Map<String, Object> listAndStr = new HashMap<String, Object>();
            // 如果没有房源list 则不解析
            if (map2.get(key) != null) {
                String value2 = map2.get(key).toString();
                List<?> list = JSON.parseArray(value2, clz);
                listAndStr.put("list", list);
            }

            listAndStr.put("houseCount", houseCount);
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(listAndStr);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    private static <T> List<T> parseList(String response, Class<T> clz) {
//        JSONObject jsonObject = JSON.parseObject(response);
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        Map<String, Object> data = (Map<String, Object>) map.get("data");
//        Map<String, Object> data = (Map<String, Object>) data.get("data");
        String value = data.get("userList").toString();
//        String s = JSON.toJSONString(value);
        List<T> ts = JSON.parseObject(value, new TypeReference<List<T>>() {
        });
        return ts;
    }

    public static ResultObject parseServerTime(String response) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        JSONObject jsonObject = JSON.parseObject(response);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");

        Object data = jsonObject.get("data");
        checkToken(code);
        if (code == 0) {
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            String s = JSON.toJSONString(data);
            JSONObject jsonObject1 = JSON.parseObject(s);
            Long time = (Long) jsonObject1.get("time");
            resultObject.setObject(time);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }

        return resultObject;
    }

    public static ResultObject parseAppId(String response) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        JSONObject jsonObject = JSON.parseObject(response);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        Object data = jsonObject.get("data");
        checkToken(code);
        if (code == 0) {
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            String s = JSON.toJSONString(data);
            JSONObject jsonObject1 = JSON.parseObject(s);
            Long appId = (Long) jsonObject1.get("appId");
            resultObject.setObject(appId);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }

        return resultObject;
    }

    public static ResultObject parseNoClz(String response) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        JSONObject jsonObject = JSON.parseObject(response);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        checkToken(code);
        if (code == 0) {
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }

    */
/**
     * 解析录入房源
     *
     * @param response
     * @return
     *//*

    public static ResultObject parseEnterHouse(String response) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            long productId = Long.valueOf(data.get("productId").toString());
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(productId);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }

    */
/**
     * 解析支付
     *
     * @param response
     * @return
     *//*

    public static ResultObject parseMakeOrder(String response) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            String outradeNo = (String) data.get("outradeNo");
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(outradeNo);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    public static <T> ResultObject parseString(String response, String key, T t) {
        ResultObject resultObject = new ResultObject();
        if (response == null) {
            resultObject.setCode(-1);
            resultObject.setSuccess(false);
            resultObject.setMessage("网络错误");
            return resultObject;
        }
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            T outradeNo = (T) data.get(key);
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
            resultObject.setObject(outradeNo);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }


    //解析首页默认房源
    public static ResultObject parseHouseList(String response, String key) {
        ResultObject resultObject = new ResultObject();
        if (response == null)
            return resultObject;
        Map<String, Object> map = JSON.parseObject(response);
        Integer code = (Integer) map.get("code");
        String message = (String) map.get("message");
//  	    List<Rent> rentList = new ArrayList<Rent>();
        checkToken(code);
        if (code == 0) {
            Map<String, Object> data = (Map<String, Object>) map.get("data");
            JSONArray dataJsonArrayArea = (JSONArray) data.get(key);
            if (dataJsonArrayArea != null) {
                for (Object jsonObject : dataJsonArrayArea) {
                    Map<String, Object> jsonMap = (Map<String, Object>) jsonObject;
                }
            }
            resultObject.setMessage(message);
            resultObject.setSuccess(true);
//      		resultObject.setObject(rentList);
            resultObject.setCode(code);
        } else {
            resultObject.setMessage(message);
            resultObject.setSuccess(false);
            resultObject.setCode(code);
        }
        return resultObject;
    }

//    static long tokenTime = SPUtils.getTokenTime(DingApplication.getContext());

    */
/**
     * 根据code检查，是否是token实效，是则发送广播
     *
     * @param code
     *//*

    private static void checkToken(int code) {
//        if (tokenTime == 0) {
//            tokenTime = SPUtils.getTokenTime(DingApplication.getContext());
//        }

        */
/*long tokenTime = SPUtils.getTokenTime(DingApplication.getContext());

        if (code == 116) {
            Intent mIntent = new Intent("tokenError");
            DingApplication.getContext().sendBroadcast(mIntent);
        } else if (tokenTime != 0 && System.currentTimeMillis() - tokenTime > 22 * 60 * 60 * 1000) {
            Intent mIntent = new Intent("tokenWillError");
            DingApplication.getContext().sendBroadcast(mIntent);
        }*//*

    }
}
*/
