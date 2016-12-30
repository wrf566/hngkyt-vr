package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoItemAdapter;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.model.VideoGroupList;
import com.hngkyt.vr.model.ResponseBean;
import com.hngkyt.vr.model.VideoList;
import com.hngkyt.vr.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 排序列表
 * Created by wrf on 2016/12/15.
 */

public class VideoSortFragment extends RecyclerViewFragment {

    public static final int SORT_BY_TIME = 1;//时间排序
    public static final int SORT_BY_PLAY = 2;//播放排序
    private static final int ITEM_SPACE = 20;//item之间的距离

    private VideoGroupList.VideoList mVideoList;
    private int sortby;

    private VideoItemAdapter mVideoItemAdapter;

    public static VideoSortFragment newInstance(VideoGroupList.VideoList videoList, int sortby) {

        Bundle args = new Bundle();
        args.putInt(VideoSortFragment.class.getCanonicalName(), sortby);
        args.putParcelable(VideoGroupList.VideoList.class.getCanonicalName(), videoList);
        VideoSortFragment fragment = new VideoSortFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        sortby = getArguments().getInt(VideoSortFragment.class.getCanonicalName());
        mVideoList = getArguments().getParcelable(VideoGroupList.VideoList.class.getCanonicalName());
        getVideoList();

    }

    private void getVideoList() {
        Call<ResponseBean> responseBeanCall = mBaseActivity.mRequestService.getVedios(mVideoList.getId(), sortby);

        ResultCall<VideoList> resultCall = new ResultCall<>(getActivity(), VideoList.class,false);
        resultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VideoList videoList = (VideoList) o;
                if (videoList.getVedioList() != null) {
                    setAdapter(videoList.getVedioList());
                }

            }

            @Override
            public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {

            }
            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        responseBeanCall.enqueue(resultCall);
    }

    private void setAdapter(List<Video> listBeen) {
        if (mVideoItemAdapter == null) {
            mVideoItemAdapter = new VideoItemAdapter(getActivity(), listBeen);
            mRecyclerView.setAdapter(mVideoItemAdapter);
        } else {
            mVideoItemAdapter.setListBeanList(listBeen);
        }
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void onRefresh() {
        getVideoList();
    }
}
