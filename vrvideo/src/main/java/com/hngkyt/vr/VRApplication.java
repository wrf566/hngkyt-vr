package com.hngkyt.vr;

import android.app.Application;
import android.net.Uri;

import com.hzgktyt.vr.baselibrary.utils.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wrf on 2016/11/17.
 */

public class VRApplication extends Application {
    public Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        if (initLeakCanary())
            return;
        initBugly();
        initLogger();
        Utils.init(this);
        initRetrofit2();
    }

    private void initLogger() {
        if (BuildConfig.LOG_DEBUG) {
            Logger.init(getString(R.string.app_name));
        } else {
            Logger.init(getString(R.string.app_name)).logLevel(LogLevel.NONE);
        }
    }

    private boolean initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return true;
        }
        LeakCanary.install(this);
        // Normal app init code...
        return false;
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

    private void initBugly(){
        CrashReport.initCrashReport(getApplicationContext(), "6d1f71dd7d", BuildConfig.BUGLY);
    }

}
