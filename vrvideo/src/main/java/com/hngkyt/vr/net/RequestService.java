package com.hngkyt.vr.net;

import com.hngkyt.vr.net.been.ResponseBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 请求类
 * Created by wrf on 2016/12/13.
 */

public interface RequestService {

    //    这里用标准的Post请求访问替代了
    //    @POST(Constants.PATH_SENDCODE)
    //    Call<ResponseBean> sendCode(@Body RequestBody params);

//    @POST(Constants.PATH_LOGIN)
//    Call<ResponseBean> login(@Body RequestBody params)

//    @POST(Constants.PATH_REGISTER)
//    Call<ResponseBean> register(@Body RequestBody params);

    @FormUrlEncoded
    @POST(Constants.PATH_SENDCODE)
    Call<ResponseBean> sendCode(@Field("phone") String phone);

    @FormUrlEncoded
    @POST(Constants.PATH_LOGIN)
    Call<ResponseBean> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST(Constants.PATH_REGISTER)
    Call<ResponseBean> register(@Field("username") String username,@Field("password") String password);


}
