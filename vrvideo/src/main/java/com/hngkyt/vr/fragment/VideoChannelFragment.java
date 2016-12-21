package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoGroupAdapter;
import com.hngkyt.vr.decoration.VideoGroupDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.CategoryVedios;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.net.been.VedioCategoryList;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wrf on 2016/11/22.
 */

public class VideoChannelFragment extends RecyclerViewFragment {

    private VedioCategoryList.VedioCategoryListBean mVedioCategoryListBean;

    public static VideoChannelFragment newInstance(VedioCategoryList.VedioCategoryListBean vedioCategoryListBean) {

        Bundle args = new Bundle();
        args.putParcelable(VedioCategoryList.VedioCategoryListBean.class.getCanonicalName(), vedioCategoryListBean);
        VideoChannelFragment fragment = new VideoChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private VideoGroupAdapter mVideoGroupAdapter;

    @Override
    protected void initView(View view) {
        super.initView(view);

        mVedioCategoryListBean = getArguments().getParcelable(VedioCategoryList.VedioCategoryListBean.class.getCanonicalName());


        getVideoDataList();


    }

    private void getVideoDataList() {
        Call<ResponseBean> categoryVediosCall = mBaseActivity.mRequestService.getCategoryVedios(mVedioCategoryListBean.getId());

        ResultCall<CategoryVedios> categoryVediosResultCall = new ResultCall<>(getActivity(),CategoryVedios.class,false);

        categoryVediosResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                CategoryVedios categoryVedios = (CategoryVedios) o;
                Logger.e("categoryVedios = " + categoryVedios);
                List<CategoryVedios.VedioListBean> categoryVediosVedioList = categoryVedios.getVedioList();
//                CategoryVedios.VedioListBean vedioListBean = categoryVediosVedioList.get(0);
//                setAdapter(vedioListBean.getList());

                setAdapter(categoryVediosVedioList);
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });

        categoryVediosCall.enqueue(categoryVediosResultCall);
    }

    private void setAdapter(List<CategoryVedios.VedioListBean> categoryVediosVedioList) {
        if(mVideoGroupAdapter==null){
            mVideoGroupAdapter = new VideoGroupAdapter(getActivity(),categoryVediosVedioList);
            mRecyclerView.setAdapter(mVideoGroupAdapter);
        }else{
            mVideoGroupAdapter.setCategoryVediosVedioList(categoryVediosVedioList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoGroupDecoration(40);
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
