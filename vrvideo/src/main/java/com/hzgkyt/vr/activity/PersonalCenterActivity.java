package com.hzgkyt.vr.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/11/17.
 */

public class PersonalCenterActivity extends TitleBarActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonAutoPlay;
    private RadioButton mRadioButtonLoopPlay;

    private Switch mSwitch;

    private TextView mTextView;


    @Override
    protected int intLayoutResId() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        setTextViewTitle(getString(R.string.personal_center));


        mSwitch = (Switch) findViewById(R.id.switch_personal_center_stereo);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSPUtils.putBoolean(String.valueOf(R.id.switch_personal_center_stereo), isChecked);
            }
        });

        mSwitch.setChecked(mSPUtils.getBoolean(String.valueOf(R.id.switch_personal_center_stereo), true));

        mTextView = (TextView) findViewById(R.id.textview_personal_center_login_signup);
        mTextView.setOnClickListener(this);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_personal_center);
        mRadioButtonAutoPlay = (RadioButton) findViewById(R.id.radiobutton_personal_center_autoplay_next);
        mRadioButtonLoopPlay = (RadioButton) findViewById(R.id.radiobutton_personal_center_loop);


        mRadioGroup.setOnCheckedChangeListener(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tnitRadioButton(mRadioButtonAutoPlay);
            tnitRadioButton(mRadioButtonLoopPlay);
        }


        setRadioButtonsStatus(mSPUtils.getBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), true));
//        mRadioButtonLoopPlay.setChecked();
    }

    public void setRadioButtonsStatus(boolean isLoop){
        if(isLoop){
            mRadioButtonLoopPlay.setChecked(true);
        }else{
            mRadioButtonAutoPlay.setChecked(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop),mRadioButtonLoopPlay.isSelected());
        outState.putBoolean(String.valueOf(R.id.switch_personal_center_stereo),mSwitch.isChecked());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setRadioButtonsStatus(savedInstanceState.getBoolean(String.valueOf(R.id.radiobutton_personal_center_loop)));
        mSwitch.setChecked(savedInstanceState.getBoolean(String.valueOf(R.id.switch_personal_center_stereo)));

    }

    private void tnitRadioButton(RadioButton radioButton) {
        Drawable drawable = getResources().getDrawable(R.drawable.abc_btn_radio_material).mutate();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.colorAccent));
        radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);


    }

    @Override
    protected void onTitleBarLeftClick() {
        super.onTitleBarLeftClick();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radiobutton_personal_center_autoplay_next:

                mSPUtils.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), false);

                break;
            case R.id.radiobutton_personal_center_loop:

                mSPUtils.putBoolean(String.valueOf(R.id.radiobutton_personal_center_loop), true);

                break;


        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.textview_personal_center_login_signup:
                startActivityOriginal(PersonalCenterActivity.this, LoginActivity.class);
        }
    }
}
