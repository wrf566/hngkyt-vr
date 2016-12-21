package com.hngkyt.vr.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.R;

/**
 * Recyceler的Fragment
 * Created by wrf on 2016/12/1.
 */

public abstract class RecyclerViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initView(View view) {
        initRecyclerView(view);
    }

    private void initRecyclerView(View view) {

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (initRecyclerViewItemDecoration() != null) {//这里有可能不添加，因为不需要分割线
            mRecyclerView.addItemDecoration(initRecyclerViewItemDecoration());
        }
        mRecyclerView.setLayoutManager(initRecyclerViewLayoutManager());

    }

    @Override
    protected int intLayoutResId() {
        return R.layout.include_recyclerview;
    }


    /**
     * 初始化自定义的适配器
     *
     * @return 相对应的适配器
     */
//    protected abstract RecyclerView.Adapter initRecyclerViewAdapter(); //并不可以搞个抽象方法，因为adapter初始化是异步的

    /**
     * 分割器初始化
     *
     * @return 分割器
     */
    protected abstract RecyclerView.ItemDecoration initRecyclerViewItemDecoration();

    /**
     * 布局初始化
     *
     * @return 布局
     */
    protected abstract RecyclerView.LayoutManager initRecyclerViewLayoutManager();



}
