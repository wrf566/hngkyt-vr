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
import com.hngkyt.vr.model.ResponseBean;
import com.hngkyt.vr.model.User;
import com.hngkyt.vr.net.ResultCall;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import retrofit2.Call;
import retrofit2.Response;

import static com.hngkyt.vr.model.User.PASSWORD_TYPE;

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
    private User mUser;

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
                sigupIntent.putExtra(PASSWORD_TYPE, User.TYPE_REGISTER);
                startActivityForResult(sigupIntent, REQUEST_CODE_DEFAULT);
                break;
            case R.id.textview_login_forget_password:
                Intent forgetPasswordIntent = new Intent(this, MessageVerifyActivity.class);
                forgetPasswordIntent.putExtra(PASSWORD_TYPE, User.TYPE_FORGET_PASSWORD);
                startActivityForResult(forgetPasswordIntent, REQUEST_CODE_FORGET_PASSWORD);
                break;
            case R.id.imageview_login_wechat:
                thirdPartLogin(Wechat.NAME);
                break;
            case R.id.imageview_login_qq:
                thirdPartLogin(QQ.NAME);
                break;
            case R.id.imageview_login_sina:
                thirdPartLogin(SinaWeibo.NAME);
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
        ResultCall<User> mResultCall = new ResultCall<>(this, User.class);
        mResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                mUser = (User) o;
                Logger.e("mUser =  " + mUser);
                saveUserInfo(mUser);
                Intent intent = new Intent();
                intent.putExtra(User.class.getCanonicalName(), mUser);
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

    public void thirdPartLogin(String platform) {
        Logger.e("platform = "+platform);

        Platform plat = ShareSDK.getPlatform(platform);
        if (plat == null) {
            return;
        }

        if (plat.isAuthValid()) {
            plat.removeAccount(true);
            return;
        }
        plat.authorize();
        //使用SSO授权，通过客户单授权
        plat.SSOSetting(false);
        plat.setPlatformActionListener(new PlatformActionListener() {
            public void onComplete(Platform plat, int action, HashMap<String, Object> res) {

                //用户资源都保存到res
                //通过打印res数据看看有哪些数据是你想要的
                if (action == Platform.ACTION_USER_INFOR) {
                    PlatformDb platDB = plat.getDb();//获取数平台数据DB
                    //通过DB获取各种数据
                    Logger.e("platDB.getToken() = "+platDB.getToken());
                    Logger.e("platDB.getUserGender() = "+platDB.getUserGender());
                    Logger.e("platDB.getUserIcon() = "+platDB.getUserIcon());
                    Logger.e("platDB.getUserId() = "+platDB.getUserId());
                    Logger.e("platDB.getUserName() = "+platDB.getUserName());


                    mUser = new User();
                    mUser.setFaceImgUrl(platDB.getUserIcon());
                    mUser.setUserName(platDB.getUserName());
                    Logger.e("mUser =  " + mUser);
                    Message msg = new Message();
                    msg.what = MSG_AUTH_COMPLETE;
                    msg.arg2 = action;
                    msg.obj = mUser;
                    mLoginHandler.sendMessage(msg);





                }


            }

            public void onError(Platform plat, int action, Throwable t) {
                if (action == Platform.ACTION_USER_INFOR) {
                    Message msg = new Message();
                    msg.what = MSG_AUTH_ERROR;
                    msg.arg2 = action;
                    msg.obj = t;
                    Logger.e("msg = "+msg);
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
                    Logger.e("msg = "+msg);
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
                    Toast.makeText(mLoginActivity, R.string.login_cancel, Toast.LENGTH_SHORT).show();
                }
                break;
                case MSG_AUTH_ERROR: {
                    // 失败
                    Throwable t = (Throwable) msg.obj;
                    String text = "caught error: " + t.getMessage();
                    Logger.e("授权登陆失败信息 = "+text);
                    Toast.makeText(mLoginActivity, R.string.login_failed, Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
                break;
                case MSG_AUTH_COMPLETE: {
                    // 成功
//                    Object[] objs = (Object[]) msg.obj;
//                    String plat = (String) objs[0];

//                    Logger.e("plat = " + plat);
                    User user = (User) msg.obj;
                    mLoginActivity.saveUserInfo(user);
                    Intent intent = new Intent();
                    intent.putExtra(User.class.getCanonicalName(), user);
                    mLoginActivity.setResult(RESULT_OK, intent);
                    mLoginActivity.onBackPressed();
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
