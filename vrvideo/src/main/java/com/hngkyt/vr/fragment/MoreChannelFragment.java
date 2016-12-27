package com.hngkyt.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoMoreChannelAdapter;
import com.hngkyt.vr.decoration.VideoChannelDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.net.been.VideoChannelList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 更多频道
 * Created by wrf on 2016/12/12.
 */

public class MoreChannelFragment extends RecyclerViewFragment {



    private static final int TYPE_MORE = 0;

    private VideoMoreChannelAdapter mVideoMoreChannelAdapter;

    @Override
    protected void initView(View view) {
        super.initView(view);


        initData();


    }

    private void initData() {
        Call<ResponseBean> vedioCateGoryCall = mBaseActivity.mRequestService.getVedioCategory(TYPE_MORE);

        ResultCall<VideoChannelList> listResultCall = new ResultCall<>(getActivity(), VideoChannelList.class,false);

        listResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VideoChannelList VideoChannelList = (VideoChannelList) o;
                List<com.hngkyt.vr.net.been.VideoChannelList.VedioChannel> vedioCategoryListBeanListBeen = VideoChannelList.getVedioCategoryList();
                if(vedioCategoryListBeanListBeen ==null){
                    vedioCategoryListBeanListBeen =new ArrayList<>();
                }
                setAdapter(vedioCategoryListBeanListBeen);


            }

            @Override
            public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {

            }
            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });

        vedioCateGoryCall.enqueue(listResultCall);
    }

    private void setAdapter(List<VideoChannelList.VedioChannel> vedioCategoryListBeanListBeen) {
        if(mVideoMoreChannelAdapter==null){
            mVideoMoreChannelAdapter = new VideoMoreChannelAdapter(getActivity(), vedioCategoryListBeanListBeen);
            mRecyclerView.setAdapter(mVideoMoreChannelAdapter);
        }else{
            mVideoMoreChannelAdapter.setVedioCategoryListBeanListBeen(vedioCategoryListBeanListBeen);
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
    }
}
