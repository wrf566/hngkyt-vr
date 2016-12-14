package com.hngkyt.vr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 准通用的适配器，未完成
 * Created by wrf on 2016/12/12.
 */

public abstract class BaseRecyclerViewAdapter<Model,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter{

    private Context mContext;

    protected List<Model> mModelList;


    @Override
    public VideoMoreChannelAdapter.VideoChannelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(initLayout(), parent, false);

        return null;
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    protected abstract int initLayout();

}
