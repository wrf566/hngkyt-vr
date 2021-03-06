package com.hngkyt.vr.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.GroupFragmentAdapter;
import com.hngkyt.vr.adapter.LocalBannerAdapter;
import com.hngkyt.vr.fragment.CategoryFragment;
import com.hngkyt.vr.fragment.LocalMoreChannelFragment;
import com.hngkyt.vr.model.LocalBanner;
import com.hngkyt.vr.model.LocalVideo;
import com.hngkyt.vr.view.InfiniteViewPager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.hzgktyt.vr.baselibrary.utils.DrawableTintUtils.getTintDrawable;
import static com.hzgktyt.vr.baselibrary.utils.DrawableTintUtils.selectStateListDrawable;

public class LocalMainActivity extends BaseActivity implements View.OnClickListener {

    public static final File FILE_ROOT = new File(Environment.getExternalStorageDirectory() + File.separator + "VRVideo");
    private static final int TYPE_HOME = 1;
    private static final int[] BANNERS = {R.drawable.vrfc, R.drawable.sc, R.drawable.ts, R.drawable.lgt, R.drawable.vrfc,R.drawable.sc,};
    private static final File FILE_CATEGORY_EXTREME_SPORTS = new File(FILE_ROOT, "极限运动");
    private static final File FILE_CATEGORY_LANDSCAPE = new File(FILE_ROOT, "风景名胜");
    public static final File FILE_CATEGORY_HOUSE = new File(FILE_ROOT, "房产");

    //c a b c a
    private ImageView mImageViewPersonalCenter;
    private InfiniteViewPager mInfiniteViewPager;
    private ViewPager mViewPagerGroup;
    private TabLayout mTabLayoutGroup;

    private static final int STORAGE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        SystemClock.sleep(2000);
        super.onCreate(savedInstanceState);


    }
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
        } else {
            initBanner();

            initVideoChannel();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initBanner();
                initVideoChannel();
            } else {
                //用户勾选了不再询问
                //提示用户手动打开权限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "读写权限已被禁止", Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.please_enter_the_authorization);
                    builder.setMessage(R.string.must_get_camera_permission);

                    builder.setPositiveButton("设置授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setResult(RESULT_CANCELED);
                            onBackPressed();
                        }
                    });
                    builder.show();
                }
            }
        }

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission();
        } else {
            initBanner();

            initVideoChannel();
        }




        mImageViewPersonalCenter = (ImageView) findViewById(R.id.imageview_main_personal_center);

        mImageViewPersonalCenter.setOnClickListener(this);


    }

    private void initVideoChannel() {
        mViewPagerGroup = (ViewPager) findViewById(R.id.viewpager_main_group);
        mTabLayoutGroup = (TabLayout) findViewById(R.id.tablayout_main_group);
        //从网络获取频道列表
        //        getVedioCategory();


        //Fragment的管理以后再优化，比如Activity杀死了，状态保存之类的
        GroupFragmentAdapter groupFragmentAdapter = new GroupFragmentAdapter(LocalMainActivity.this, mFragmentManager);
        groupFragmentAdapter.addFragment(CategoryFragment.newInstance(FILE_ROOT));
        groupFragmentAdapter.addFragment(CategoryFragment.newInstance(FILE_CATEGORY_EXTREME_SPORTS));
        groupFragmentAdapter.addFragment(CategoryFragment.newInstance(FILE_CATEGORY_LANDSCAPE));
        groupFragmentAdapter.addFragment(new LocalMoreChannelFragment());

        mViewPagerGroup.setAdapter(groupFragmentAdapter);

        mTabLayoutGroup.setupWithViewPager(mViewPagerGroup);

        //由于是纯色的图片，所以我选择染色，而不是去问UI要切图
        mTabLayoutGroup.getTabAt(0).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.newest)
                , getTintDrawable(getResources().getDrawable(R.drawable.newest)
                        , getResources().getColor(R.color.red)))
                , R.string.newest_video));//默认选中的一开始就要染色成红的

        mTabLayoutGroup.getTabAt(1).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.sport)
                , getTintDrawable(getResources().getDrawable(R.drawable.sport)
                        , getResources().getColor(R.color.red)))
                , "极限运动"));//默认选中的一开始就要染色成红的

        mTabLayoutGroup.getTabAt(2).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.landscape)
                , getTintDrawable(getResources().getDrawable(R.drawable.landscape)
                        , getResources().getColor(R.color.red)))
                , "风景名胜"));//默认选中的一开始就要染色成红的

        //这里写成size+1是因为size不固定
        mTabLayoutGroup.getTabAt(3).setCustomView(getTabLayoutCustomView(selectStateListDrawable(
                getResources().getDrawable(R.drawable.more)
                , getTintDrawable(getResources().getDrawable(R.drawable.more)
                        , getResources().getColor(R.color.red))), R.string.more_channel));


    }



    private void initBanner() {
        List<LocalBanner> localBannerList = new ArrayList<>();

//

        LocalBanner localBanner1 = new LocalBanner();
        localBanner1.setLocalVideo(new LocalVideo(new File(FILE_CATEGORY_EXTREME_SPORTS, "本田F1赛场比赛.mp4")));
        LocalBanner localBanner2 = new LocalBanner();
        localBanner2.setLocalVideo(new LocalVideo(new File(FILE_CATEGORY_EXTREME_SPORTS, "花样跳伞.mp4")));
        LocalBanner localBanner3 = new LocalBanner();
        localBanner3.setLocalVideo(new LocalVideo(new File(FILE_CATEGORY_LANDSCAPE, "楼观之路.mp4")));
        LocalBanner localBanner4 = new LocalBanner();
        localBanner4.setLocalVideo(new LocalVideo(new File(FILE_CATEGORY_HOUSE, "VR带你看房.mp4")));

        localBannerList.add(localBanner1);
        localBannerList.add(localBanner2);
        localBannerList.add(localBanner3);
        localBannerList.add(localBanner4);
        localBannerList.add(0, localBannerList.get(localBannerList.size() - 1));
        localBannerList.add(localBannerList.get(1));//因为第一个已经是刚刚添加的了，所以要从第二个开始添加


        for (int i = 0; i < BANNERS.length; i++) {
            localBannerList.get(i).setBannerdrawableId(BANNERS[i]);
        }


        mInfiniteViewPager = (InfiniteViewPager) findViewById(R.id.viewpager_banner);

        final LocalBannerAdapter bannerAdapter = new LocalBannerAdapter(this, localBannerList);

        mInfiniteViewPager.setAdapter(bannerAdapter);

        mInfiniteViewPager.setCurrentItem(1);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_main_indicator);

        tabLayout.setupWithViewPager(mInfiniteViewPager);


        for (int i = 0; i < bannerAdapter.getCount(); i++) {
            ImageView imageView = new ImageView(LocalMainActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            imageView.setLayoutParams(layoutParams);
            if (i == 0 || i == bannerAdapter.getCount() - 1) {
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

    private TextView getTabLayoutCustomView(Drawable drawable, String name) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_channel, null);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        textView.setText(name);
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


}
