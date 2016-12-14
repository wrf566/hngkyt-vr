package com.hngkyt.vr.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hngkyt.vr.R;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by wrf on 2016/11/23.
 */

public class LoginActivity extends TitleBarActivity {

    private static final int MSG_AUTH_CANCEL = 1;
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;
    private Button mButtonSignUp;
    private TextView mTextViewForgetPassword;
    private ImageView mImageViewWeChat;
    private ImageView mImageViewQQ;
    private ImageView mImageViewSina;
    private LoginHandler mLoginHandler = new LoginHandler(this);

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

        mImageViewWeChat.setOnClickListener(this);
        mImageViewQQ.setOnClickListener(this);
        mImageViewSina.setOnClickListener(this);


        mButtonSignUp = (Button) findViewById(R.id.button_login_signup);
        mTextViewForgetPassword = (TextView) findViewById(R.id.textview_login_forget_password);
        mButtonSignUp.setOnClickListener(this);
        mTextViewForgetPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_login_signup:
                startActivityOriginal(this, SignupActivity.class);

                break;
            case R.id.textview_login_forget_password:
                startActivityOriginal(this, MessageVerifyActivity.class);
                break;
            case R.id.imageview_login_wechat:

                break;
            case R.id.imageview_login_qq:
                login(QQ.NAME);
                break;
            case R.id.imageview_login_sina:
                login(SinaWeibo.NAME);

                break;
        }
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

                    Logger.e("plat = "+plat);

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
