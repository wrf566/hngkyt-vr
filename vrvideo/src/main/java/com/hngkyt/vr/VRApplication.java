package com.hngkyt.vr;

import android.app.Application;
import android.net.Uri;

import com.hzgktyt.vr.baselibrary.utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wrf on 2016/11/17.
 */

public class VRApplication extends Application{
    public Retrofit mRetrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initRetrofit2();
    }

    private void initRetrofit2() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.encodedAuthority(BuildConfig.AUTHORITY);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(builder.toString())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
