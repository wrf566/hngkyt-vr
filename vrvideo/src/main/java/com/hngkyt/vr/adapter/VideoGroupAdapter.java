package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.MoreGroupActivity;
import com.hngkyt.vr.activity.VRVideoActivity;
import com.hngkyt.vr.net.been.CategoryVedios;

import java.util.List;

/**
 * 分组适配器，一个频道中有多个分组
 * Created by wrf on 2016/12/5.
 */

public class VideoGroupAdapter extends RecyclerView.Adapter<VideoGroupAdapter.VideoGroupHolder> {


    private Context mContext;
    private List<CategoryVedios.VedioListBean> mCategoryVediosVedioList;

    public VideoGroupAdapter(Context context, List<CategoryVedios.VedioListBean> categoryVediosVedioList) {
        mContext = context;
        mCategoryVediosVedioList = categoryVediosVedioList;
    }

    @Override
    public VideoGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_video_group, parent, false);

        return new VideoGroupHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(VideoGroupHolder holder, int position) {


        final CategoryVedios.VedioListBean vedioListBean = mCategoryVediosVedioList.get(position);

        final List<CategoryVedios.VedioListBean.ListBean> vedioListBeanList = vedioListBean.getList();

        holder.mTextViewVideoGroupMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MoreGroupActivity.class);
                intent.putExtra(CategoryVedios.VedioListBean.class.getCanonicalName(), vedioListBean);
                mContext.startActivity(intent);
            }
        });

        holder.mTextViewVideoGroupName.setText(vedioListBean.getName());


        for (int i = 0; i < vedioListBeanList.size(); i++) {
            CategoryVedios.VedioListBean.ListBean listBean = vedioListBeanList.get(i);

            holder.mTextViewVideoGroupName.setText(listBean.getVedioCategoryName());
            if (vedioListBeanList.size() == 1) {
                holder.includeView2.setVisibility(View.INVISIBLE);
            }

            if (i == 0) {
                holder.setItemData(mContext, listBean, holder.mTextViewVideoItemName1, holder.mImageViewVideoItemCover1, holder.includeView1);
            } else {
                holder.setItemData(mContext, listBean, holder.mTextViewVideoItemName2, holder.mImageViewVideoItemCover2, holder.includeView2);
            }

        }


    }


    @Override
    public int getItemCount() {
        return mCategoryVediosVedioList.size();
    }

    public List<CategoryVedios.VedioListBean> getCategoryVediosVedioList() {
        return mCategoryVediosVedioList;
    }

    public void setCategoryVediosVedioList(List<CategoryVedios.VedioListBean> categoryVediosVedioList) {
        mCategoryVediosVedioList = categoryVediosVedioList;
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

        VideoGroupHolder(View itemView) {
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

        void setItemData(final Context context
                , final CategoryVedios.VedioListBean.ListBean listBean
                , TextView videoNameTextView
                , ImageView imageView, View includeView) {

            videoNameTextView.setText(listBean.getVedioName());

            Glide.with(context)
                    .load(listBean.getVedioImgUrl())
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);

            includeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VRVideoActivity.class);
                    intent.putExtra(CategoryVedios.VedioListBean.ListBean.class.getCanonicalName(), listBean);
                    context.startActivity(intent);
                }
            });
        }
    }
}
