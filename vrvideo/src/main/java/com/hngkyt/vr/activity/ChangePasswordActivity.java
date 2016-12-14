package com.hngkyt.vr.activity;

import com.hngkyt.vr.R;

/**
 * Created by wrf on 2016/11/24.
 */

public class ChangePasswordActivity extends TitleBarActivity {



    @Override
    protected int intLayoutResId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.change_password);


    }
}
