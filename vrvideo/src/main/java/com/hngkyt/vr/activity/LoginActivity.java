package com.hngkyt.vr.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hngkyt.vr.R;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.DataUser;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import retrofit2.Call;
import retrofit2.Response;

import static com.hngkyt.vr.net.been.DataUser.PASSWORD_TYPE;

/**
 * 登陆页面
 * Created by wrf on 2016/11/23.
 */

public class LoginActivity extends TitleBarActivity {

    private static final int MSG_AUTH_CANCEL = 1;
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;
    private static final int REQUEST_CODE_FORGET_PASSWORD = 2;
    private Button mButtonSignUp;
    private Button mButtonLogin;
    private TextView mTextViewForgetPassword;
    private ImageView mImageViewWeChat;
    private ImageView mImageViewQQ;
    private ImageView mImageViewSina;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private LoginHandler mLoginHandler = new LoginHandler(this);
    private DataUser mDataUser;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        ShareSDK.initSDK(this);


        setTextViewTitle(R.string.login);

        mImageViewWeChat = (ImageView) findViewById(R.id.imageview_login_wechat);
        mImageViewQQ = (ImageView) findViewById(R.id.imageview_login_qq);
        mImageViewSina = (ImageView) findViewById(R.id.imageview_login_sina);
        mEditTextUsername = (EditText) findViewById(R.id.edittext_login_username);
        mEditTextPassword = (EditText) findViewById(R.id.edittext_login_password);

        mImageViewWeChat.setOnClickListener(this);
        mImageViewQQ.setOnClickListener(this);
        mImageViewSina.setOnClickListener(this);


        mButtonSignUp = (Button) findViewById(R.id.button_login_signup);
        mButtonLogin = (Button) findViewById(R.id.button_login_login);
        mTextViewForgetPassword = (TextView) findViewById(R.id.textview_login_forget_password);
        mButtonSignUp.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mTextViewForgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_login_signup:
                Intent sigupIntent = new Intent(this, MessageVerifyActivity.class);
                sigupIntent.putExtra(PASSWORD_TYPE, DataUser.TYPE_REGISTER);
                startActivityForResult(sigupIntent, REQUEST_CODE_DEFAULT);
                break;
            case R.id.textview_login_forget_password:
                Intent forgetPasswordIntent = new Intent(this, MessageVerifyActivity.class);
                forgetPasswordIntent.putExtra(PASSWORD_TYPE, DataUser.TYPE_FORGET_PASSWORD);
                startActivityForResult(forgetPasswordIntent, REQUEST_CODE_FORGET_PASSWORD);
                break;
            case R.id.imageview_login_wechat:

                break;
            case R.id.imageview_login_qq:
                login(QQ.NAME);
                break;
            case R.id.imageview_login_sina:
                login(SinaWeibo.NAME);

                break;
            case R.id.button_login_login:
                login();


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_DEFAULT:
                    setResult(RESULT_OK, data);
                    onBackPressed();
                    break;
            }
        }
    }

    private void login() {
        String username = getEditTextContent(mEditTextUsername);
        String password = getEditTextContent(mEditTextPassword);


        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShortToast(this, R.string.username_can_not_be_nul);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast(this, R.string.password_can_not_be_empty);
            return;

        }
        Call<ResponseBean> loginCall = mRequestService.login(username, password);
        ResultCall<DataUser> mResultCall = new ResultCall<>(this, DataUser.class);
        mResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                mDataUser = (DataUser) o;
                Logger.e("mDataUser =  " + mDataUser);
                saveUserInfo(mDataUser);
                Intent intent = new Intent();
                intent.putExtra(DataUser.class.getCanonicalName(), mDataUser);
                setResult(RESULT_OK, intent);
                onBackPressed();
            }

            @Override
            public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });

        loginCall.enqueue(mResultCall);

    }

    public void login(String platform) {

        Platform plat = ShareSDK.getPlatform(platform);
        if (plat == null) {
            return;
        }

        if (plat.isAuthValid()) {
            plat.removeAccount(true);
            return;
        }

        //使用SSO授权，通过客户单授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(new PlatformActionListener() {
            public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_COMPLETE;
                    msg.arg2 = action;
                    msg.obj = new Object[]{plat.getName(), res};
                    mLoginHandler.sendMessage(msg);
                }
            }

            public void onError(Platform plat, int action, Throwable t) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_ERROR;
                    msg.arg2 = action;
                    msg.obj = t;
                    mLoginHandler.sendMessage(msg);
                }
                t.printStackTrace();
            }

            public void onCancel(Platform plat, int action) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_CANCEL;
                    msg.arg2 = action;
                    msg.obj = plat;
                    mLoginHandler.sendMessage(msg);
                }
            }
        });
        plat.showUser(null);
    }

    static class LoginHandler extends Handler {

        private WeakReference<LoginActivity> mLoginActivityWeakReference;
        private LoginActivity mLoginActivity;


        LoginHandler(LoginActivity loginActivity) {
            mLoginActivityWeakReference = new WeakReference<>(loginActivity);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLoginActivity = mLoginActivityWeakReference.get();
            switch (msg.what) {
                case MSG_AUTH_CANCEL: {
                    // 取消
                    Toast.makeText(mLoginActivity, "canceled", Toast.LENGTH_SHORT).show();
                }
                break;
                case MSG_AUTH_ERROR: {
                    // 失败
                    Throwable t = (Throwable) msg.obj;
                    String text = "caught error: " + t.getMessage();
                    Toast.makeText(mLoginActivity, text, Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
                break;
                case MSG_AUTH_COMPLETE: {
                    // 成功
                    Object[] objs = (Object[]) msg.obj;
                    String plat = (String) objs[0];

                    Logger.e("plat = " + plat);

                    //                    @SuppressWarnings("unchecked")
                    //                    HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                    //                    if (loginListener != null && loginListener.onLogin(plat, res)) {
                    //                        RegisterPage.setOnLoginListener(loginListener);
                    //                        RegisterPage.setPlatform(plat);
                    //                        Intent intent = new Intent(context, RegisterPage.class);
                    //                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //                        mLoginActivity.startActivity(intent);
                    //                    }
                }
                break;
            }

        }
    }
}
