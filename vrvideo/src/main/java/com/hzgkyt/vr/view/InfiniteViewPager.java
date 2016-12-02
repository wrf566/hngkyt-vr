package com.hzgkyt.vr.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 无限循环Viewpager
 * Created by wrf on 2016/12/2.
 */

public class InfiniteViewPager extends ViewPager {

    private TabSelectListener mTabSelectListener;

    public void setTabSelectListener(TabSelectListener tabSelectListener) {
        mTabSelectListener = tabSelectListener;
    }

    public InfiniteViewPager(Context context) {
        super(context);
        addOnPageChangeListener(mOnPageChangeListener);
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(mOnPageChangeListener);
    }

    /**
     *  页面结构  c a b c a

     */
    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition;

        @Override
        public void onPageScrolled(int positon, float baifen, int offset) {
        }

        @Override
        public void onPageSelected(int position) {
            if (getAdapter().getCount() == 0)
                return;
            currentPosition = position;
            if (position == 0) {
                currentPosition = getAdapter().getCount() - 2;
            } else if (position == getAdapter().getCount() - 1) {
                currentPosition = 1;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (getAdapter().getCount() == 0)
                return;
            if (state == ViewPager.SCROLL_STATE_IDLE) {
//                mTabSelectListener.selectTab(currentPosition);
                setCurrentItem(currentPosition, false);
            }
        }
    };


    public interface TabSelectListener{
        void selectTab(int position);
    }


}
