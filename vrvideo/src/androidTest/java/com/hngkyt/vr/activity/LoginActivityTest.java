package com.hngkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.orhanobut.logger.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by wrf on 2017/1/5.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class LoginActivityTest {

    private static final String USERNAME = "15675858695";
    private static final String PASSWORD = "w";

    private static final String PACKAG_ENAME = "com.hngkyt.vr";
    private static final int LAUNCH_TIMEOUT = 5 * 1000;
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();


        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAG_ENAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);


    }

    @Test
    public void openPersonalActivity() {


        mDevice.wait(Until.hasObject(By.res(PACKAG_ENAME,"imageview_main_personal_center")), LAUNCH_TIMEOUT);

        Logger.e("执行没执行");
        UiObject uiObject = mDevice.findObject(new UiSelector().resourceId("imageview_main_personal_center"));
        Logger.e("执行没执行2");

        //        mDevice.findObjects(By.)
        //        uiObject2.click();
        try {

            uiObject.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }


    }


}
