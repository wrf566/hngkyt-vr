package com.hzgkyt.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.adapter.VideoItemAdapter;
import com.hzgkyt.vr.decoration.VideoItemNewestDecoration;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class ViedeoNewestFragment extends RecyclerViewFragment {

    private static final int SPAN_COUNT = 2;//列数
    private static final int ITEM_SPACE = 20;//item之间的距离


    @Override
    protected int intLayoutResId() {
        return R.layout.fragment_newest_video;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoItemAdapter(getActivity(),R.layout.item_video,getVideoItemList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemNewestDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(),SPAN_COUNT);
    }

    private List<ViedoGroupModel.VideoItem> getVideoItemList(){
       List<ViedoGroupModel.VideoItem> mVideoItemList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            ViedoGroupModel.VideoItem videoItem = new ViedoGroupModel.VideoItem();
            videoItem.setName("item "+i);
            mVideoItemList.add(videoItem);
        }
        return mVideoItemList;
    }
}
