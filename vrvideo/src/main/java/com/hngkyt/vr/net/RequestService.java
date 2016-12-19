package com.hngkyt.vr.net;

import com.hngkyt.vr.net.been.ResponseBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 请求类
 * Created by wrf on 2016/12/13.
 */

public interface RequestService {


    @POST(Constants.PATH_SENDCODE)
    Call<ResponseBean> sendCode(@Body RequestBody params);

    @POST(Constants.PATH_LOGIN)
    Call<ResponseBean> login(@Body RequestBody params);

    @POST(Constants.PATH_REGISTER)
    Call<ResponseBean> register(@Body RequestBody params);


}
