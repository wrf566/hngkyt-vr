package com.hngkyt.vr.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.hngkyt.vr.R;
import com.hngkyt.vr.fragment.LocalVRVideoFragment;
import com.hngkyt.vr.model.LocalVideo;

/**
 * Created by wrf on 2016/11/16.
 */

public class LocalVRVideoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    protected int intLayoutResId() {
        return R.layout.include_framelayout_fragment;
    }


    @Override
    protected void initView() {

        LocalVideo localVideo = getIntent().getParcelableExtra(LocalVideo.class.getCanonicalName());

//        Logger.e("localVideo = "+ localVideo);


        //当Activity被系统销毁的时候，回复状态要判断Fragment会不会为空，不然Fragment中onActivityCreated会执行两次
        LocalVRVideoFragment localVRVideoFragment = (LocalVRVideoFragment) mFragmentManager.findFragmentByTag(LocalVRVideoFragment.class.getCanonicalName());
        if (localVRVideoFragment != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            //            Logger.e("添加前 =" + mFragmentManager.getFragments().size());
            //attach、show、replace不清楚有什么区别效果都一样，都能达到恢复状态的效果
            //使用add会报错说已经存在这个fragment了，这是符合逻辑的本来就已经有了
            fragmentTransaction.show(localVRVideoFragment);
            //fragmentTransaction.add(localVRVideoFragment,VRVideoFragment.class.getCanonicalName());
            //ragmentTransaction.attach(localVRVideoFragment);
            //fragmentTransaction.replace(R.id.framelayout_fragment,localVRVideoFragment,VRVideoFragment.class.getCanonicalName());
            //           Logger.e("添加后 =" + mFragmentManager.getFragments().size());

            fragmentTransaction.commit();
        } else {
            replaceFragment(LocalVRVideoFragment.newInstance(localVideo), LocalVRVideoFragment.class.getCanonicalName());
        }

        showStatsBar();


    }


    public void hideStatsBar() {
        View decorView = getWindow().getDecorView();

        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void showStatsBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
