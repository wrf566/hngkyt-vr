package com.hzgkyt.vr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity{




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intLayoutResId());




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

    public void startActivityOriginal(Context context, Class<?> cls){
        startActivity(new Intent(context,cls));
    }
}
