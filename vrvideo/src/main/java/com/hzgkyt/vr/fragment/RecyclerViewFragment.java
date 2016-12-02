package com.hzgkyt.vr.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hzgkyt.vr.R;

/**
 * Recyceler的Fragment
 * Created by wrf on 2016/12/1.
 */

public abstract class RecyclerViewFragment extends BaseFragment {

    protected RecyclerView mRecyclerView;


    @Override
    protected void initView(View view) {
        initRecyclerView(view);
    }

    private void initRecyclerView(View view){

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(initRecyclerViewAdapter());
        mRecyclerView.addItemDecoration(initRecyclerViewItemDecoration());
        mRecyclerView.setLayoutManager(initRecyclerViewLayoutManager());

    }


    /**
     * 初始化自定义的适配器
     * @return 相对应的适配器
     */
    protected abstract RecyclerView.Adapter initRecyclerViewAdapter();

    /**
     *  分割器初始化
     * @return 分割器
     */
    protected abstract RecyclerView.ItemDecoration initRecyclerViewItemDecoration();

    /**
     * 布局初始化
     * @return 布局
     */
    protected abstract RecyclerView.LayoutManager initRecyclerViewLayoutManager();
}
