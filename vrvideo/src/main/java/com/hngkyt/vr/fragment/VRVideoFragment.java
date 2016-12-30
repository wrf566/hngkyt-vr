package com.hngkyt.vr.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hngkyt.vr.R;
import com.hngkyt.vr.adapter.VideoRecommendAdapter;
import com.hngkyt.vr.decoration.VideoRecommeneItemDecotation;
import com.hngkyt.vr.model.ResponseBean;
import com.hngkyt.vr.model.User;
import com.hngkyt.vr.model.VedioList;
import com.hngkyt.vr.model.Video;
import com.hngkyt.vr.net.ResultCall;
import com.hzgktyt.vr.baselibrary.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 视频播放页面
 * Created by wrf on 2016/12/5.
 */

public class VRVideoFragment extends RecyclerViewFragment implements View.OnClickListener {


    private static final String STATE_IS_PLAY = "isPlay";
    private static final String STATE_CURRENT_POSITION = "state_current_position";
    private static final String STATE_VIDEO_DURATION = "videoDuration";
    private static final int DISPLAYMODE_PORTRAIT = 1;
    private static final int DISPLAYMODE_LANDSCAPE = 2;
    private static final int DISPLAYMODE_STEREO = 3;
    private FrameLayout mFrameLayoutController;
    private CheckBox mCheckBoxPlay;
    private TextView mTextViewCurrentTime;
    private TextView mTextViewTotaltime;
    private ImageView mImageViewFullScreen;//全屏模式
    private ImageView mImageViewBack;//返回
    private ImageView mImageViewStereo;//立体模式
    private VrVideoView mVrVideoView;
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;
    private Video mVideo;//视频实体类
    private boolean isCompletion = false;
    private VideoLoaderTask mVideoLoaderTask;

    private TextView mTextViewName;
    private TextView mTextViewPlayCounts;
    private TextView mTextViewReleaseTime;


    private CompoundButton.OnCheckedChangeListener playChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Logger.e("isChecked = " + isChecked);

