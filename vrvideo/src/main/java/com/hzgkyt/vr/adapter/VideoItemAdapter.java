package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {


    private Context mContext;
    private List<ViedoGroupModel.VideoItem> mGroupItemModelList;
    private int mLayoutRes;

    public VideoItemAdapter(Context context, int layoutRes,List<ViedoGroupModel.VideoItem> groupItemModelList ) {
        mContext = context;
        mLayoutRes = layoutRes;
        mGroupItemModelList = groupItemModelList;
    }

    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(mLayoutRes, parent, false);

        VideoItemViewHolder viewHolder = new VideoItemViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {
        ViedoGroupModel.VideoItem groupItemModel= mGroupItemModelList.get(position);

        holder.mTextViewName.setText(groupItemModel.getName());
//        holder.mRelativeLayoutItem.setBackgroundResource(R.drawable.ylsy);
        holder.mImageViewCover.setImageResource(R.drawable.ylsy);
    }

    @Override
    public int getItemCount() {
        return mGroupItemModelList.size();
    }


}
