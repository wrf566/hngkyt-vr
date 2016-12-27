package com.hngkyt.vr.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hngkyt.vr.R;

/**
 * Created by wrf on 2016/11/18.
 */

public  abstract class TitleBarActivity extends BaseActivity implements View.OnClickListener {


    protected TextView mTextViewTitle;
    protected ImageView mImageViewTitleBarLeft;
    protected ImageView mImageViewBarRight;


    @Override
    protected int intLayoutResId() {
        return R.layout.activity_base_titlebar;
    }

    @Override
    protected void initView() {
        mImageViewTitleBarLeft = (ImageView) findViewById(R.id.imageview_titlebar_left);
        mImageViewBarRight = (ImageView) findViewById(R.id.imageview_titlebar_right);
        mTextViewTitle = (TextView) findViewById(R.id.textview_titlebar_title);


        mImageViewTitleBarLeft.setOnClickListener(this);
        mImageViewBarRight.setOnClickListener(this);
        mTextViewTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageview_titlebar_left:
                onTitleBarLeftClick();
                break;
            case R.id.imageview_titlebar_right:
                onTitleBarRightClick();
                break;
            case R.id.textview_titlebar_title:
                onTitleBarTitleClick();
                break;
        }
    }

    protected  void onTitleBarLeftClick(){
        onBackPressed();
    }
    protected  void onTitleBarTitleClick(){}
    protected  void onTitleBarRightClick(){}


    protected void setTextViewTitle(int stringId){
        mTextViewTitle.setText(stringId);
    }

    protected void setTextViewTitle(String title){
        mTextViewTitle.setText(title);
    }
}
