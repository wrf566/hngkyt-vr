package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.VRVideoActivity;
import com.hngkyt.vr.net.been.VideoBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by wrf on 2016/12/22.
 */

public class VideoRecommendAdapter extends RecyclerView.Adapter<VideoRecommendAdapter.RecommendViewHolder> {
    private Context mContext;
    private List<VideoBean> mVideoBeanList;

    public VideoRecommendAdapter(Context context, List<VideoBean> videoBeanList) {
        mContext = context;
        mVideoBeanList = videoBeanList;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_video_recommend, parent, false);


        return new RecommendViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {
        final VideoBean videoBean = mVideoBeanList.get(position);


        Glide.with(mContext)
                .load(videoBean.getVedioImgUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImageViewCover);
        holder.mTextViewName.setText(videoBean.getVedioName());
        holder.mTextViewPlayTimes.setText(mContext.getResources().getString(R.string.play_counts, videoBean.getPlayAmount()));
        holder.mTextViewReleaseTime.setText(mContext.getResources().getString(R.string.release_time
                ,  new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(videoBean.getAddTime()))));
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(VideoBean.class.getCanonicalName(), videoBean);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mVideoBeanList.size();
    }

    public void setVideoBeanList(List<VideoBean> videoBeanList) {
        mVideoBeanList = videoBeanList;
        notifyDataSetChanged();
    }

    static class RecommendViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageViewCover;
        TextView mTextViewName;
        TextView mTextViewReleaseTime;
        TextView mTextViewPlayTimes;
        RelativeLayout mRelativeLayout;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video_recommend);
            mImageViewCover = (ImageView) itemView.findViewById(R.id.imageview_item_video_recommend_cover);
            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_recommend_name);
            mTextViewReleaseTime = (TextView) itemView.findViewById(R.id.textview_item_video_recommend_release_time);
            mTextViewPlayTimes = (TextView) itemView.findViewById(R.id.textview_item_video_recommend_play_counts);
        }
    }
}
