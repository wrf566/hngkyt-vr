package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.LocalVRVideoActivity;
import com.hngkyt.vr.model.LocalBanner;
import com.hngkyt.vr.model.LocalVideo;

import java.util.List;

/**
 * Created by wrf on 2016/12/29.
 */

public class LocalBannerAdapter extends PagerAdapter {

    private Context mContext;
    private List<LocalBanner> mLocalBannerList;


    public LocalBannerAdapter(Context context, List<LocalBanner> localBannerList) {
        mContext = context;
        mLocalBannerList = localBannerList;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageView(mContext);

        final LocalBanner localBanner = mLocalBannerList.get(position);


        Glide.with(mContext)
                .load(localBanner.getBannerdrawableId())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             Intent intent = new Intent(mContext, LocalVRVideoActivity.class);
                                             intent.putExtra(LocalVideo.class.getCanonicalName()
                                                     , localBanner.getLocalVideo());
                                             mContext.startActivity(intent);


                                         }

                                     }
        );

        container.addView(imageView);

        return imageView;
    }


    @Override
    public int getCount() {
        return mLocalBannerList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
