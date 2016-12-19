package com.hngkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hngkyt.vr.R;
import com.hngkyt.vr.receiver.TimerBroadcastReceiver;
import com.hngkyt.vr.services.TimerServices;
import com.orhanobut.logger.Logger;

/**
 * Created by wrf on 2016/11/22.
 */

public class SignupActivity extends TitleBarActivity implements TimerBroadcastReceiver.OnTimerBroadcastReceiver {


    //    private Spinner mSpinnerReligions;

    private EditText mEditTextPhoneNumber;
    private EditText mEditTextVerifyCode;

    private Button mButtonSendCode;
    private Button mButtonOK;

    private TimerBroadcastReceiver mTimerBroadcastReceiver;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_message_verify;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.signup);

        mEditTextPhoneNumber = (EditText) findViewById(R.id.edittext_message_verify_phonenumber);
        mEditTextVerifyCode = (EditText) findViewById(R.id.edittext_message_verify_code);

        mButtonSendCode = (Button) findViewById(R.id.button_message_verify_send);
        mButtonOK = (Button) findViewById(R.id.button_message_verify_ok);


        mButtonSendCode.setOnClickListener(this);
        mButtonOK.setOnClickListener(this);

        //        mSpinnerReligions = (Spinner) findViewById(R.id.spinner_signup_religion);
        //        ArrayAdapter<String> stringArrayAdapter =
        //                new ArrayAdapter<>(this,R.layout.item_spinner,getResources().getStringArray(R.array.array_religions));
        //
        //        mSpinnerReligions.setAdapter(stringArrayAdapter);
        //
        //        mSpinnerReligions.setAdapter(stringArrayAdapter);

        mTimerBroadcastReceiver = new TimerBroadcastReceiver();
        mTimerBroadcastReceiver.setOnTimerBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TimerServices.ACTION_TIMER_TICK);
        intentFilter.addAction(TimerServices.ACTION_TIMER_FINISH);
        LocalBroadcastManager.getInstance(this).registerReceiver(mTimerBroadcastReceiver,intentFilter);


    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mTimerBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_message_verify_send:
                startService(new Intent(this,TimerServices.class));
                break;
            case R.id.button_message_verify_ok:
                break;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case TimerServices.ACTION_TIMER_TICK:
                String time = intent.getStringExtra(TimerServices.REMAINING_TIME);
                mButtonSendCode.setText(time);
                break;
            case TimerServices.ACTION_TIMER_FINISH:
                Logger.e("完成了！");
                mButtonSendCode.setText(R.string.send_verify_code);
                break;
        }
    }
}
