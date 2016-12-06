package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.activity.VRVideoActivity;
import com.hzgkyt.vr.model.VideoItemModel;

import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.VideoItemViewHolder> {


    private Context mContext;
    private List<VideoItemModel> mVideoItemModelList;

    public VideoItemAdapter(Context context, List<VideoItemModel> videoItemModelList) {
        mContext = context;
        mVideoItemModelList = videoItemModelList;
    }

    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_video, parent, false);

        VideoItemViewHolder viewHolder = new VideoItemViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {
        final VideoItemModel videoItemModel = mVideoItemModelList.get(position);

        holder.mTextViewName.setText(videoItemModel.getName());
        holder.mRelativeLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(VideoItemModel.class.getCanonicalName(), videoItemModel);
                mContext.startActivity(intent);

            }
        });

        if (position  == 0) {
            holder.mImageViewCover.setImageResource(R.drawable.t1);
        } else if(position  == 1){
            holder.mImageViewCover.setImageResource(R.drawable.t2);
        }else if(position  == 2){
            holder.mImageViewCover.setImageResource(R.drawable.t3);
        }else if(position  == 3){
            holder.mImageViewCover.setImageResource(R.drawable.t4);

        }
    }

    @Override
    public int getItemCount() {
        return mVideoItemModelList.size();
    }


    static class VideoItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        ImageView mImageViewCover;
        RelativeLayout mRelativeLayoutItem;

        public VideoItemViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
            mImageViewCover = (ImageView) itemView.findViewById(R.id.imageview_item_video_cover);
            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
        }
    }


}
