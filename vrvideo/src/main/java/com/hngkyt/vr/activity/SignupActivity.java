package com.hngkyt.vr.activity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hngkyt.vr.R;

/**
 * Created by wrf on 2016/11/22.
 */

public class SignupActivity extends TitleBarActivity{


    private Spinner mSpinnerReligions;

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(R.string.signup);

        mSpinnerReligions = (Spinner) findViewById(R.id.spinner_signup_religion);
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<>(this,R.layout.item_spinner,getResources().getStringArray(R.array.array_religions));

        mSpinnerReligions.setAdapter(stringArrayAdapter);

        mSpinnerReligions.setAdapter(stringArrayAdapter);



    }
}
