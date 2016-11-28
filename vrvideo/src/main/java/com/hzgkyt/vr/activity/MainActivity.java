package com.hzgkyt.vr.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzgkyt.vr.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private View mDecorView;

    private ImageView mImageView;

    private ViewPager mViewPager;

    private TextView mTextView;

    int[] pics = {R.drawable.test,R.drawable.test,R.drawable.test,R.drawable.test,R.drawable.test};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        SystemClock.sleep(2000);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mTextView = (TextView) findViewById(R.id.textview_main_group);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityOriginal(MainActivity.this,VRVideoActivity.class);
            }
        });

        mImageView = (ImageView) findViewById(R.id.imageview_main_personal_center);

        mImageView.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_banner);

        mViewPager.setAdapter(new ImageViewAdapter());


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main_indicator);
        tabLayout.setupWithViewPager(mViewPager, true);
    }


    private void showSystemUI() {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void hideSystemUI() {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageview_main_personal_center:
                startActivityOriginal(this,PersonalCenterActivity.class);
                break;
        }
    }

    class ImageViewAdapter extends PagerAdapter{





        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==  object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(pics[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

}
