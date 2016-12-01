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
 * Created by wrf on 2016/12/1.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {


    private Context mContext;
    private List<ViedoGroupModel.VideoItem> mGroupItemModelList;

    public VideoItemAdapter(Context context, List<ViedoGroupModel.VideoItem> groupItemModelList) {
        mContext = context;
        mGroupItemModelList = groupItemModelList;
    }

    @Override
    public VideoItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_video, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViedoGroupModel.VideoItem groupItemModel= mGroupItemModelList.get(position);

        holder.mTextViewName.setText(groupItemModel.getName());
        holder.mRelativeLayoutItem.setBackgroundResource(R.drawable.ylsy);

    }

    @Override
    public int getItemCount() {
        return mGroupItemModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        RelativeLayout mRelativeLayoutItem;


        public ViewHolder(View itemView) {
            super(itemView);

            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
        }
    }

}
