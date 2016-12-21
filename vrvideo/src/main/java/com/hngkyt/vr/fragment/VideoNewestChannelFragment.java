package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hngkyt.vr.adapter.VideoItemAdapter;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.CategoryVedios;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.net.been.VideoBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoNewestChannelFragment extends RecyclerViewFragment {

    private static final int SPAN_COUNT = 2;//列数
    private static final int ITEM_SPACE = 20;//item之间的距离

    private VideoItemAdapter mVideoItemAdapter;

    @Override
    protected void initView(View view) {
        super.initView(view);
//        Logger.e("initView");
        getVideoDataList();


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Logger.e("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Logger.e("onViewCreated");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Logger.e("onActivityCreated");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Logger.e("onCreate");

    }

    @Override
    public void onStart() {
        super.onStart();
//        Logger.e("onStart");

    }


    @Override
    public void onPause() {
        super.onPause();
//        Logger.e("onPause");

    }


    @Override
    public void onStop() {
        super.onStop();
//        Logger.e("onStop");

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        Logger.e("onDestroy");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Logger.e("onDestroyView");

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Logger.e("onDetach");

    }



    @Override
    public void onResume() {
        super.onResume();
//        Logger.e("onResume");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Logger.e("onSaveInstanceState");

    }

    private void getVideoDataList() {
        Call<ResponseBean> categoryVediosCall = mBaseActivity.mRequestService.getCategoryVedios(0);
        ResultCall<CategoryVedios> categoryVediosResultCall = new ResultCall<>(getActivity(), CategoryVedios.class,false);
        categoryVediosResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                CategoryVedios categoryVedios = (CategoryVedios) o;
                Logger.e("categoryVedios = " + categoryVedios);
                List<CategoryVedios.VedioListBean> categoryVediosVedioList = categoryVedios.getVedioList();
                CategoryVedios.VedioListBean vedioListBean = categoryVediosVedioList.get(0);
                setAdapter(vedioListBean.getList());

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        categoryVediosCall.enqueue(categoryVediosResultCall);
    }

    private void setAdapter(List<VideoBean> listBeen) {
        if(mVideoItemAdapter==null){
            mVideoItemAdapter = new VideoItemAdapter(getActivity(),listBeen);
            mRecyclerView.setAdapter(mVideoItemAdapter);
        }else{
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
        return new GridLayoutManager(getActivity(), SPAN_COUNT);
    }



    @Override
    public void onRefresh() {
        getVideoDataList();
    }
}
