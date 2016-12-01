package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.List;

/**
 * 最新视频适配器
 * Created by wrf on 2016/12/1.
 */

public class VideoNewestAdapter extends RecyclerView.Adapter<VideoNewestAdapter.ViewHolder> {


    private Context mContext;

    private List<ViedoGroupModel.VideoItem> mVideoItemlist;


    public VideoNewestAdapter(Context context, List<ViedoGroupModel.VideoItem> videoItemlist) {
        mContext = context;
        mVideoItemlist = videoItemlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View itemView = inflater.inflate(R.layout.item_video_newest, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViedoGroupModel.VideoItem videoItem = mVideoItemlist.get(position);


        holder.mTextViewName.setText(videoItem.getName());
        holder.mRelativeLayoutItem.setBackgroundResource(R.drawable.ylsy);

    }

    @Override
    public int getItemCount() {
        return mVideoItemlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        RelativeLayout mRelativeLayoutItem;


        ViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_newest_name);
            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video_newest);
        }
    }
}
