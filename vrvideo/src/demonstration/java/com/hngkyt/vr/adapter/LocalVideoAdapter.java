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
import com.hngkyt.vr.activity.LocalVRVideoActivity;
import com.hngkyt.vr.model.LocalVideo;

import java.util.List;

/**
 * Created by wrf on 2016/12/29.
 */

public class LocalVideoAdapter extends RecyclerView.Adapter<LocalVideoAdapter.VideoItemViewHolder> {

    private Context mContext;
    private List<LocalVideo> mLocalVideoList;

    public LocalVideoAdapter(Context context, List<LocalVideo> localVideoList) {
        mContext = context;
        mLocalVideoList = localVideoList;
    }

    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_video, parent, false);
        return new LocalVideoAdapter.VideoItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {
        final LocalVideo localVideo = mLocalVideoList.get(position);

        holder.mTextViewName.setText(localVideo.getName());
        holder.mRelativeLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, LocalVRVideoActivity.class);
                intent.putExtra(LocalVideo.class.getCanonicalName(), localVideo);
                mContext.startActivity(intent);

            }
        });

        Glide.with(mContext)
                .load(localVideo.getCoverFile())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImageViewCover);


    }

    @Override
    public int getItemCount() {
        return mLocalVideoList.size();
    }

    static class VideoItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        ImageView mImageViewCover;
        RelativeLayout mRelativeLayoutItem;

        VideoItemViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
            mImageViewCover = (ImageView) itemView.findViewById(R.id.imageview_item_video_cover);
            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
        }
    }

    public void setLocalVideoList(List<LocalVideo> localVideoList) {
        mLocalVideoList = localVideoList;
        notifyDataSetChanged();
    }
}
