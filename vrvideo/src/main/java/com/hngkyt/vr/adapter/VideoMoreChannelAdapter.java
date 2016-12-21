package com.hngkyt.vr.adapter;

import android.content.Context;
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
import com.hngkyt.vr.net.been.VedioCategoryList;

import java.util.List;

/**
 * 频道适配器，暂时只有更多频道在用
 * Created by wrf on 2016/12/12.
 */

public class VideoMoreChannelAdapter extends RecyclerView.Adapter<VideoMoreChannelAdapter.VideoChannelHolder> {


    private Context mContext;

    private List<VedioCategoryList.VedioCategoryListBean> mVedioCategoryListBeen;


    public VideoMoreChannelAdapter(Context context, List<VedioCategoryList.VedioCategoryListBean> vedioCategoryListBeen) {
        mContext = context;
        mVedioCategoryListBeen = vedioCategoryListBeen;
    }


    @Override
    public VideoChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_more_channel, parent, false);

        return new VideoMoreChannelAdapter.VideoChannelHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(VideoChannelHolder holder, int position) {
        VedioCategoryList.VedioCategoryListBean vedioCategoryListBean = mVedioCategoryListBeen.get(position);


        holder.mButtonChannel.setText(vedioCategoryListBean.getVedioCategoryName());

        Glide.with(mContext)
                .load(vedioCategoryListBean.getLogoImgUrl())
                .asBitmap()
                .into(new ButtonSimpleTarget(holder.mButtonChannel));

    }

    @Override
    public int getItemCount() {
        return mVedioCategoryListBeen.size();
    }

    static class VideoChannelHolder extends RecyclerView.ViewHolder {

        Button mButtonChannel;

        VideoChannelHolder(View itemView) {
            super(itemView);
            mButtonChannel = (Button) itemView;
        }
    }

    public List<VedioCategoryList.VedioCategoryListBean> getVedioCategoryListBeen() {
        return mVedioCategoryListBeen;
    }

    public void setVedioCategoryListBeen(List<VedioCategoryList.VedioCategoryListBean> vedioCategoryListBeen) {
        mVedioCategoryListBeen = vedioCategoryListBeen;
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
