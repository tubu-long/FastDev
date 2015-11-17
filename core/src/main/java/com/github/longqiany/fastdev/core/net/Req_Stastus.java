package com.github.longqiany.fastdev.core.net;

/**
 * Created by zzz on 15/8/17.
 */
public enum Req_Stastus {



    REQ_SUCCESS(100000, "成功"),
    REQ_UNKNOW(100001, "未知错误"),
    OUTDATE(100016, "登录过期"),
    TOKEN(1, "token"),
    BUTLER(2, "ddbutler"),
    MUM_KEY(20150720, "");


    private int code;
    private String msg;

    Req_Stastus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(int code){
        for (Req_Stastus rs: Req_Stastus.values()) {
            if (rs.code == code) {
                return rs.getMsg(code);
            }
        }
        return REQ_UNKNOW.getMsg();
    }
}
