package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.VideoChannelModel;

import java.util.List;

/**
 * 频道适配器，暂时只有更多频道在用
 * Created by wrf on 2016/12/12.
 */

public class VideoMoreChannelAdapter extends RecyclerView.Adapter<VideoMoreChannelAdapter.VideoChannelHolder>{


    private Context mContext;

    private List<VideoChannelModel> mVideoChannelModelList;


    public VideoMoreChannelAdapter(Context context, List<VideoChannelModel> videoChannelModelList) {
        mContext = context;
        mVideoChannelModelList = videoChannelModelList;
    }

    @Override
    public VideoChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_channel, parent, false);

        return new VideoMoreChannelAdapter.VideoChannelHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(VideoChannelHolder holder, int position) {
        VideoChannelModel videoChannelModel = mVideoChannelModelList.get(position);


        holder.mTextViewChannel.setText(videoChannelModel.getName());
        holder.mTextViewChannel.setCompoundDrawablesWithIntrinsicBounds(0,videoChannelModel.getDrawableID(),0,0);

    }

    @Override
    public int getItemCount() {
        return mVideoChannelModelList.size();
    }

    static class VideoChannelHolder extends RecyclerView.ViewHolder{

        TextView mTextViewChannel;

        public VideoChannelHolder(View itemView) {
            super(itemView);
            mTextViewChannel = (TextView) itemView;
        }
    }
}
