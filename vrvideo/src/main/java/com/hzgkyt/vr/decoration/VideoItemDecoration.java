package com.hzgkyt.vr.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by wrf on 2016/12/1.
 */

public class VideoItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public VideoItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);

        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof GridLayoutManager) {
            Logger.e("GridLayoutManager");
            if (position % 2 == 0) {
                outRect.right = mSpace;
            }
            outRect.bottom = mSpace;
            return;
        }


        if (layoutManager instanceof LinearLayoutManager) {
            Logger.e("LinearLayoutManager");
            outRect.right = mSpace;
        }



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
