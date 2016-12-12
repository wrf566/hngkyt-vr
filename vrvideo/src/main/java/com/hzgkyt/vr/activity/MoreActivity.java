package com.hzgkyt.vr.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hzgkyt.vr.fragment.MoreGroupFragment;

/**
 * Created by wrf on 2016/12/2.
 */

public class MoreActivity extends TitleBarActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new MoreGroupFragment(),MoreGroupFragment.class.getCanonicalName());
    }

    @Override
    protected void initView() {
        super.initView();
        String category = getIntent().getStringExtra(MoreActivity.class.getCanonicalName());
        setTextViewTitle(category);
    }
}
