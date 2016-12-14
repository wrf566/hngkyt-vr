package com.hngkyt.vr.net.been;

import com.google.gson.JsonObject;

/**
 * Created by wrf on 2016/12/14.
 */

public class ResponseBean {
    private String msg;
    private String code;
    private JsonObject data;

    public ResponseBean() {
    }

    public ResponseBean(String msg, String code, JsonObject data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
