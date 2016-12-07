package com.hzgkyt.vr.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.vr.sdk.widgets.common.VrWidgetRenderer;
import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.VideoItemModel;
import com.orhanobut.logger.Logger;

import java.io.IOException;

/**
 * Created by wrf on 2016/12/5.
 */

public class VRVideoFragment extends BaseFragment implements View.OnClickListener {


    public static final String VRVIDEO_PATH = "vrvideo_path";
    public static final String VRVIDEO_NAME = "vrvideo_name";
    private static final String STATE_IS_PLAY = "isPlay";
    private static final String STATE_PROGRESS_TIME = "progressTime";
    private static final String STATE_VIDEO_DURATION = "videoDuration";

    private FrameLayout mFrameLayoutController;
    private CheckBox mCheckBoxPlay;

    private TextView mTextViewCurrentTime;
    private TextView mTextViewTotaltime;

    private ImageView mImageViewFullScreen;
    private ImageView mImageViewBack;
    private ImageView mImageViewStereo;

    private VrVideoView mVrVideoView;
    private SeekBar mSeekBar;
    private boolean isPaused = false;

    private VideoItemModel mVideoItemModel;

    private boolean isFirstPlay = true;

    private boolean isCompletion = false;

    private CompoundButton.OnCheckedChangeListener playChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isFirstPlay) {
                new VideoLoaderTask().execute(mVideoItemModel.getCoverURL());
                isFirstPlay = false;
            }


            Logger.e("isChecked =  " + isChecked);
            Logger.e("isCompletion =  " + isCompletion);

            if (isChecked) {
                //播放完毕重新播放
//                if (isCompletion) {
//                    isCompletion = false;
//                    mVrVideoView.seekTo(0);
//                } else {
                    mVrVideoView.playVideo();
//                }

            } else {
                mVrVideoView.pauseVideo();
            }

        }
    };

    public static VRVideoFragment newInstance(VideoItemModel videoItemModel) {

        Bundle args = new Bundle();
        args.putParcelable(VideoItemModel.class.getCanonicalName(), videoItemModel);

        VRVideoFragment fragment = new VRVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int intLayoutResId() {
        return R.layout.fragment_vrvideo;
    }

    @Override
    protected void initView(View view) {

        mFrameLayoutController = (FrameLayout) view.findViewById(R.id.framelayout_vrvideo_controller);
        mCheckBoxPlay = (CheckBox) view.findViewById(R.id.checkbox_vrvideo_play);

        mTextViewCurrentTime = (TextView) view.findViewById(R.id.textview_vrvideo_currenttime);
        mTextViewTotaltime = (TextView) view.findViewById(R.id.textview_vrvideo_totaltime);

        mImageViewFullScreen = (ImageView) view.findViewById(R.id.imageview_vrvideo_fullscreen);
        mImageViewBack = (ImageView) view.findViewById(R.id.imageview_vrvideo_back);
        mImageViewStereo = (ImageView) view.findViewById(R.id.imageview_vrvideo_stereo);

        mImageViewFullScreen.setOnClickListener(this);
        mImageViewBack.setOnClickListener(this);
        mImageViewStereo.setOnClickListener(this);
        mCheckBoxPlay.setOnCheckedChangeListener(playChangeListener);


        mVideoItemModel = getArguments().getParcelable(VideoItemModel.class.getCanonicalName());


        mVrVideoView = (VrVideoView) view.findViewById(R.id.vrvideoview_vrvideo);
        mVrVideoView.setEventListener(new ActivityEventListener());
        mVrVideoView.setDisplayMode(1);


        hideDefaultViews(mVrVideoView);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_vrvideo);
        mSeekBar.setOnSeekBarChangeListener(new SeekBarListener());


        //        Logger.e("VRVideoPath = " + mVideoItemModel.getCoverURL());

    }

    /**
     * 隐藏默认的播放控制按钮
     *
     * @param vrVideoView VR播放组件
     */
    private void hideDefaultViews(VrVideoView vrVideoView) {

        VrWidgetView vrWidgetView = new VrWidgetView(getActivity()) {

            @Override
            protected VrWidgetRenderer createRenderer(Context context, VrWidgetRenderer.GLThreadScheduler glThreadScheduler, float v, float v1, int i) {
                return null;
            }
        };

        vrVideoView.setFullscreenButtonEnabled(false);
        vrVideoView.setInfoButtonEnabled(false);
        vrVideoView.setStereoModeButtonEnabled(false);
        vrVideoView.setTransitionViewEnabled(false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            long progressTime = savedInstanceState.getLong(STATE_PROGRESS_TIME);
            mVrVideoView.seekTo(progressTime);
            mSeekBar.setMax((int) savedInstanceState.getLong(STATE_VIDEO_DURATION));
            mSeekBar.setProgress((int) progressTime);
            mCheckBoxPlay.setChecked(savedInstanceState.getBoolean(STATE_IS_PLAY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(STATE_PROGRESS_TIME, mVrVideoView.getCurrentPosition());
        savedInstanceState.putLong(STATE_VIDEO_DURATION, mVrVideoView.getDuration());
        savedInstanceState.putBoolean(STATE_IS_PLAY, mCheckBoxPlay.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onResume() {
        mVrVideoView.resumeRendering();

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mVrVideoView.pauseRendering();
    }

    @Override
    public void onDestroy() {
        mVrVideoView.shutdown();
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_vrvideo_fullscreen:
                mVrVideoView.setDisplayMode(2);
                break;
            case R.id.imageview_vrvideo_back:
                getActivity().onBackPressed();
                break;
            case R.id.imageview_vrvideo_stereo:
                mVrVideoView.setDisplayMode(3);
                break;
        }
    }

    /**
     * When the user manipulates the seek bar, update the video position.
     */
    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mVrVideoView.seekTo(progress);
            } // else this was from the ActivityEventHandler.onNewFrame()'s seekBar.setProgress update.
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }


    }

    /**
     * Listen to the important events from widget.
     */
    private class ActivityEventListener extends VrVideoEventListener {
        private static final int DISPLAYMODE_PORTRAIT = 1;
        private static final int DISPLAYMODE_LANDSCAPE = 2;
        private static final int DISPLAYMODE_STEREO = 3;

        /**
         * Called by video widget on the UI thread when it's done loading the video.
         */
        @Override
        public void onLoadSuccess() {
            mSeekBar.setMax((int) mVrVideoView.getDuration());

        }

        /**
         * Called by video widget on the UI thread on any asynchronous error.
         */
        @Override
        public void onLoadError(String errorMessage) {
        }

        @Override
        public void onClick() {

            if (mVrVideoView.getDisplayMode() == DISPLAYMODE_PORTRAIT) {
                if (mFrameLayoutController.getVisibility() == View.VISIBLE) {
                    mFrameLayoutController.setVisibility(View.GONE);
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
            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());

            //            if(infoButton!=null){
            //                infoButton.setVisibility(View.GONE);
            //            }
        }

        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
            //            mVrVideoView.seekTo(0);
            Logger.e("onCompletion");
            isCompletion = true;
            mCheckBoxPlay.setChecked(false);

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
                case DISPLAYMODE_STEREO://普通全屏

                    break;

            }
        }


    }

    /**
     * Helper class to manage threading.
     */
    class VideoLoaderTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... files) {
            try {

                VrVideoView.Options options = new VrVideoView.Options();
                mVrVideoView.loadVideo(Uri.parse(files[0]), options);
            } catch (IOException e) {
                e.printStackTrace();

            }

            return true;
        }
    }
}
