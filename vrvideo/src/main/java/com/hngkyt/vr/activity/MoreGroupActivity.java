package com.hngkyt.vr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hngkyt.vr.fragment.MoreGroupFragment;

/**
 * Created by wrf on 2016/12/2.
 */

public class MoreGroupActivity extends TitleBarActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new MoreGroupFragment(),MoreGroupFragment.class.getCanonicalName());
    }

    @Override
    protected void initView() {
        super.initView();
        String category = getIntent().getStringExtra(MoreGroupActivity.class.getCanonicalName());
        setTextViewTitle(category);
    }
}
