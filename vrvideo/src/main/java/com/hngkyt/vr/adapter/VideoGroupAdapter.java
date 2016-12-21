package com.hngkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
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


        CategoryVedios.VedioListBean vedioListBean = mCategoryVediosVedioList.get(position);


        holder.mTextViewVideoGroupMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(mContext, MoreGroupActivity.class);
                //                intent.putExtra(MoreGroupActivity.class.getCanonicalName(), videoGroupModel.getName());
                //                mContext.startActivity(intent);
            }
        });

        holder.includeView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(mContext, VRVideoActivity.class);
                //                intent.putExtra(VideoItemModel.class.getCanonicalName(), videoGroupModel.getVideoItemModels()[0]);
                //                mContext.startActivity(intent);
            }
        });

        holder.includeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(mContext, VRVideoActivity.class);
                //                intent.putExtra(VideoItemModel.class.getCanonicalName(), videoGroupModel.getVideoItemModels()[1]);
                //                mContext.startActivity(intent);
            }
        });

        holder.mTextViewVideoGroupName.setText(vedioListBean.getName());


        List<CategoryVedios.VedioListBean.ListBean> vedioListBeanList = vedioListBean.getList();

        for (int i = 0; i < vedioListBeanList.size(); i++) {

            CategoryVedios.VedioListBean.ListBean listBean = vedioListBeanList.get(i);
            holder.setItemData(mContext, listBean, holder.mTextViewVideoItemName1, holder.mImageViewVideoItemCover1);
            holder.setItemData(mContext, listBean, holder.mTextViewVideoItemName2, holder.mImageViewVideoItemCover2);
            holder.mTextViewVideoGroupName.setText(listBean.getVedioCategoryName());

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

        void setItemData(Context context
                , CategoryVedios.VedioListBean.ListBean listBean
                , TextView videoNameTextView
                , ImageView imageView) {

            videoNameTextView.setText(listBean.getVedioName());

            Glide.with(context)
                    .load(listBean.getVedioImgUrl())
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }
}
