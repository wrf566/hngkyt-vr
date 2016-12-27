package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoGroupAdapter;
import com.hngkyt.vr.adapter.VideoItemAdapter;
import com.hngkyt.vr.decoration.VideoGroupDecoration;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.model.ResponseBean;
import com.hngkyt.vr.model.Video;
import com.hngkyt.vr.model.VideoChannelList;
import com.hngkyt.vr.model.VideoGroupList;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wrf on 2016/11/22.
 */

public class VideoChannelFragment extends RecyclerViewFragment {

    private VideoChannelList.VedioChannel mVedioChannel;
    private VideoGroupAdapter mVideoGroupAdapter;
    private VideoItemAdapter mVideoItemAdapter;

    private static final int SPAN_COUNT = 2;//列数
    private static final int ITEM_SPACE = 20;//item之间的距离
    private static final int ITEM_SPACE_GROUP = 40;//item之间的距离

    private VideoGroupDecoration mVideoGroupDecoration = new VideoGroupDecoration(ITEM_SPACE_GROUP);

    public static VideoChannelFragment newInstance(VideoChannelList.VedioChannel VedioChannel) {

        Bundle args = new Bundle();
        args.putParcelable(VideoChannelList.VedioChannel.class.getCanonicalName(), VedioChannel);
        VideoChannelFragment fragment = new VideoChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mVedioChannel = getArguments().getParcelable(VideoChannelList.VedioChannel.class.getCanonicalName());
        getVideoDataList();


    }

    private void getVideoDataList() {
        Call<ResponseBean> categoryVediosCall = mBaseActivity.mRequestService.getCategoryVedios(mVedioChannel.getId());

        ResultCall<VideoGroupList> categoryVediosResultCall = new ResultCall<>(getActivity(), VideoGroupList.class, false);

        categoryVediosResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VideoGroupList videoGroupList = (VideoGroupList) o;


                switch (videoGroupList.getType()) {
                    case VideoGroupList.TYPE_HASGROUP:
                        List<VideoGroupList.VideoList> vedioListBeen = videoGroupList.getVedioList();
                        if (vedioListBeen == null) {
                            vedioListBeen = new ArrayList<>();
                        }
                        Logger.e("vedioListBeen = " + vedioListBeen);
                        setHasGroupAdapter(vedioListBeen);
                        break;
                    case VideoGroupList.TYPE_NOGROUP:
                        List<Video> videoList = videoGroupList.getVedioList().get(0).getList();
                        if (videoList == null) {
                            videoList = new ArrayList<>();
                        }
                        Logger.e("videoList = " + videoList);
                        setNoGroupAdapter(videoList);
                        break;
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

        categoryVediosCall.enqueue(categoryVediosResultCall);
    }

    private void setNoGroupAdapter(List<Video> videoList) {
        if (mVideoItemAdapter == null) {
            mVideoItemAdapter = new VideoItemAdapter(getActivity(), videoList);
            mRecyclerView.setAdapter(mVideoItemAdapter);
            mRecyclerView.removeItemDecoration(mVideoGroupDecoration);//这里要加不然分割会重叠
            mRecyclerView.addItemDecoration(new VideoItemDecoration(ITEM_SPACE));
            mRecyclerView.setLayoutManager( new GridLayoutManager(getActivity(), SPAN_COUNT));
        } else {
            mVideoItemAdapter.setListBeanList(videoList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setHasGroupAdapter(List<VideoGroupList.VideoList> categoryVediosVedioList) {
        if (mVideoGroupAdapter == null) {
            mVideoGroupAdapter = new VideoGroupAdapter(getActivity(), categoryVediosVedioList);
            mRecyclerView.setAdapter(mVideoGroupAdapter);
        } else {
            mVideoGroupAdapter.setCategoryVediosVedioList(categoryVediosVedioList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return mVideoGroupDecoration;
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    @Override
    public void onRefresh() {
        getVideoDataList();
    }
}
