package com.hngkyt.vr.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.GroupFragmentAdapter;
import com.hngkyt.vr.fragment.MoreChannelFragment;
import com.hngkyt.vr.fragment.VideoChannelFragment;
import com.hngkyt.vr.fragment.VideoNewestChannelFragment;
import com.hngkyt.vr.view.InfiniteViewPager;

import static com.hzgktyt.vr.baselibrary.utils.DrawableTintUtils.getTintDrawable;
import static com.hzgktyt.vr.baselibrary.utils.DrawableTintUtils.selectStateListDrawable;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    //c a b c a
    int[] pics = {R.drawable.c, R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.a};


    private ImageView mImageViewPersonalCenter;
    private InfiniteViewPager mInfiniteViewPager;


    private ViewPager mViewPagerGroup;
    private TabLayout mTabLayoutGroup;

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


        initVideoChannel();
        initBanner();


        mImageViewPersonalCenter = (ImageView) findViewById(R.id.imageview_main_personal_center);

        mImageViewPersonalCenter.setOnClickListener(this);


    }

    private void initVideoChannel() {
        mViewPagerGroup = (ViewPager) findViewById(R.id.viewpager_main_group);
        mTabLayoutGroup = (TabLayout) findViewById(R.id.tablayout_main_group);


        GroupFragmentAdapter groupFragmentAdapter = new GroupFragmentAdapter(MainActivity.this, mFragmentManager);
        groupFragmentAdapter.addFragment(new VideoNewestChannelFragment());
        groupFragmentAdapter.addFragment(new VideoChannelFragment());
        groupFragmentAdapter.addFragment(new VideoChannelFragment());
        groupFragmentAdapter.addFragment(new MoreChannelFragment());

        mViewPagerGroup.setAdapter(groupFragmentAdapter);


        mTabLayoutGroup.setupWithViewPager(mViewPagerGroup);






        //由于是纯色的图片，所以我选择染色，而不是去问UI要切图
        mTabLayoutGroup.getTabAt(0).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.newest)
                , getTintDrawable(getResources().getDrawable(R.drawable.newest)
                        , getResources().getColor(R.color.red)))
                , R.string.newest_video));//默认选中的一开始就要染色成红的
        mTabLayoutGroup.getTabAt(1).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.religious)
                , getTintDrawable(getResources().getDrawable(R.drawable.religious)
                        , getResources().getColor(R.color.red))), R.string.religionary));
        mTabLayoutGroup.getTabAt(2).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.landscape)
                , getTintDrawable(getResources().getDrawable(R.drawable.landscape)
                        , getResources().getColor(R.color.red))), R.string.landscaoe));
        mTabLayoutGroup.getTabAt(3).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.more)
                , getTintDrawable(getResources().getDrawable(R.drawable.more)
                        , getResources().getColor(R.color.red))), R.string.more));

    }



    private void initBanner() {
        mInfiniteViewPager = (InfiniteViewPager) findViewById(R.id.viewpager_banner);

        final ImageViewAdapter imageViewAdapter = new ImageViewAdapter();

        mInfiniteViewPager.setAdapter(imageViewAdapter);

        mInfiniteViewPager.setCurrentItem(1);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main_indicator);

        tabLayout.setupWithViewPager(mInfiniteViewPager);


        for (int i = 0; i < imageViewAdapter.getCount(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            imageView.setLayoutParams(layoutParams);
            if (i == 0 || i == imageViewAdapter.getCount() - 1) {
                imageView.setVisibility(View.GONE);
            }
            imageView.setImageResource(R.drawable.indicator);
            tabLayout.getTabAt(i).setCustomView(imageView);

        }
    }


    private TextView getTabLayoutCustomView(Drawable drawable, int stringRes) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_channel, null);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        textView.setText(stringRes);
        return textView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_main_personal_center:
                startActivityOriginal(this, PersonalCenterActivity.class);
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
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(pics[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtils.showShortToast(MainActivity.this,"position = "+position);
                }
            });

            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }


    }




}
