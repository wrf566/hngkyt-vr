package com.hngkyt.vr.fragment;

import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hngkyt.vr.adapter.VideoGroupAdapter;
import com.hngkyt.vr.decoration.VideoGroupDecoration;
import com.hngkyt.vr.model.VideoGroupModel;
import com.hngkyt.vr.model.VideoItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrf on 2016/11/22.
 */

public class VideoChannelFragment extends RecyclerViewFragment {





    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoGroupAdapter(getActivity(), createViedoGroupModelList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoGroupDecoration(40);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    private List<VideoGroupModel> createViedoGroupModelList() {

        List<VideoGroupModel> videoGroupModelList = new ArrayList<>();

        videoGroupModelList.add(getVideoGroupModel("楼观台"
                , new String[]{
                        "楼观之路", "楼观台广场"
                }
                , new String[]{
                        Environment.getExternalStorageDirectory() + "/vrvideo/lgzl.mp4"
                        , Environment.getExternalStorageDirectory() + "/vrvideo/lgtgc.mp4"
                }));

        videoGroupModelList.add(getVideoGroupModel("财神"
                , new String[]{
                        "财神殿", "金鱼池"
                }
                , new String[]{
                        Environment.getExternalStorageDirectory() + "/vrvideo/csd.mp4"
                        , Environment.getExternalStorageDirectory() + "/vrvideo/jyc.mp4"
                }));


        videoGroupModelList.add(getVideoGroupModel("太极"
                , new String[]{
                        "太极台", "八卦池"
                }
                , new String[]{
                        Environment.getExternalStorageDirectory() + "/vrvideo/tjt.mp4"
                        , Environment.getExternalStorageDirectory() + "/vrvideo/bgc.mp4"
                }));



        return videoGroupModelList;
    }

    private VideoGroupModel getVideoGroupModel(String group, String[] names, String path[]) {
        VideoGroupModel viedoGroupModel = new VideoGroupModel();
        viedoGroupModel.setName(group);
        VideoItemModel[] videoItemModels = new VideoItemModel[2];
        videoItemModels[0] = new VideoItemModel(names[0], path[0]);
        videoItemModels[1] = new VideoItemModel(names[1], path[1]);
        viedoGroupModel.setVideoItemModels(videoItemModels);
        return  viedoGroupModel;
    }
}
