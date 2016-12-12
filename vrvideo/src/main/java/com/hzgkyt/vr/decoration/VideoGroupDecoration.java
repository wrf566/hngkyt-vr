package com.hzgkyt.vr.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wrf on 2016/12/5.
 */

public class VideoGroupDecoration extends RecyclerView.ItemDecoration{

    private int mSpace;

    public VideoGroupDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position == 0) {
            outRect.top = 40;
        }
        outRect.bottom = mSpace;
    }
}
