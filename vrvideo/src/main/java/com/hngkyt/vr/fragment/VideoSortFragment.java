package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.adapter.VideoItemAdapter;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.net.ResultCall;
import com.hngkyt.vr.net.been.CategoryVedios;
import com.hngkyt.vr.net.been.ResponseBean;
import com.hngkyt.vr.net.been.VedioList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by wrf on 2016/12/15.
 */

public class VideoSortFragment extends RecyclerViewFragment {

    public static final int SORT_BY_TIME = 1;//时间排序
    public static final int SORT_BY_PLAY = 2;//播放排序
    private static final int ITEM_SPACE = 20;//item之间的距离

    private CategoryVedios.VedioListBean mVedioListBean;
    private int sortby;

    private VideoItemAdapter mVideoItemAdapter;

    public static VideoSortFragment newInstance(CategoryVedios.VedioListBean vedioListBean, int sortby) {

        Bundle args = new Bundle();
        args.putInt(VideoSortFragment.class.getCanonicalName(), sortby);
        args.putParcelable(CategoryVedios.VedioListBean.class.getCanonicalName(), vedioListBean);
        VideoSortFragment fragment = new VideoSortFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        sortby = getArguments().getInt(VideoSortFragment.class.getCanonicalName());
        mVedioListBean = getArguments().getParcelable(CategoryVedios.VedioListBean.class.getCanonicalName());
        getVideoList();

    }

    private void getVideoList() {
        Call<ResponseBean> responseBeanCall = mBaseActivity.mRequestService.getVedios(mVedioListBean.getId(), sortby);

        ResultCall<VedioList> resultCall = new ResultCall<>(getActivity(), VedioList.class);
        resultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VedioList vedioList = (VedioList) o;
//                mVideoItemAdapter = new VideoItemAdapter()

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

            }
        });
        responseBeanCall.enqueue(resultCall);
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
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
