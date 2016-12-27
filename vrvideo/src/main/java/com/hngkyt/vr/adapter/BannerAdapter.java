package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.VRVideoActivity;
import com.hngkyt.vr.net.been.BannerList;
import com.hngkyt.vr.net.been.Video;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by wrf on 2016/12/23.
 */

public class BannerAdapter extends PagerAdapter {

    private Context mContext;
    private List<BannerList.Banner> mBannerList;


    public BannerAdapter(Context context, List<BannerList.Banner> bannerList) {
        mContext = context;
        mBannerList = bannerList;
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageView(mContext);

        final BannerList.Banner banner = mBannerList.get(position);


        Glide.with(mContext)
                .load(banner.getImgUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (banner.getType()) {
                    case BannerList.TYPE_VIDEO:
                        Logger.e("点击");
                        Video video = new Video();
                        video.setId(banner.getVedioId());
                        Intent intent = new Intent(mContext, VRVideoActivity.class);
                        intent.putExtra(Video.class.getCanonicalName(), video);
                        mContext.startActivity(intent);
                        break;
                    case BannerList.TYPE_URL:

                        break;

                }

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
