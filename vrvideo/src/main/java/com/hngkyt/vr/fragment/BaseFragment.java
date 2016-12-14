package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hngkyt.vr.activity.BaseActivity;

/**
 * 基Fragment
 * Created by wrf on 2016/11/21.
 */

public abstract class BaseFragment extends Fragment{
    protected BaseActivity mBaseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(intLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBaseActivity = (BaseActivity) getActivity();
        initView(view);
    }

    /**
     * 初始化布局
     * @return 布局资源id
     */
    abstract protected int intLayoutResId();

    /**
     * 初始化View方法
     * @param view 根V
     */
    abstract protected void initView(View view);


}
