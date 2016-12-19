package com.hngkyt.vr.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wrf on 2016/12/15.
 */

public class TimerBroadcastReceiver extends BroadcastReceiver{

    private OnTimerBroadcastReceiver mOnTimerBroadcastReceiver;

    public void setOnTimerBroadcastReceiver(OnTimerBroadcastReceiver onTimerBroadcastReceiver) {
        mOnTimerBroadcastReceiver = onTimerBroadcastReceiver;
    }

    public interface OnTimerBroadcastReceiver {
       void onReceive(Context context, Intent intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mOnTimerBroadcastReceiver.onReceive(context,intent);
    }
}
