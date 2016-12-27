package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.MoreGroupActivity;
import com.hngkyt.vr.model.VideoChannelList;
import com.hngkyt.vr.model.VideoGroupList;

import java.util.List;

/**
 * 频道适配器，暂时只有更多频道在用
 * Created by wrf on 2016/12/12.
 */

public class VideoMoreChannelAdapter extends RecyclerView.Adapter<VideoMoreChannelAdapter.VideoChannelHolder> {


    private Context mContext;

    private List<VideoChannelList.VedioChannel> mVedioCategoryListBeanListBeen;


    public VideoMoreChannelAdapter(Context context, List<VideoChannelList.VedioChannel> vedioCategoryListBeanListBeen) {
        mContext = context;
        mVedioCategoryListBeanListBeen = vedioCategoryListBeanListBeen;
    }


    @Override
    public VideoChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_more_channel, parent, false);

        return new VideoMoreChannelAdapter.VideoChannelHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(VideoChannelHolder holder, int position) {
       final VideoChannelList.VedioChannel VedioChannel = mVedioCategoryListBeanListBeen.get(position);


        holder.mButtonChannel.setText(VedioChannel.getVedioCategoryName());
        holder.mButtonChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreGroupActivity.class);
                //这里为了界面接受统一，接口其实也是统一的所以转成了CategoryVedios.VideoList
                VideoGroupList.VideoList videoList = new VideoGroupList.VideoList();
                videoList.setName(VedioChannel.getVedioCategoryName());
                videoList.setId(VedioChannel.getId());

                intent.putExtra(VideoGroupList.VideoList.class.getCanonicalName(), videoList);
                mContext.startActivity(intent);
            }
        });

        Glide.with(mContext)
                .load(VedioChannel.getLogoImgUrl())
                .asBitmap()
                .into(new ButtonSimpleTarget(holder.mButtonChannel));

    }

    @Override
    public int getItemCount() {
        return mVedioCategoryListBeanListBeen.size();
    }

    static class VideoChannelHolder extends RecyclerView.ViewHolder {

        Button mButtonChannel;

        VideoChannelHolder(View itemView) {
            super(itemView);
            mButtonChannel = (Button) itemView;
        }
    }

    public List<VideoChannelList.VedioChannel> getVedioCategoryListBeanListBeen() {
        return mVedioCategoryListBeanListBeen;
    }

    public void setVedioCategoryListBeanListBeen(List<VideoChannelList.VedioChannel> vedioCategoryListBeanListBeen) {
        mVedioCategoryListBeanListBeen = vedioCategoryListBeanListBeen;
        notifyDataSetChanged();
    }


    /**
     * 这个类用于加载大分类中的图片
     */
   private class ButtonSimpleTarget extends SimpleTarget<Bitmap> {



        private Button mButton;

        ButtonSimpleTarget(Button button) {
            this.mButton = button;
        }

        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                    mButton.setCompoundDrawablesWithIntrinsicBounds(null, new BitmapDrawable(mContext.getResources(), bitmap), null, null);

        }


    }
}
