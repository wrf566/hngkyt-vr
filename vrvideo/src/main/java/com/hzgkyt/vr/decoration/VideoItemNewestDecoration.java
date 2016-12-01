package com.hzgkyt.vr.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemNewestDecoration extends RecyclerView.ItemDecoration {

    private int mBottomSpace;
    private int mRightSpace;

    public VideoItemNewestDecoration(int bottomSpace,int rightSpace) {
        mBottomSpace = bottomSpace;
        mRightSpace = rightSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = mBottomSpace;
        outRect.right = mRightSpace;


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
