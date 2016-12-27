package com.hngkyt.vr.model;

/**
 * Created by wrf on 2016/12/19.
 */

public class DataSendCode {
    private  String code;
    private  String  phone;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "DataSendCode{" +
                "code=" + code +
                ", phone=" + phone +
                '}';
    }
}
