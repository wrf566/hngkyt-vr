package com.hzgkyt.vr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

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
