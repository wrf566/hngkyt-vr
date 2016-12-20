package com.hngkyt.vr.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HTTP请求回调方法
 * Created by wrf on 2016/12/19.
 */

public class ResultCall<T> implements Callback<ResponseBean> {

    private Context mContext;

    private OnCallListener mOnCallListener;

    private Class<T> mClassOf;


    public ResultCall(Context context, Class<T> classOf) {
        mContext = context;
        mClassOf = classOf;
    }

    public void setOnCallListener(OnCallListener onCallListener) {
        mOnCallListener = onCallListener;
    }

    @Override
    public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
        Logger.e("response.raw() = " + response.raw());
        Logger.e("response.headers() = " + response.headers());
        Logger.e("response.message() = " + response.message());
        Logger.e("response.body() = " + response.body());
        Logger.e("response.code() = " + response.code());
        //        try {这里暂时不打印，正常访问的时候会报空异常
        //            Logger.e("response.errorBody() = " + response.errorBody().string());
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        if (response.isSuccessful()) {
            ResponseBean responseBean = response.body();
            ToastUtils.showShortToast(mContext, responseBean.getMsg());
            JsonObject data = responseBean.getData();
            if (data.size() > 0) {
                Logger.e("非空");
                Gson gson = new Gson();
                T t = gson.fromJson(data.toString(), mClassOf);
                mOnCallListener.onResponse(call, response, t);
            }

        }


    }

    @Override
    public void onFailure(Call<ResponseBean> call, Throwable t) {
        Logger.e("call = " + call.toString());
        Logger.e("Throwable = " + t.getMessage());
        mOnCallListener.onFailure(call, t);
    }


    public interface OnCallListener {
        //这里最后一个参数不能用T，因为这个接口类相当于外部类，访问不到T，所以只能用Obejct替代
        //暂时没找到什么好方法
        void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o);

        void onFailure(Call<ResponseBean> call, Throwable t);
    }

}
