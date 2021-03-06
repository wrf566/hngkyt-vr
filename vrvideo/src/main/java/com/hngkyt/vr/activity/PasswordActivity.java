package com.hngkyt.vr.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hngkyt.vr.R;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.model.User;
import com.hngkyt.vr.model.ResponseBean;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;

import static com.hngkyt.vr.model.User.TYPE_REGISTER;

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
                confirmPassword();

                break;
        }
    }

    private void confirmPassword() {
        String password = getEditTextContent(mEditTextPassword);
        String confirmPassword = getEditTextContent(mEditTextConfirmPassword);

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            ToastUtils.showShortToast(this, R.string.password_can_not_be_empty);
            return;
        }

        if (password.equals(confirmPassword)) {


            String username = getIntent().getStringExtra(User.USERNAME);
            int type = getIntent().getIntExtra(User.PASSWORD_TYPE,TYPE_REGISTER);
            Logger.e("username = "+username);
            Logger.e("type = "+type);

            Call<ResponseBean> registerCall = mRequestService.register(username, getEditTextContent(mEditTextPassword),type);

            ResultCall<User> resultCall = new ResultCall<>(this, User.class);
            resultCall.setOnCallListener(new ResultCall.OnCallListener() {
                @Override
                public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                    User user = (User) o;
                    Logger.e("user = " + user);
                    saveUserInfo(user);
                    Intent intent = new Intent();
                    intent.putExtra(User.class.getCanonicalName(), user);
                    setResult(RESULT_OK, intent);
                    onBackPressed();


                }

                @Override
                public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                    setResult(RESULT_OK);
                    onBackPressed();
                }
                @Override
                public void onFailure(Call<ResponseBean> call, Throwable t) {

                }
            });
            registerCall.enqueue(resultCall);


        }else{
            ToastUtils.showShortToast(this,R.string.password_not_equals);
        }
    }
}
