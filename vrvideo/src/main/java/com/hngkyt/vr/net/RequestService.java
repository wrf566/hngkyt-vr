package com.hngkyt.vr.net;

import com.hngkyt.vr.net.been.ResponseBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<ResponseBean> sendCode(@Field("phone") String phone);//发送验证码

    @FormUrlEncoded
    @POST(Constants.PATH_LOGIN)
    Call<ResponseBean> login(@Field("username") String username,@Field("password") String password);//登录

    @FormUrlEncoded
    @POST(Constants.PATH_REGISTER)
    Call<ResponseBean> register(@Field("username") String username,@Field("password") String password);//注册

    @GET(Constants.PATH_GETVEDIOCATEGORY)
    Call<ResponseBean> getVedioCategory(@Query("type") int type);//获取视频分类

    @GET(Constants.PATH_GETCATEGORYVEDIOS)
    Call<ResponseBean> getCategoryVedios(@Query("vedioCategoryId") int vedioCategoryId);//分类视频列表


}
