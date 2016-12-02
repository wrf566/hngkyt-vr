package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.activity.MoreActivity;
import com.hzgkyt.vr.decoration.VideoItemDecoration;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频分组适配器
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
        final ViedoGroupModel viedoGroupModel = mViedoGroupModelList.get(position);

        TextView textViewCategoryName = holder.mTextViewCategoryName;
        textViewCategoryName.setText(viedoGroupModel.getName());


        TextView textViewMore = holder.mTextViewMore;
        textViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MoreActivity.class);
                intent.putExtra(MoreActivity.class.getCanonicalName(), viedoGroupModel.getName());
                mContext.startActivity(intent);

            }
        });


        RecyclerView recyclerView = holder.mRecyclerView;

        VideoItemAdapter videoItemAdapter = new VideoItemAdapter(mContext, R.layout.item_video, getVideoItems());

        recyclerView.setAdapter(videoItemAdapter);


    }

    private List<ViedoGroupModel.VideoItem> getVideoItems() {
        List<ViedoGroupModel.VideoItem> mVideoItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ViedoGroupModel.VideoItem videoItem = new ViedoGroupModel.VideoItem();
            videoItem.setName("item " + i);
            mVideoItemList.add(videoItem);
        }
        return mVideoItemList;
    }

    @Override
    public int getItemCount() {
        return mViedoGroupModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewCategoryName;
        TextView mTextViewMore;
        RecyclerView mRecyclerView;


        ViewHolder(View itemView) {
            super(itemView);
            mTextViewCategoryName = (TextView) itemView.findViewById(R.id.textview_item_videogroup_category_name);
            mTextViewMore = (TextView) itemView.findViewById(R.id.textview_item_videogroup_more);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
            mRecyclerView.addItemDecoration(new VideoItemDecoration(8));
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(layoutManager);

        }
    }

}
