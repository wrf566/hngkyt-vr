package com.hngkyt.vr.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.GroupFragmentAdapter;

/**
 * 更多页面
 * <p>
 * Created by wrf on 2016/12/2.
 */

public class MoreGroupFragment extends BaseFragment {


    private TabLayout mTabLayoutSort;
    private ViewPager mViewPager;
    private GroupFragmentAdapter mGroupFragmentAdapter;

    @Override
    protected void initView(View view) {
        mTabLayoutSort = (TabLayout) view.findViewById(R.id.tablayout_moregroup_sort);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_moregroup);
        mGroupFragmentAdapter = new GroupFragmentAdapter(getActivity(),mBaseActivity.getSupportFragmentManager());
        mGroupFragmentAdapter.addFragment(VideoSortFragment.newInstance(VideoSortFragment.SORT_BY_TIME),getString(R.string.sort_by_time));
        mGroupFragmentAdapter.addFragment(VideoSortFragment.newInstance(VideoSortFragment.SORT_BY_PLAY),getString(R.string.sort_by_play));
        mViewPager.setAdapter(mGroupFragmentAdapter);
        mTabLayoutSort.setupWithViewPager(mViewPager);
    }


    @Override
    protected int intLayoutResId() {
        return R.layout.fragment_moregroup;
    }

}