package com.hzgkyt.vr.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.fragment.VRVideoFragment;
import com.hzgkyt.vr.model.VideoItemModel;

/**
 * Created by wrf on 2016/11/16.
 */

public class VRVideoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int intLayoutResId() {
        return R.layout.include_framelayout_fragment;
    }

    @Override
    protected void initView() {
        VideoItemModel videoItemModel = getIntent().getParcelableExtra(VideoItemModel.class.getCanonicalName());
        replaceFragment(VRVideoFragment.newInstance(videoItemModel), null);
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
