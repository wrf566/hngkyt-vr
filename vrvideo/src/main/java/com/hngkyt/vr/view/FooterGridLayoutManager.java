package com.hngkyt.vr.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wrf on 2017/1/11.
 */

public class FooterGridLayoutManager extends GridLayoutManager {

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_FOOTER = 2;
    public static final int ITEM_TYPE_HEADER = 1;

    public FooterGridLayoutManager(Context context, RecyclerView.Adapter adapter, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSpan(adapter);
    }

    private void initSpan(final RecyclerView.Adapter adapter) {
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case ITEM_TYPE_HEADER:
                    case ITEM_TYPE_FOOTER:
                        return getSpanCount();
                    case ITEM_TYPE_NORMAL:
                        return 1;
                    default:return 1;
                }
            }
        });
    }

    public FooterGridLayoutManager(Context context, RecyclerView.Adapter adapter, int spanCount) {
        super(context, spanCount);
        initSpan(adapter);
    }

    public FooterGridLayoutManager(Context context, RecyclerView.Adapter adapter, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        initSpan(adapter);

    }




}
