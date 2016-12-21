package com.hngkyt.vr.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/15.
 */

public class GroupFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mFragmentTitleList;
    private Context mContext;

    public GroupFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragmentTitleList.isEmpty()) {
            return super.getPageTitle(position);
        } else {
            return mFragmentTitleList.get(position);
        }

    }
}
