package com.hngkyt.vr.net;

import com.hngkyt.vr.net.been.ResponseBean;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * 请求类
 * Created by wrf on 2016/12/13.
 */

public interface RequestService {


    @POST(Constants.PATH_SENDCODE)
    Call<ResponseBean> sendCode(String phoneNumber);

    @POST(Constants.PATH_LOGIN)
    Call<ResponseBean> login(String userName,String password);

    @POST(Constants.PATH_REGISTER)
    Call<ResponseBean> register();


}
