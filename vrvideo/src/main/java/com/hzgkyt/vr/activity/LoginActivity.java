package com.hzgkyt.vr.activity;

import android.view.View;
import android.widget.TextView;

import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/11/23.
 */

public class LoginActivity extends TitleBarActivity {

    private TextView mTextView;
    private TextView mTextViewForgetPassword;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.login);
//        XLog.e("adasdasdasdas");
        mTextView = (TextView) findViewById(R.id.textview_login_signup);
        mTextViewForgetPassword = (TextView) findViewById(R.id.textview_login_forget_password);
        mTextView.setOnClickListener(this);
        mTextViewForgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.textview_login_signup:
                startActivityOriginal(this,SignupActivity.class);

                break;
            case R.id.textview_login_forget_password:
                startActivityOriginal(this,MessageVerifyActivity.class);

                break;
        }
    }
}