            //第一次运行
            if (mVideoLoaderTask == null) {
                mVideoLoaderTask = new VideoLoaderTask();
                mVideoLoaderTask.execute(mVideo.getVedioUrl());
                return;
            }
            //重新播放
            if (isCompletion) {
                if (isChecked) {
                    Logger.e("重新播放");
                    mVrVideoView.seekTo(0);
                    isCompletion = false;
                }
            } else {
                if (isChecked) {
                    mVrVideoView.playVideo();
                } else {
                    mVrVideoView.pauseVideo();
                }
            }
        }
    };
    /**
     * 只有当用户拖动进度条的时候改变播放时间
     */
    private SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mTextViewCurrentTime.setText(DateUtils.formatElapsedTime(progress / 1000));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Logger.e("onStopTrackingTouch");
            mProgressBar.setVisibility(View.VISIBLE);
            if (seekBar.getProgress() != seekBar.getMax()) {
                isCompletion = false;
            }
            //这里是防止播放完毕后用户再拖动进度条，如果光调用seekTo()，会直接播放
            //但是实际上，我们的按钮是可播放样式，所以要立马暂停
            //其实未播放完毕的时候调用seekTo是不会影响暂停和播放状态的
            //即拖动前是在播放或暂停那么拖动后也是播放或暂停，但是只要播放完毕了，那么调用seekTo就无视之前的状态自动播放
            //由于播放完毕了就表示视频已经暂停了，所以播放完毕的拖动全部都是暂停的，除非用户手动点击播放
            mVrVideoView.seekTo(seekBar.getProgress());
            if (!mCheckBoxPlay.isChecked()) {
                mVrVideoView.pauseVideo();
            }

        }


    };
    private VideoRecommendAdapter mVideoRecommendAdapter;
    private List<Video> mListVideo;
    private boolean isAutoNext;

    public static VRVideoFragment newInstance(Video listBean) {

        Bundle args = new Bundle();
        args.putParcelable(Video.class.getCanonicalName(), listBean);

        VRVideoFragment fragment = new VRVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 向服务器提交播放记录
     */
    private void putPlaycount() {
        User userInfo = mBaseActivity.getUserInfo();
        int userId;
        if (userInfo == null) {
            userId = 0;
        } else {
            userId = userInfo.getId();
        }
        Logger.e("mVideo.getId() = " + mVideo.getId());
        Logger.e("userId =  " + userId);
        Call<ResponseBean> responseBeanCall = mBaseActivity.mRequestService.playAmont(mVideo.getId(), userId);
        ResultCall<String> resultCall = new ResultCall<>(getActivity(), String.class, false);
        responseBeanCall.enqueue(resultCall);
    }

    @Override
    protected int intLayoutResId() {
        Logger.e("VRVideoFragment");
        return R.layout.fragment_vrvideo;
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoRecommeneItemDecotation(getActivity(), VideoRecommeneItemDecotation.VERTICAL_LIST);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new LinearLayoutManager(getActivity());

    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        isAutoNext = mBaseActivity.mSPUtils.getBoolean(String.valueOf(R.id.radiobutton_personal_center_autoplay_next), false);

        mFrameLayoutController = (FrameLayout) view.findViewById(R.id.framelayout_vrvideo_controller);
        mCheckBoxPlay = (CheckBox) view.findViewById(R.id.checkbox_vrvideo_play);

        mTextViewCurrentTime = (TextView) view.findViewById(R.id.textview_vrvideo_currenttime);
        mTextViewTotaltime = (TextView) view.findViewById(R.id.textview_vrvideo_totaltime);


        mTextViewName = (TextView) view.findViewById(R.id.textview_vrvideo_name);
        mTextViewPlayCounts = (TextView) view.findViewById(R.id.textview_vrvideo_play_counts);
        mTextViewReleaseTime = (TextView) view.findViewById(R.id.textview_vrvideo_release_time);

        mImageViewFullScreen = (ImageView) view.findViewById(R.id.imageview_vrvideo_fullscreen);
        mImageViewBack = (ImageView) view.findViewById(R.id.imageview_vrvideo_back);
        mImageViewStereo = (ImageView) view.findViewById(R.id.imageview_vrvideo_stereo);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar_vrvideo);


        mImageViewFullScreen.setOnClickListener(this);
        mImageViewBack.setOnClickListener(this);
        mImageViewStereo.setOnClickListener(this);
        mCheckBoxPlay.setOnCheckedChangeListener(playChangeListener);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_vrvideo);
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        //第一次进来还没播放的时候就不让拖动
        mSeekBar.setEnabled(false);

        mVideo = getArguments().getParcelable(Video.class.getCanonicalName());

        //这里要单独获取视频详情是因为，用户点击了播放，添加了播放次数然后回到视频列表页面，再点击同样一个视频。
        //由于回到视频列表，列表中的视频数据还是旧的，所以要单独拉取。
        getVideoDetail();


        mVrVideoView = (VrVideoView) view.findViewById(R.id.vrvideoview_vrvideo);
        mVrVideoView.setEventListener(new ActivityEventListener());

        mVrVideoView.setDisplayMode(DISPLAYMODE_PORTRAIT);


        hideDefaultViews(mVrVideoView);


        setVideoInfo();

    }


    /**
     * 获取视频详情
     */
    private void getVideoDetail() {
        Call<ResponseBean> vedioDetailCall = mBaseActivity.mRequestService.getVedioDetail(mVideo.getId());

        ResultCall<Video> responseBeanResultCall = new ResultCall<>(getActivity(), Video.class, false);
        responseBeanResultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                mVideo = (Video) o;
                setVideoInfo();
                initRecommendListData();

            }

            @Override
            public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

                initRecommendListData();
            }
        });
        vedioDetailCall.enqueue(responseBeanResultCall);

    }

    private void setVideoInfo() {
        mTextViewName.setText(mVideo.getVedioName());
        mTextViewPlayCounts.setText(getResources().getString(R.string.play_counts, mVideo.getPlayAmount()));
        mTextViewReleaseTime.setText(getResources().getString(R.string.release_time
                , new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(mVideo.getAddTime()))
        ));

    }

    /**
     * 初始化推荐列表数据
     */
    private void initRecommendListData() {
        Call<ResponseBean> vedioDetailCall = mBaseActivity.mRequestService.getVedios(mVideo.getVedioCategoryId(), VideoSortFragment.SORT_BY_TIME);

        ResultCall<VedioList> resultCall = new ResultCall<>(getActivity(), VedioList.class, false);
        resultCall.setOnCallListener(new ResultCall.OnCallListener() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {
                VedioList vedioList = (VedioList) o;
                if (vedioList.getVedioList() != null) {
                    mListVideo = vedioList.getVedioList();
                    mListVideo.remove(mVideo);//这里要把自己排除掉
                    setAdapter(vedioList.getVedioList());
                }
            }

            @Override
            public void onResponseNoData(Call<ResponseBean> call, Response<ResponseBean> response, Object o) {

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
        vedioDetailCall.enqueue(resultCall);

    }

    private void setAdapter(final List<Video> videoList) {
        if (mVideoRecommendAdapter == null) {
            mVideoRecommendAdapter = new VideoRecommendAdapter(getActivity(), videoList);
            mRecyclerView.setAdapter(mVideoRecommendAdapter);
        } else {
            mVideoRecommendAdapter.setVideoList(videoList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        initRecommendListData();
    }


    /**
     * 隐藏默认的播放控制按钮
     *
     * @param vrVideoView VR播放组件
     */
    private void hideDefaultViews(VrVideoView vrVideoView) {

        vrVideoView.setFullscreenButtonEnabled(false);
        vrVideoView.setInfoButtonEnabled(false);
        vrVideoView.setStereoModeButtonEnabled(false);
        vrVideoView.setTransitionViewEnabled(false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //        Logger.e("onActivityCreated");
        if (savedInstanceState != null) {
            Logger.e("当然不是空的啦");
            mVrVideoView.seekTo(savedInstanceState.getLong(STATE_CURRENT_POSITION));
            mSeekBar.setMax((int) savedInstanceState.getLong(STATE_VIDEO_DURATION));
            mSeekBar.setProgress((int) savedInstanceState.getLong(STATE_CURRENT_POSITION));
            mCheckBoxPlay.setChecked(savedInstanceState.getBoolean(STATE_IS_PLAY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //        Logger.e("onSaveInstanceState");
        savedInstanceState.putLong(STATE_CURRENT_POSITION, mVrVideoView.getCurrentPosition());
        savedInstanceState.putLong(STATE_VIDEO_DURATION, mVrVideoView.getDuration());
        savedInstanceState.putBoolean(STATE_IS_PLAY, mCheckBoxPlay.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {
        //        Logger.e("onResume");
        mVrVideoView.resumeRendering();

        super.onResume();
    }

    @Override
    public void onPause() {
        //        Logger.e("onPause");
        mVrVideoView.pauseRendering();
        //应用被遮盖要暂停
        mCheckBoxPlay.setChecked(false);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        //        Logger.e("onDestory");
        mVrVideoView.shutdown();
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_vrvideo_fullscreen:
                mVrVideoView.setDisplayMode(DISPLAYMODE_LANDSCAPE);
                break;
            case R.id.imageview_vrvideo_back:
                getActivity().onBackPressed();
                break;
            case R.id.imageview_vrvideo_stereo:
                //如果视频没有在播放那么播放
                if (!mCheckBoxPlay.isChecked()) {
                    mCheckBoxPlay.performClick();
                }
                mVrVideoView.setDisplayMode(DISPLAYMODE_STEREO);

                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Logger.e("onCreate");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //        Logger.e("onDetach");
    }


    /**
     * Listen to the important events from widget.
     */
    private class ActivityEventListener extends VrVideoEventListener {

        /**
         * Called by video widget on the UI thread when it's done loading the video.
         */
        @Override
        public void onLoadSuccess() {
            Logger.e("onLoadSuccess");
            putPlaycount();//添加服务器播放数量
            mProgressBar.setVisibility(View.GONE);
            mSeekBar.setEnabled(true);//运行加载完后进度条才能拖动
            mSeekBar.setMax((int) mVrVideoView.getDuration());//设置总的播放进度条
            mTextViewTotaltime.setText(DateUtils.formatElapsedTime(mVrVideoView.getDuration() / 1000));//设置总的播放时间
            if (mBaseActivity.mSPUtils.getBoolean(String.valueOf(R.id.switch_personal_center_stereo), true)) {
                mVrVideoView.setDisplayMode(DISPLAYMODE_STEREO);
            } else {
                mVrVideoView.setDisplayMode(DISPLAYMODE_PORTRAIT);
            }

        }

        /**
         * Called by video widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
            Logger.e("errorMessage = " + errorMessage);
            mProgressBar.setVisibility(View.GONE);
            ToastUtils.showShortToast(getActivity(), R.string.video_load_error);
        }

        @Override
        public void onClick() {

            if (mVrVideoView.getDisplayMode() == DISPLAYMODE_PORTRAIT) {
                if (mFrameLayoutController.getVisibility() == View.VISIBLE) {
                    mFrameLayoutController.setVisibility(View.INVISIBLE);
                } else {
                    mFrameLayoutController.setVisibility(View.VISIBLE);

                }
            }


        }

        /**
         * Update the UI every frame.
         */
        @Override
        public void onNewFrame() {
            if (mProgressBar.getVisibility() == View.VISIBLE) {
                mProgressBar.setVisibility(View.GONE);
            }

            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());
            mTextViewCurrentTime.setText(DateUtils.formatElapsedTime(mVrVideoView.getCurrentPosition() / 1000));

        }


        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
            Logger.e("onCompletion");
            if (!isAutoNext) {
                mVrVideoView.seekTo(0);
            } else {
                isCompletion = true;
                mCheckBoxPlay.setChecked(false);
            }

            if (isAutoNext) {
                Logger.e("自动下一个视频");
                int i = mListVideo.indexOf(mVideo);
                if (i != mListVideo.size() - 1) {
                    mVideo = mListVideo.get(i + 1);
                    Logger.e("mVideo = " + mVideo);
                    mListVideo.remove(mVideo);
                    mVideoRecommendAdapter.notifyDataSetChanged();
                    setVideoInfo();
                    mVideoLoaderTask = null;
                    mVrVideoView.seekTo(0);
                    mCheckBoxPlay.setChecked(true);
                }

            }


        }


        @Override
        public void onDisplayModeChanged(int newDisplayMode) {
            super.onDisplayModeChanged(newDisplayMode);
            Logger.e("newDisplayMode = " + newDisplayMode);
            switch (newDisplayMode) {
                case DISPLAYMODE_PORTRAIT://竖屏
                    break;
                case DISPLAYMODE_LANDSCAPE://普通全屏
                    break;
                case DISPLAYMODE_STEREO://立体全屏
                    break;

            }
            //            mProgressBar.setVisibility(View.VISIBLE);
        }


    }

    /**
     * Helper class to manage threading.
     */
    class VideoLoaderTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSeekBar.setProgress(0);
            mProgressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                VrVideoView.Options options = new VrVideoView.Options();
                options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
                //                mVrVideoView.loadVideo(Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"), options);
                Logger.e("在线视频URL = " + Uri.parse(urls[0]));
                mVrVideoView.loadVideo(Uri.parse(urls[0]), options);
            } catch (IOException e) {
                e.printStackTrace();

            }

            return true;
        }
    }
}
