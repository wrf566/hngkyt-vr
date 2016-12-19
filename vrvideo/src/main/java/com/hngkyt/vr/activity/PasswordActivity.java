package com.hngkyt.vr.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.hngkyt.vr.R;
import com.hngkyt.vr.net.been.DataLogin;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.hngkyt.vr.net.Constants.APPLICATION_JSON_UTF8;

/**
 * Created by wrf on 2016/11/24.
 */

public class PasswordActivity extends TitleBarActivity {


    private EditText mEditTextPassword;
    private EditText mEditTextConfirmPassword;
    private Button mButtonOK;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_password;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.confirm_password);
        mEditTextPassword = (EditText) findViewById(R.id.edittext_change_password_password);
        mEditTextConfirmPassword = (EditText) findViewById(R.id.edittext_change_password_confirm_password);
        mButtonOK = (Button) findViewById(R.id.button_change_password_ok);
        mButtonOK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_change_password_ok:
                String password = getEditTextContent(mEditTextPassword);
                String confirmPassword = getEditTextContent(mEditTextConfirmPassword);

                if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    ToastUtils.showShortToast(this, R.string.password_can_not_be_empty);
                    return;
                }

                if (password.equals(confirmPassword)) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(DataLogin.USERNAME, getIntent().getStringExtra(DataLogin.USERNAME));
                    jsonObject.addProperty(DataLogin.PASSWORD, getEditTextContent(mEditTextPassword));
                    RequestBody requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_UTF8), jsonObject.toString());
                    mRequestService.register(requestBody);
                }

                break;
        }
    }
}
