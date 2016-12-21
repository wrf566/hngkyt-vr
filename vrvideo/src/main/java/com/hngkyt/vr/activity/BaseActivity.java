package com.hngkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.hngkyt.vr.R;
import com.hngkyt.vr.VRApplication;
import com.hngkyt.vr.net.RequestService;
import com.hzgktyt.vr.baselibrary.utils.SPUtils;

/**
 * Created by wrf on 2016/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public SPUtils mSPUtils;
    public FragmentManager mFragmentManager;
    public VRApplication mVRApplication;

    public RequestService mRequestService;

    public static final int REQUEST_CODE_DEFAULT = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intLayoutResId());
        mSPUtils = new SPUtils(this, SPUtils.class.getName());
        mVRApplication = (VRApplication) getApplication();
        mRequestService = mVRApplication.mRetrofit.create(RequestService.class);
        mFragmentManager = getSupportFragmentManager();

        initView();
    }


    abstract protected int intLayoutResId();

    abstract protected void initView();

    protected void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (tag == null) {
            transaction.replace(R.id.framelayout_fragment, fragment);
        } else {
            transaction.replace(R.id.framelayout_fragment, fragment, tag);
        }
        transaction.commit();
    }

    public void startActivityOriginal(Context context, Class<?> cls) {
        startActivity(new Intent(context, cls));
    }


    public String getEditTextContent(EditText editText) {
        return editText.getText().toString().trim();
    }
}
