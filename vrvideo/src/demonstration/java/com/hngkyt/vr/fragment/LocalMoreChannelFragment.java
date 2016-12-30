package com.hngkyt.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.LocalVideoMoreChannelAdapter;
import com.hngkyt.vr.decoration.VideoChannelDecoration;
import com.hngkyt.vr.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多频道
 * Created by wrf on 2016/12/12.
 */

public class LocalMoreChannelFragment extends RecyclerViewFragment {


    private static final int[] IDS = {R.drawable.fc, R.drawable.kb, R.drawable.mv, R.drawable.mx, R.drawable.cy, R.drawable.yy, R.drawable.zj};
    private static final String [] NAMES = {"房产", "恐怖惊悚", "美女萌宠", "明星综艺", "创意脑洞", "音乐现场", "宗教"};
    private LocalVideoMoreChannelAdapter mVideoMoreChannelAdapter;

    @Override
    protected void initView(View view) {
        super.initView(view);
        initData();
    }

    private void initData() {
        List<Category> categoryList = new ArrayList<>();

        for (int i = 0; i < IDS.length; i++) {
            Category category = new Category();
            category.setName(NAMES[i]);
            category.setDrawableId(IDS[i]);
            categoryList.add(category);
        }
        setAdapter(categoryList);

    }

    private void setAdapter(List<Category> categoryList) {
        if (mVideoMoreChannelAdapter == null) {
            mVideoMoreChannelAdapter = new LocalVideoMoreChannelAdapter(getActivity(), categoryList);
            mRecyclerView.setAdapter(mVideoMoreChannelAdapter);
        } else {
            mVideoMoreChannelAdapter.setCategoryList(categoryList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoChannelDecoration(100);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), 3);
    }


    @Override
    public void onRefresh() {
        initData();
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
