package com.hngkyt.vr.services;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.orhanobut.logger.Logger;

/**
 * Created by wrf on 2016/12/15.
 */

public class TimerServices extends Service {

    private static final int MILLIS_IN_FUTURE = 60 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 900;


    public static final String ACTION_TIMER_TICK = "action_timer_tick";
    public static final String ACTION_TIMER_FINISH= "action_timer_finish";
    public static final String REMAINING_TIME= "remaining_time";

    private CountDownTimer mCountDownTimer;
    private boolean isFinish = false;

    private LocalBroadcastManager mLocalBroadcastManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Logger.e("onStartCommand");
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {

                private Intent timerIntent = new Intent();

                @Override
                public void onTick(long millisUntilFinished) {
                    isFinish = false;
//                    Logger.e("millisUntilFinished = "+millisUntilFinished);
                    timerIntent.setAction(ACTION_TIMER_TICK);
                    timerIntent.putExtra(REMAINING_TIME,String.valueOf(millisUntilFinished/1000));
                    mLocalBroadcastManager.sendBroadcast(timerIntent);
                }

                @Override
                public void onFinish() {
                    isFinish = true;
                    timerIntent.setAction(ACTION_TIMER_FINISH);
                    mLocalBroadcastManager.sendBroadcast(timerIntent);

                }



            };
            mCountDownTimer.start();
        } else {
            if (isFinish) {
                mCountDownTimer.start();
            }
        }


        return super.onStartCommand(intent, flags, startId);
    }


}
