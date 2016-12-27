package com.hngkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hngkyt.vr.R;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.DataSendCode;
import com.hngkyt.vr.net.been.DataUser;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.receiver.TimerBroadcastReceiver;
import com.hngkyt.vr.services.TimerServices;
import com.hzgktyt.vr.baselibrary.utils.RegexUtils;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wrf on 2016/11/22.
 */

public class MessageVerifyActivity extends TitleBarActivity implements TimerBroadcastReceiver.OnTimerBroadcastReceiver {


    //    private Spinner mSpinnerReligions;

    private EditText mEditTextPhoneNumber;
    private EditText mEditTextVerifyCode;
    private Button mButtonSendCode;
    private Button mButtonOK;
    private TimerBroadcastReceiver mTimerBroadcastReceiver;
    private DataSendCode mDataSendCode;//验证码返回结果bean


    @Override
    protected int intLayoutResId() {
        return R.layout.activity_message_verify;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.message_verify);


        mEditTextPhoneNumber = (EditText) findViewById(R.id.edittext_message_verify_phonenumber);
        mEditTextVerifyCode = (EditText) findViewById(R.id.edittext_message_verify_code);

        mButtonSendCode = (Button) findViewById(R.id.button_message_verify_send);
        mButtonOK = (Button) findViewById(R.id.button_message_verify_ok);


        mButtonSendCode.setOnClickListener(this);
        mButtonOK.setOnClickListener(this);


        mTimerBroadcastReceiver = new TimerBroadcastReceiver();
        mTimerBroadcastReceiver.setOnTimerBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TimerServices.ACTION_TIMER_TICK);
        intentFilter.addAction(TimerServices.ACTION_TIMER_FINISH);
        LocalBroadcastManager.getInstance(this).registerReceiver(mTimerBroadcastReceiver, intentFilter);


    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mTimerBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.button_message_verify_send:
                if (RegexUtils.isMobileSimple(getEditTextContent(mEditTextPhoneNumber))) {
                    sendCode();
                } else {
                    ToastUtils.showShortToast(this, R.string.phonenumber_format_is_not_correct);

                }

                break;
            case R.id.button_message_verify_ok:
                if (mDataSendCode != null) {
                    if (getEditTextContent(mEditTextVerifyCode).equals(mDataSendCode.getCode())) {
                        Intent intent = new Intent(this, PasswordActivity.class);
                        intent.putExtra(DataUser.USERNAME, getEditTextContent(mEditTextPhoneNumber));
                        intent.putExtra(DataUser.PASSWORD_TYPE, getIntent().getIntExtra(DataUser.PASSWORD_TYPE,DataUser.TYPE_REGISTER));
                        startActivityForResult(intent, REQUEST_CODE_DEFAULT);
                    } else {
                        ToastUtils.showShortToast(this, R.string.verify_code_incorrect);
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_DEFAULT) {
                setResult(RESULT_OK,data);
                onBackPressed();
            }
        }
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
        //        JsonObject jsonObject = new JsonObject();
        //        jsonObject.addProperty(PHONE, getEditTextContent(mEditTextPhoneNumber));
        //        RequestBody requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_UTF8), jsonObject.toString());
        //        Call<ResponseBean> responseBeanCall = mRequestService.sendCode(requestBody);
        Call<ResponseBean> responseBeanCall = mRequestService.sendCode(getEditTextContent(mEditTextPhoneNumber));
        ResultCall<DataSendCode> resultCall = new ResultCall<>(this, DataSendCode.class);
        resultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
//                if (o != null) {
                    startService(new Intent(MessageVerifyActivity.this, TimerServices.class));
                    mDataSendCode = (DataSendCode) o;
                    Logger.e("mDataSendCode =  " + mDataSendCode);
//                }

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });
        responseBeanCall.enqueue(resultCall);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case TimerServices.ACTION_TIMER_TICK:
                String time = intent.getStringExtra(TimerServices.REMAINING_TIME);
                mButtonSendCode.setText(time);
                mButtonSendCode.setEnabled(false);
                break;
            case TimerServices.ACTION_TIMER_FINISH:
                mButtonSendCode.setText(R.string.send_verify_code);
                mButtonSendCode.setEnabled(true);
                break;
        }
    }
}
