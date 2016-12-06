package com.hzgkyt.vr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hzgkyt.vr.fragment.VRVideoFragment;
import com.hzgkyt.vr.model.VideoItemModel;

/**
 * Created by wrf on 2016/11/16.
 */

public class VRVideoActivity extends TitleBarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        super.initView();
        VideoItemModel videoItemModel = getIntent().getParcelableExtra(VideoItemModel.class.getCanonicalName());
        setTextViewTitle(videoItemModel.getName());
        replaceFragment(VRVideoFragment.newInstance(videoItemModel), null);
    }


}
