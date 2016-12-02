package com.hzgkyt.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.adapter.VideoItemAdapter;
import com.hzgkyt.vr.decoration.VideoItemDecoration;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多页面
 *
 * Created by wrf on 2016/12/2.
 */

public class MoreFragment extends RecyclerViewFragment {

    private static final int ITEM_SPACE = 20;//item之间的距离


    @Override
    protected void initView(View view) {
        super.initView(view);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mRecyclerView.getLayoutParams();
        layoutParams.setMargins(30, 30, 30, 30);
        mRecyclerView.setLayoutParams(layoutParams);
    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoItemAdapter(getActivity(), R.layout.item_video, getVideoItemList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }

    @Override
    protected int intLayoutResId() {
        return R.layout.include_recyclerview;
    }

    private List<ViedoGroupModel.VideoItem> getVideoItemList() {
        List<ViedoGroupModel.VideoItem> mVideoItemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ViedoGroupModel.VideoItem videoItem = new ViedoGroupModel.VideoItem();
            videoItem.setName("item " + i);
            mVideoItemList.add(videoItem);
        }
        return mVideoItemList;
    }
}
