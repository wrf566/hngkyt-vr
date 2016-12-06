package com.hzgkyt.vr.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.hzgkyt.vr.R;
import com.hzgkyt.vr.model.VideoItemModel;

import java.io.IOException;

/**
 * Created by wrf on 2016/12/5.
 */

public class VRVideoFragment extends BaseFragment {


    public static final String VRVIDEO_PATH = "vrvideo_path";
    public static final String VRVIDEO_NAME = "vrvideo_name";
    private static final String STATE_IS_PAUSED = "isPaused";
    private static final String STATE_PROGRESS_TIME = "progressTime";
    private static final String STATE_VIDEO_DURATION = "videoDuration";
    private VrVideoView mVrVideoView;
    private SeekBar mSeekBar;
    private VrVideoView.Options videoOptions = new VrVideoView.Options();
    private boolean isPaused = false;

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
        mVrVideoView = (VrVideoView) view.findViewById(R.id.vrvideoview_vrvideo);
        mVrVideoView.setEventListener(new ActivityEventListener());
        mVrVideoView.setDisplayMode(3);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_vrvideo);
        mSeekBar.setOnSeekBarChangeListener(new SeekBarListener());


        VideoItemModel videoItemModel = getArguments().getParcelable(VideoItemModel.class.getCanonicalName());


//        Logger.e("VRVideoPath = " + videoItemModel.getCoverURL());
        //"file:///sdcard/vrtest.mp4"
        new VideoLoaderTask().execute(videoItemModel.getCoverURL());

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            long progressTime = savedInstanceState.getLong(STATE_PROGRESS_TIME);
            mVrVideoView.seekTo(progressTime);
            mSeekBar.setMax((int) savedInstanceState.getLong(STATE_VIDEO_DURATION));
            mSeekBar.setProgress((int) progressTime);
            isPaused = savedInstanceState.getBoolean(STATE_IS_PAUSED);
            if (isPaused) {
                mVrVideoView.pauseVideo();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong(STATE_PROGRESS_TIME, mVrVideoView.getCurrentPosition());
        savedInstanceState.putLong(STATE_VIDEO_DURATION, mVrVideoView.getDuration());
        savedInstanceState.putBoolean(STATE_IS_PAUSED, isPaused);
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
        isPaused = true;
    }

    @Override
    public void onDestroy() {
        mVrVideoView.shutdown();
        super.onDestroy();

    }

    private void togglePause() {
        if (isPaused) {
            mVrVideoView.playVideo();
        } else {
            mVrVideoView.pauseVideo();
        }
        isPaused = !isPaused;
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
        /**
         * Called by video widget on the UI thread when it's done loading the video.
         */
        @Override
        public void onLoadSuccess() {
            //            loadVideoStatus = LOAD_VIDEO_STATUS_SUCCESS;
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
//            togglePause();
        }

        /**
         * Update the UI every frame.
         */
        @Override
        public void onNewFrame() {
            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());

        }

        /**
         * Make the video play in a loop. This method could also be used to move to the next video in
         * a playlist.
         */
        @Override
        public void onCompletion() {
            mVrVideoView.seekTo(0);

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
