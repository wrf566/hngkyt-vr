package com.hzgkyt.vr.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.fragment.VideoGroupFragment;
import com.hzgkyt.vr.fragment.ViedeoNewestFragment;
import com.hzgkyt.vr.view.InfiniteViewPager;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    //c a b c a
    int[] pics = {R.drawable.c, R.drawable.a, R.drawable.b, R.drawable.c,R.drawable.a};


    private ImageView mImageView;
    private InfiniteViewPager mInfiniteViewPager;
    private TextView mTextViewGroupName;
    private RadioGroup mRadioGroup;

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
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mTextViewGroupName = (TextView) findViewById(R.id.textview_main_group);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_main_viedogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.radiobutton_main_viedogroup_newest);


        mImageView = (ImageView) findViewById(R.id.imageview_main_personal_center);

        mImageView.setOnClickListener(this);

        mInfiniteViewPager = (InfiniteViewPager) findViewById(R.id.viewpager_banner);

        final ImageViewAdapter imageViewAdapter = new ImageViewAdapter();

        mInfiniteViewPager.setAdapter(imageViewAdapter);

        mInfiniteViewPager.setCurrentItem(1);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main_indicator);
        tabLayout.setupWithViewPager(mInfiniteViewPager);


        for (int i = 0;i<imageViewAdapter.getCount();i++){
            ImageView imageView = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40,40);
            imageView.setLayoutParams(layoutParams);
            if(i==0||i==imageViewAdapter.getCount()-1){
                imageView.setVisibility(View.GONE);
            }
            imageView.setImageResource(R.drawable.indicator);
            tabLayout.getTabAt(i).setCustomView(imageView);

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_main_personal_center:
                startActivityOriginal(this, PersonalCenterActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radiobutton_main_viedogroup_newest:
                mTextViewGroupName.setText(R.string.newest_video);
                replaceFragment(new ViedeoNewestFragment(), null);
                break;

            case R.id.radiobutton_main_viedogroup_religionary:
                mTextViewGroupName.setText(R.string.religionary);
                replaceFragment(new VideoGroupFragment(), null);

                break;
            case R.id.radiobutton_main_viedogroup_landscape:
                mTextViewGroupName.setText(R.string.landscaoe);
                replaceFragment(new VideoGroupFragment(), null);

                break;
            case R.id.radiobutton_main_viedogroup_history:
                mTextViewGroupName.setText(R.string.history);
                replaceFragment(new VideoGroupFragment(), null);

                break;

        }
    }

    class ImageViewAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(pics[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }


    }

}
