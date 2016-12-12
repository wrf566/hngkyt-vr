package com.hzgkyt.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.adapter.VideoChannelAdapter;
import com.hzgkyt.vr.decoration.VideoChannelDecoration;
import com.hzgkyt.vr.model.VideoChannelModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/12/12.
 */

public class MoreChannelFragment extends RecyclerViewFragment {

    private int[] drawableIDs = {R.drawable.creativity, R.drawable.variety, R.drawable.horror, R.drawable.pat, R.drawable.music, R.drawable.sport};


    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoChannelAdapter(getActivity(), initCHannelList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoChannelDecoration(100);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), 3);
    }

    private List<VideoChannelModel> initCHannelList() {
        List<VideoChannelModel> videoChannelModelList = new ArrayList<>();

        String[] names = getResources().getStringArray(R.array.array_channel_more);

        for (int i = 0; i < drawableIDs.length; i++) {
            VideoChannelModel videoChannelModel = new VideoChannelModel();
            videoChannelModel.setName(names[i]);
            videoChannelModel.setDrawableID(drawableIDs[i]);
            videoChannelModelList.add(videoChannelModel);
        }


        return videoChannelModelList;
    }


}
