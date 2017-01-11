package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.VRVideoActivity;
import com.hngkyt.vr.model.Video;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;

import java.util.List;

import static com.hngkyt.vr.view.FooterGridLayoutManager.ITEM_TYPE_FOOTER;
import static com.hngkyt.vr.view.FooterGridLayoutManager.ITEM_TYPE_NORMAL;

/**
 * 单个视频适配器
 * Created by wrf on 2016/12/1.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Video> mVideoList;
    private boolean isVisibility = false;

    public VideoItemAdapter(Context context, List<Video> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    public void setFooterVisibility(boolean visibility) {
        isVisibility = visibility;
    }

    public void setVideoList(List<Video> videoList) {
        mVideoList = videoList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_video, parent, false);

        switch (viewType) {
            case ITEM_TYPE_FOOTER:
                itemView = layoutInflater.inflate(R.layout.item_video_footer, parent, false);
                return new VideoItemFooterViewHolder(itemView);
            case ITEM_TYPE_NORMAL:
                return new VideoItemViewHolder(itemView);
            default:
                return new VideoItemViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_NORMAL:
                Video video = mVideoList.get(position);
                initNormalItem((VideoItemViewHolder) holder, video);
                break;
            case ITEM_TYPE_FOOTER:
                initFooterItem((VideoItemFooterViewHolder) holder);
                break;
        }


    }

    private void initNormalItem(VideoItemViewHolder holder, final Video video) {
        holder.mTextViewName.setText(video.getVedioName());
        if (video.getIsFree()) {
            holder.mTextViewPay.setVisibility(View.GONE);
        } else {
            holder.mTextViewPay.setVisibility(View.VISIBLE);
        }
        holder.mRelativeLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(Video.class.getCanonicalName(), video);
                mContext.startActivity(intent);

            }
        });
        Glide.with(mContext)
                .load(video.getVedioImgUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImageViewCover);
    }

    private void initFooterItem(VideoItemFooterViewHolder holder) {
        holder.mButtonFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast(mContext, "aaaaa");
            }
        });
    }


    @Override
    public int getItemCount() {
        return isVisibility ? mVideoList.size() + 1 : mVideoList.size();
    }


    public int getNormalItemCount() {
        return mVideoList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (isVisibility) {
            //这里要加1是因为position表示的是列表位置，getItemCount表示的是个数，postion永远比getItemCount小1
            return position + 1 == getItemCount() ? ITEM_TYPE_FOOTER : ITEM_TYPE_NORMAL;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    private static class VideoItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        TextView mTextViewPay;
        ImageView mImageViewCover;
        RelativeLayout mRelativeLayoutItem;

        VideoItemViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
            mTextViewPay = (TextView) itemView.findViewById(R.id.textview_item_video_pay);
            mImageViewCover = (ImageView) itemView.findViewById(R.id.imageview_item_video_cover);
            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
        }
    }

    private static class VideoItemFooterViewHolder extends RecyclerView.ViewHolder {

        Button mButtonFooter;

        VideoItemFooterViewHolder(View itemView) {
            super(itemView);
            mButtonFooter = (Button) itemView.findViewById(R.id.button_item_video_more_videos);
        }
    }
}
