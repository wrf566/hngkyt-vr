package com.hngkyt.vr.activity;

import android.view.View;
import android.widget.Button;

import com.hngkyt.vr.R;

/**
 * Created by wrf on 2016/11/24.
 */

public class MessageVerifyActivity1 extends TitleBarActivity {


    private Button mButtonOK;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_message_verify;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.message_verify);

        mButtonOK = (Button) findViewById(R.id.button_message_verify_ok);

        mButtonOK.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_message_verify_ok:
                startActivityOriginal(this,PasswordActivity.class);
                break;
        }
    }
}
