package com.hngkyt.vr.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        if (position == 0||position == 1) {
            outRect.top = 40;
        }
        if (position % 2 == 0) {
            outRect.right = mSpace;
        }
        outRect.bottom = mSpace;


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }



    //1.取消列表的横向滑动
    //2.更多页面添加排序
    //3.视频的详情
    //4.视频分类可以滑动
}
