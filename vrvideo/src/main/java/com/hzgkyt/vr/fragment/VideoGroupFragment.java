package com.hzgkyt.vr.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hzgkyt.vr.R;
import com.hzgkyt.vr.adapter.VideoGroupAdapter;
import com.hzgkyt.vr.decoration.VideoGroupDecoration;
import com.hzgkyt.vr.model.ViedoGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/11/22.
 */

public class VideoGroupFragment extends RecyclerViewFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    private List<ViedoGroupModel> mViedoGroupModelList;

    @Override
    protected int intLayoutResId() {
        return R.layout.fragment_videogroup;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        createViedoGroupModelList();
        return new VideoGroupAdapter(getActivity(), mViedoGroupModelList);
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoGroupDecoration(40);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    private void createViedoGroupModelList() {

        mViedoGroupModelList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            ViedoGroupModel viedoGroupModel = new ViedoGroupModel();
            viedoGroupModel.setName("宗教 " + i);
            mViedoGroupModelList.add(viedoGroupModel);
        }


    }
}
