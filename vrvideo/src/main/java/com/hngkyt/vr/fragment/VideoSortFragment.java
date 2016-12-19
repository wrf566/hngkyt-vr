package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoItemAdapter;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.model.VideoItemModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/15.
 */

public class VideoSortFragment extends RecyclerViewFragment {

    public static final int SORT_BY_TIME = 1;//时间排序
    public static final int SORT_BY_PLAY = 2;//播放排序
    private static final int ITEM_SPACE = 20;//item之间的距离

    public static VideoSortFragment newInstance(int sortby) {

        Bundle args = new Bundle();
        args.putInt(VideoSortFragment.class.getCanonicalName(), sortby);
        VideoSortFragment fragment = new VideoSortFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        int sortby = getArguments().getInt(VideoSortFragment.class.getCanonicalName());
        Logger.e("sortby = "+sortby);

    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoItemAdapter(getActivity(), getVideoItemList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }

    private List<VideoItemModel> getVideoItemList() {
        List<VideoItemModel> mVideoItemList = new ArrayList<>();
//        File file = new File(Environment.getExternalStorageDirectory() + "/vrvideo");

//        File[] files = file.listFiles();

//        for (File file1 : files) {
//            mVideoItemList.add(new VideoItemModel(file1.getName(), file1.getAbsolutePath()));
//        }


                mVideoItemList.add(new VideoItemModel("楼观台广场", Environment.getExternalStorageDirectory() + "/vrvideo/lgtgc.mp4"));
        //        mVideoItemList.add(new VideoItemModel("财神殿", Environment.getExternalStorageDirectory() + "/vrvideo/csd.mp4"));
        //        mVideoItemList.add(new VideoItemModel("金鱼池", Environment.getExternalStorageDirectory() + "/vrvideo/jyc.mp4"));
        //        mVideoItemList.add(new VideoItemModel("太极台", Environment.getExternalStorageDirectory() + "/vrvideo/tjt.mp4"));
        //        mVideoItemList.add(new VideoItemModel("八卦池", Environment.getExternalStorageDirectory() + "/vrvideo/bgc.mp4"));

        return mVideoItemList;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
