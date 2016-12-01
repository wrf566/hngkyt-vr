package com.hzgkyt.vr.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.fragment.VideoGroupFragment;
import com.hzgkyt.vr.fragment.ViedeoNewestFragment;
import com.orhanobut.logger.Logger;

public class MainActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    int[] pics = {R.drawable.test, R.drawable.test, R.drawable.test, R.drawable.test, R.drawable.test};


    private ImageView mImageView;
    private ViewPager mViewPager;
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
        mTextViewGroupName = (TextView) findViewById(R.id.textview_main_group);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_main_viedogroup);
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.radiobutton_main_viedogroup_newest);



        mImageView = (ImageView) findViewById(R.id.imageview_main_personal_center);

        mImageView.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_banner);

        mViewPager.setAdapter(new ImageViewAdapter());


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main_indicator);
        tabLayout.setupWithViewPager(mViewPager, true);
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
                Logger.e("radiobutton_main_viedogroup_newest");
                mTextViewGroupName.setText(R.string.newest_video);
                replaceFragment(new ViedeoNewestFragment(),null);
                break;

            case R.id.radiobutton_main_viedogroup_religionary:
                mTextViewGroupName.setText(R.string.religionary);
                replaceFragment(new VideoGroupFragment(),null);

                break;
            case R.id.radiobutton_main_viedogroup_landscape:
                mTextViewGroupName.setText(R.string.landscaoe);
                replaceFragment(new VideoGroupFragment(),null);

                break;
            case R.id.radiobutton_main_viedogroup_history:
                mTextViewGroupName.setText(R.string.history);
                replaceFragment(new VideoGroupFragment(),null);

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
