package com.hzgkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.activity.MoreActivity;
import com.hzgkyt.vr.activity.VRVideoActivity;
import com.hzgkyt.vr.model.VideoGroupModel;
import com.hzgkyt.vr.model.VideoItemModel;

import java.util.List;

/**
 * 分组适配器，一个频道中有多个分组
 * Created by wrf on 2016/12/5.
 */

public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.VideoGroupHolder> {


    private Context mContext;
    private List<VideoGroupModel> mVideoGroupModelList;


    public VideoGroupAdapter(Context context, List<VideoGroupModel> videoGroupModelList) {
        mContext = context;
        mVideoGroupModelList = videoGroupModelList;
    }

    @Override
    public VideoGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_video_group, parent, false);

        return new VideoGroupHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(VideoGroupHolder holder, int position) {
        final VideoGroupModel videoGroupModel = mVideoGroupModelList.get(position);

        holder.mTextViewVideoGroupMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreActivity.class);
                intent.putExtra(MoreActivity.class.getCanonicalName(), videoGroupModel.getName());
                mContext.startActivity(intent);
            }
        });

        holder.includeView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(VideoItemModel.class.getCanonicalName(), videoGroupModel.getVideoItemModels()[0]);
                mContext.startActivity(intent);
            }
        });

        holder.includeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(VideoItemModel.class.getCanonicalName(), videoGroupModel.getVideoItemModels()[1]);
                mContext.startActivity(intent);
            }
        });

        holder.mTextViewVideoGroupName.setText(videoGroupModel.getName());


        VideoItemModel[] videoItemModels = videoGroupModel.getVideoItemModels();


        holder.mTextViewVideoItemName1.setText(videoItemModels[0].getName());
        holder.mTextViewVideoItemName2.setText(videoItemModels[1].getName());

        holder.mImageViewVideoItemCover1.setImageResource(R.drawable.t4);
        holder.mImageViewVideoItemCover2.setImageResource(R.drawable.t1);


    }

    @Override
    public int getItemCount() {
        return mVideoGroupModelList.size();
    }

    static class VideoGroupHolder extends RecyclerView.ViewHolder {

        TextView mTextViewVideoItemName1;
        TextView mTextViewVideoItemName2;

        ImageView mImageViewVideoItemCover1;
        ImageView mImageViewVideoItemCover2;


        TextView mTextViewVideoGroupName;
        TextView mTextViewVideoGroupMore;

        View includeView1;
        View includeView2;

        public VideoGroupHolder(View itemView) {
            super(itemView);

            includeView1 = itemView.findViewById(R.id.include_video_item1);
            includeView2 = itemView.findViewById(R.id.include_video_item2);


            mTextViewVideoItemName1 = (TextView) includeView1.findViewById(R.id.textview_item_video_name);
            mTextViewVideoItemName2 = (TextView) includeView2.findViewById(R.id.textview_item_video_name);

            mImageViewVideoItemCover1 = (ImageView) includeView1.findViewById(R.id.imageview_item_video_cover);
            mImageViewVideoItemCover2 = (ImageView) includeView2.findViewById(R.id.imageview_item_video_cover);


            mTextViewVideoGroupName = (TextView) itemView.findViewById(R.id.textview_item_video_group_name);
            mTextViewVideoGroupMore = (TextView) itemView.findViewById(R.id.textview_item_video_group_more);


        }
    }
}
