package com.hngkyt.vr.net;

import com.hngkyt.vr.net.been.ResponseBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 请求类
 * Created by wrf on 2016/12/13.
 */

public interface RequestService {
    //凡是和接口相关的videos都拼写成了vedios，这个不是我的锅，因为接口写错了
    //我只是保持统一以免出错

    @FormUrlEncoded
    @POST(Constants.PATH_SENDCODE)
    Call<ResponseBean> sendCode(@Field("phone") String phone);//发送验证码

    @FormUrlEncoded
    @POST(Constants.PATH_LOGIN)
    Call<ResponseBean> login(@Field("username") String username, @Field("password") String password);//登录

    @FormUrlEncoded
    @POST(Constants.PATH_REGISTER)
    Call<ResponseBean> register(@Field("username") String username, @Field("password") String password, @Field("type") int type);//注册

    @GET(Constants.PATH_GETVEDIOCATEGORY)
    Call<ResponseBean> getVedioCategory(@Query("type") int type);//获取视频分类

    @GET(Constants.PATH_GETCATEGORYVEDIOS)
    Call<ResponseBean> getCategoryVedios(@Query("vedioCategoryId") int vedioCategoryId);//分类视频列表

    @FormUrlEncoded
    @POST(Constants.PATH_PLAYAMONT)
    Call<ResponseBean> playAmont(@Field("vedioId") int vedioId, @Field("userId") int userId);//播放次数


    @GET(Constants.PATH_GETVEDIOS)
    Call<ResponseBean> getVedios(@Query("vedioCategoryId") int vedioCategoryId, @Query("sortType") int sortType);//排序视频列表

    @GET(Constants.PATH_GETVEDIODETAIL)
    Call<ResponseBean> getVedioDetail(@Query("vedioId") int id);//视频详情

    @GET(Constants.PATH_GETVERSION)
    Call<ResponseBean> getVersion();//获取版本更新

    @GET(Constants.PATH_GETBANNERS)
    Call<ResponseBean> getBanners();//获取Banner

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    //http://dldir1.qq.com/qqfile/qq/QQ8.8/19876/QQ8.8.exe

}
