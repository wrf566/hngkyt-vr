package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.ViewHolder> {

    private List<ViedoGroupModel> mViedoGroupModelList;

    private List<ViedoGroupModel.VideoItem> mVideoItemList;

    private Context mContext;


    public VideoGroupAdapter(Context context, List<ViedoGroupModel> viedoGroupModelList) {
        mViedoGroupModelList = viedoGroupModelList;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View itemView = layoutInflater.inflate(R.layout.item_videogroup, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViedoGroupModel viedoGroupModel = mViedoGroupModelList.get(position);

        TextView textViewCategoryName = holder.mTextViewCategoryName;
        TextView textViewMore = holder.mTextViewMore;
        textViewCategoryName.setText(viedoGroupModel.getName());


        RecyclerView recyclerView = holder.mRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        addVideoItems();
        VideoItemAdapter videoItemAdapter = new VideoItemAdapter(mContext,R.layout.item_video,mVideoItemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(videoItemAdapter);


    }

    private void addVideoItems(){
        mVideoItemList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            ViedoGroupModel.VideoItem videoItem = new ViedoGroupModel.VideoItem();
            videoItem.setName("item "+i);
            mVideoItemList.add(videoItem);
        }
    }

    @Override
    public int getItemCount() {
        return mViedoGroupModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewCategoryName;
        TextView mTextViewMore;
        RecyclerView mRecyclerView;


        ViewHolder(View itemView) {
            super(itemView);
            mTextViewCategoryName = (TextView) itemView.findViewById(R.id.textview_item_videogroup_category_name);
            mTextViewMore = (TextView) itemView.findViewById(R.id.textview_item_videogroup_more);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        }
    }

}
