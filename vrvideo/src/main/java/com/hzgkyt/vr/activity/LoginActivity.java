package com.hzgkyt.vr.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hzgkyt.vr.R;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by wrf on 2016/11/23.
 */

public class LoginActivity extends TitleBarActivity {

    private Button mButtonSignUp;
    private TextView mTextViewForgetPassword;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        ShareSDK.initSDK(this);


        setTextViewTitle(R.string.login);
//        XLog.e("adasdasdasdas");
        mButtonSignUp = (Button) findViewById(R.id.button_login_signup);
        mTextViewForgetPassword = (TextView) findViewById(R.id.textview_login_forget_password);
        mButtonSignUp.setOnClickListener(this);
        mTextViewForgetPassword.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_login_signup:
                startActivityOriginal(this,SignupActivity.class);

                break;
            case R.id.textview_login_forget_password:
                startActivityOriginal(this,MessageVerifyActivity.class);

                break;
        }
    }
}
