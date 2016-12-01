package com.hzgkyt.vr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzgkyt.vr.R;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    TextView mTextViewName;
    RelativeLayout mRelativeLayoutItem;

    public VideoItemViewHolder(View itemView) {
        super(itemView);

        mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
        mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
    }
}
