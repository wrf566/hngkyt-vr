package com.hngkyt.vr.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.GroupFragmentAdapter;
import com.hngkyt.vr.fragment.VideoSortFragment;
import com.hngkyt.vr.net.been.CategoryVedios;
import com.orhanobut.logger.Logger;

/**
 * Created by wrf on 2016/12/2.
 */

public class MoreGroupActivity extends TitleBarActivity {

    private TabLayout mTabLayoutSort;
    private ViewPager mViewPager;
    private GroupFragmentAdapter mGroupFragmentAdapter;

    @Override
    protected void initView() {
        super.initView();
        CategoryVedios.VedioListBean vedioListBean = getIntent().getParcelableExtra(CategoryVedios.VedioListBean.class.getCanonicalName());
        Logger.e("vedioListBean = "+vedioListBean);

        setTextViewTitle(vedioListBean.getName());

        mTabLayoutSort = (TabLayout) findViewById(R.id.tablayout_moregroup_sort);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_moregroup);
        mGroupFragmentAdapter = new GroupFragmentAdapter(this, getSupportFragmentManager());
        mGroupFragmentAdapter.addFragment(VideoSortFragment.newInstance(vedioListBean,VideoSortFragment.SORT_BY_TIME), getString(R.string.sort_by_time));
        mGroupFragmentAdapter.addFragment(VideoSortFragment.newInstance(vedioListBean,VideoSortFragment.SORT_BY_PLAY), getString(R.string.sort_by_play));
        mViewPager.setAdapter(mGroupFragmentAdapter);
        mTabLayoutSort.setupWithViewPager(mViewPager);

    }

    @Override
    protected int intLayoutResId() {
        return R.layout.activity_moregroup;
    }

}
