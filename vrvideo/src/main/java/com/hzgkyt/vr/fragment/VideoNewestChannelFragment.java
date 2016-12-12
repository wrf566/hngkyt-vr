package com.hzgkyt.vr.fragment;

import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hzgkyt.vr.adapter.VideoItemAdapter;
import com.hzgkyt.vr.decoration.VideoItemDecoration;
import com.hzgkyt.vr.model.VideoItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoNewestChannelFragment extends RecyclerViewFragment {

    private static final int SPAN_COUNT = 2;//列数
    private static final int ITEM_SPACE = 20;//item之间的距离



    @Override
    protected void initView(View view) {
        super.initView(view);
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mRecyclerView.getLayoutParams();
//        layoutParams.setMargins(10, 10, 10, 10);
//        mRecyclerView.setLayoutParams(layoutParams);
    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoItemAdapter(getActivity(),  getVideoItemList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), SPAN_COUNT);
    }

    private List<VideoItemModel> getVideoItemList() {
        List<VideoItemModel> mVideoItemList = new ArrayList<>();
        mVideoItemList.add(new VideoItemModel("财神殿", Environment.getExternalStorageDirectory() + "/vrvideo/zm.mp4"));
        mVideoItemList.add(new VideoItemModel("金鱼池", Environment.getExternalStorageDirectory() + "/vrvideo/jyc.mp4"));
        mVideoItemList.add(new VideoItemModel("三清殿1", Environment.getExternalStorageDirectory() + "/vrvideo/sqd1.mp4"));
        mVideoItemList.add(new VideoItemModel("三清殿2", Environment.getExternalStorageDirectory() + "/vrvideo/sqd2.mp4"));
        return mVideoItemList;
    }
}
