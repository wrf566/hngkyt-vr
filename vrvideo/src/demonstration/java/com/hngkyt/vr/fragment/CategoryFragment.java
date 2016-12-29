package com.hngkyt.vr.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hngkyt.vr.activity.LocalMainActivity;
import com.hngkyt.vr.adapter.LocalVideoAdapter;
import com.hngkyt.vr.decoration.VideoItemDecoration;
import com.hngkyt.vr.model.LocalVideo;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wrf on 2016/11/22.
 */

public class CategoryFragment extends RecyclerViewFragment {

    private static final int SPAN_COUNT = 2;//列数
    private static final int ITEM_SPACE = 20;//item之间的距离
    private File mCategoryFile;
    private LocalVideoAdapter mLocalVideoAdapter;

    public static CategoryFragment newInstance(File categoryFile) {

        Bundle args = new Bundle();
        args.putSerializable(File.class.getCanonicalName(), categoryFile);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mCategoryFile = (File) getArguments().getSerializable(File.class.getCanonicalName());
        setNoGroupAdapter(getLocalVideos());

    }

    private List<LocalVideo> getLocalVideos() {
        List<LocalVideo> localVideoList = new ArrayList<>();
        if (mCategoryFile.getAbsolutePath().equals(LocalMainActivity.FILE_ROOT.getAbsolutePath())) {
            List<File> videoList = new ArrayList<>();
            List<File> coverList = new ArrayList<>();

            File[] categoryfiles = mCategoryFile.listFiles();
            for (int i = 0; i < categoryfiles.length; i++) {
                videoList.addAll(Arrays.asList(getFiles(categoryfiles[i], ".mp4")));
                coverList.addAll(Arrays.asList(getFiles(categoryfiles[i], ".jpg")));
            }
            for (int i = 0; i < videoList.size(); i++) {
                localVideoList.add(new LocalVideo(videoList.get(i),coverList.get(i)));
            }


        } else {
            File[] viodeFiles = getFiles(mCategoryFile, ".mp4");
            File[] coverFiles = getFiles(mCategoryFile, ".jpg");

            for (int i = 0; i < viodeFiles.length; i++) {
                localVideoList.add(new LocalVideo(viodeFiles[i], coverFiles[i]));
            }
        }


        return localVideoList;
    }

    private File[] getFiles(File categoryFile, final String extensionName) {
        return categoryFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {

                return name.toLowerCase().endsWith(extensionName);
            }
        });


    }


    private void setNoGroupAdapter(List<LocalVideo> localVideoList) {
        if (mLocalVideoAdapter == null) {
            mLocalVideoAdapter = new LocalVideoAdapter(getActivity(), localVideoList);
            mRecyclerView.setAdapter(mLocalVideoAdapter);
        } else {
            mLocalVideoAdapter.setLocalVideoList(localVideoList);
        }
    }


    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), SPAN_COUNT);
    }


    @Override
    public void onRefresh() {
        getLocalVideos();
        mSwipeRefreshLayout.setRefreshing(false);

    }
}
